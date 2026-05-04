# Bài 9_06 — CI/CD Pipeline Optimization & Caching

## 1. Tóm tắt ý tưởng

Khi dự án phát triển, mỗi lần CI chạy lại đều phải tải toàn bộ Maven dependency từ
Maven Central → mất thời gian đáng kể, đặc biệt với project có nhiều thư viện.

Bài này cấu hình **dependency caching** trên GitHub Actions thông qua tham số
`cache: 'maven'` của action `actions/setup-java@v4`. Cách này tự động cache thư
mục `~/.m2/repository` dựa trên hash của `pom.xml` (và `pom.xml` của các module
con nếu có). Kết quả:

- **Lần đầu chạy**: cache miss → Maven tải dependency từ Central như bình thường
  → cache được upload lên GitHub.
- **Lần sau** (khi `pom.xml` không đổi): cache hit → restore từ cache → bỏ qua
  bước download → workflow nhanh hơn rõ rệt.

## 2. Vì sao chọn `cache: 'maven'` của setup-java thay vì `actions/cache@v4`?

| Tiêu chí | `cache: 'maven'` (setup-java) | `actions/cache@v4` thủ công |
|---|---|---|
| Số dòng config | 1 dòng | 5–7 dòng |
| Cache key | Tự sinh từ hash `pom.xml` | Phải tự viết `${{ hashFiles('**/pom.xml') }}` |
| Cache path | `~/.m2/repository` mặc định | Phải chỉ định tay |
| Khả năng sai sót | Thấp | Có thể cache sai path/key |

Cách tích hợp sẵn vừa ngắn vừa an toàn → ưu tiên cho bài học này.

## 3. Cấu trúc thư mục

```
Bai9_06/
├── pom.xml                     # Maven config — nhiều dep để cache có ý nghĩa
├── src/
│   ├── main/java/Main.java     # "Cham" vao tat ca dep de chac chan duoc tai
│   └── test/java/MainTest.java # Test don gian cho mvn test
├── run.sh                      # Script chay local
└── README.md                   # File nay
```

Workflow YAML đặt ở `.github/workflows/ci-bai9-06.yml` tại **root repo**, dùng
`paths:` filter để chỉ trigger khi `Bai9_06/**` thay đổi.

## 4. Cách chạy

### Local

Yêu cầu: JDK 25, Maven 3.9+.

```bash
chmod +x run.sh
./run.sh
```

### Trên GitHub Actions

Tự động chạy mỗi lần `push`/`pull_request` chạm vào `Bai9_06/`. Có thể chạy tay
qua nút **Run workflow** trong tab **Actions** (nhờ `workflow_dispatch`).

## 5. Quy trình demo & so sánh thời gian

### Bước 1 — Push lần 1 (cache miss)

Đẩy code lên repo. Vào tab **Actions** → đợi workflow chạy xong → mở step
*"Set up JDK 25 with Maven cache"* và *"Build & test (mvn package)"*, ghi lại
thời gian.

Trong log của step setup-java, sẽ thấy đoạn:

```
Cache not found for input keys: setup-java-Linux-Ubuntu-maven-...
```

Trong step `mvn package`, có hàng loạt dòng:

```
Downloading from central: https://repo.maven.apache.org/maven2/...
```

→ Maven đang tải dep từ Central. Sau khi job kết thúc thành công, GitHub Actions
sẽ **upload cache** (~50–100 MB cho project này).

### Bước 2 — Push lần 2 (cache hit)

Sửa nhỏ một file (ví dụ thêm comment trong `Main.java`), commit & push.

Trong log step setup-java lần này:

```
Cache restored from key: setup-java-Linux-Ubuntu-maven-<hash>
```

Trong step `mvn package`, **không còn** dòng `Downloading from central` nào
(hoặc chỉ còn vài file plugin nhỏ chưa cache được) → step kết thúc nhanh hơn.

### Bước 3 — Bảng so sánh

| | Lần 1 (cache miss) | Lần 2 (cache hit) | Tiết kiệm |
|---|---|---|---|
| Step *Set up JDK*       | _<4>_ | _<điền giây>_ | _<%>_ |
| Step *mvn package*      | _<20>_ | _<điền giây>_ | _<%>_ |
| **Tổng workflow**       | _<24>_ | _<điền giây>_ | _<%>_ |

> Hiếu điền số liệu vào bảng trên sau khi chạy 2 lần workflow.

## 6. Workflow runs

Paste URL từ tab Actions sau khi push:

- **Run lần 1** (cache miss): _<paste URL>_
- **Run lần 2** (cache hit):  _<paste URL>_

## 7. Phân tích log — chứng minh dependency lấy từ cache

Mở run lần 2 → expand step *"Set up JDK 25 with Maven cache"*. Đoạn log đáng
chú ý:

```
Run actions/setup-java@v4
  ...
  cache: maven
Trying to resolve the latest version from remote
...
/usr/bin/tar -xf /home/runner/work/_temp/<hash>/cache.tzst -P -C ...
Cache restored successfully
Cache restored from key: setup-java-Linux-Ubuntu-maven-<hash>
```

Đối chiếu với run lần 1, đoạn tương ứng sẽ là:

```
Cache not found for input keys: setup-java-Linux-Ubuntu-maven-<hash>
```

Tiếp đó, mở step *"Build & test (mvn package)"*:

- **Lần 1** chứa nhiều dòng `Downloading from central: https://repo.maven.apache.org/maven2/...`
- **Lần 2** không có (hoặc rất ít) dòng `Downloading` — Maven dùng JAR đã có
  sẵn trong `~/.m2/repository` được restore từ cache.

→ Đây chính là bằng chứng dependency đã được lấy thành công từ cache thay vì
phải tải lại từ Maven Central.

## 8. Yêu cầu môi trường

- JDK 25 (Temurin trên CI)
- Maven 3.9+
- Bash (Git Bash trên Windows)
