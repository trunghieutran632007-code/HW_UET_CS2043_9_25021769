# Bai 9_08 — Đóng gói sản phẩm thực thi với Maven

## 1. Ý tưởng chính

Dùng **Maven** để build project Java thành một file `.jar` thực thi (executable JAR), có thể chạy độc lập trên bất kỳ máy nào có JDK mà không cần IDE. Cấu hình `maven-jar-plugin` trong `pom.xml` để chỉ định `Main-Class` trong `MANIFEST.MF`, sau đó dùng `mvn clean package` để tạo file `.jar`.

## 2. Cách chạy

```bash
chmod +x run.sh
./run.sh
```

`run.sh` sẽ thực thi 2 bước:

1. `mvn clean package` — build và đóng gói thành `target/bai9-08.jar`
2. `java -jar target/bai9-08.jar` — chạy file `.jar` vừa tạo

## 3. Cấu trúc project

```
Bai9_08/
├── src/
│   └── main/
│       └── java/
│           └── vn/uet/lapnangcao/
│               └── Main.java
├── pom.xml
├── run.sh
└── README.md
```

Maven yêu cầu source code đặt ở `src/main/java/...` theo convention. Khi build, tất cả file `.java` trong thư mục này sẽ được compile và đóng gói tự động.

## 4. Vì sao có thể chạy bằng `java -jar`?

Plugin `maven-jar-plugin` được cấu hình để ghi dòng `Main-Class: vn.uet.lapnangcao.Main` vào file `META-INF/MANIFEST.MF` bên trong `.jar`. Khi gặp `java -jar <file>.jar`, JVM sẽ:

1. Đọc `MANIFEST.MF` từ trong file `.jar`
2. Tìm thuộc tính `Main-Class`
3. Load class đó và gọi method `main()` của nó

Nếu không có `Main-Class` trong manifest, lệnh `java -jar` sẽ báo lỗi "no main manifest attribute".

Có thể kiểm tra manifest bằng lệnh:

```bash
unzip -p target/bai9-08.jar META-INF/MANIFEST.MF
```

---

## 5. Ý nghĩa chi tiết của thư mục `target/`

`target/` là **thư mục output mặc định (convention)** của Maven — nơi chứa toàn bộ sản phẩm sinh ra từ quá trình build. Đây không phải là một quy tắc kỹ thuật bắt buộc, mà là một quy ước (convention over configuration) được toàn bộ hệ sinh thái Maven tuân theo.

### 5.1. Nội dung điển hình của `target/`

Sau khi chạy `mvn clean package`, thư mục `target/` thường có cấu trúc:

```
target/
├── classes/                      # Bytecode (.class) compile tu src/main/java/
│   └── vn/uet/lapnangcao/
│       └── Main.class
├── test-classes/                 # Bytecode tu src/test/java/ (neu co)
├── generated-sources/            # Code duoc sinh tu dong (annotation processor, etc.)
├── maven-status/                 # Metadata Maven dung de incremental build
├── maven-archiver/
│   └── pom.properties            # Thong tin groupId, artifactId, version
├── bai9-08.jar                   # Artifact cuoi cung (tu <finalName>)
└── surefire-reports/             # Bao cao test (neu co chay test)
```

### 5.2. Vì sao Maven dùng `target/` riêng biệt?

- **Tách biệt source và build artifacts**: Source code (trong `src/`) không bị "nhiễm" file compile, dễ quản lý version control.
- **Reproducibility**: `mvn clean` chỉ cần xóa `target/` là build lại được từ đầu, đảm bảo môi trường build sạch.
- **Quy ước phổ biến**: Tất cả công cụ Maven, IDE, CI/CD đều biết artifact nằm ở `target/`. Chính vì vậy đề bài viết `java -jar target/*.jar` — giáo viên không cần biết chi tiết, chỉ cần biết artifact luôn ở đó.

### 5.3. `target/` luôn được `.gitignore`

Vì là output có thể tái tạo, `target/` không bao giờ được commit lên Git. Mỗi máy build sẽ tự sinh ra `target/` của riêng nó.

### 5.4. Có thể đổi tên `target/` không?

Có, qua thẻ `<directory>` trong `pom.xml`:

```xml
<build>
    <directory>${project.basedir}/build</directory>
</build>
```

Nhưng **không nên** — vì sẽ phá vỡ convention, gây bối rối cho người khác và các tool khác.

---

## 6. Ý nghĩa chi tiết của phase `package`

`package` là **một phase trong Maven Default Lifecycle**. Để hiểu nó, cần hiểu khái niệm **lifecycle** trước.

### 6.1. Maven Lifecycle là gì?

Maven định nghĩa 3 built-in lifecycle:

| Lifecycle  | Mục đích                                         |
| ---------- | ------------------------------------------------ |
| `clean`    | Xóa output từ build trước (xóa `target/`)        |
| `default`  | Build và deploy project (lifecycle chính)        |
| `site`     | Sinh tài liệu (documentation site) cho project   |

Mỗi lifecycle gồm một chuỗi **phase** chạy **tuần tự**.

### 6.2. Default lifecycle có những phase nào?

Default lifecycle có ~23 phase, các phase quan trọng nhất là:

```
validate → compile → test → package → verify → install → deploy
```

| Phase      | Việc làm                                                        |
| ---------- | --------------------------------------------------------------- |
| `validate` | Kiểm tra `pom.xml` hợp lệ, project có đủ thông tin              |
| `compile`  | Compile `src/main/java/` → `target/classes/`                    |
| `test`     | Chạy unit test trong `src/test/java/`                           |
| `package`  | **Đóng gói bytecode + resources thành artifact (`.jar`/`.war`)** |
| `verify`   | Kiểm tra artifact có đạt tiêu chuẩn không (integration test)    |
| `install`  | Cài artifact vào local Maven repo (`~/.m2/repository`)          |
| `deploy`   | Upload artifact lên remote repo (Nexus, Maven Central, …)       |

### 6.3. Quy tắc "chạy tuần tự" của Maven

**Khi gọi `mvn <phase>`, Maven sẽ chạy phase đó VÀ TẤT CẢ phase đứng trước nó.**

Ví dụ với `mvn package`, Maven chạy lần lượt:

```
validate → initialize → ... → compile → ... → test → package
```

Đây là lý do vì sao chỉ cần gõ `mvn package` là bytecode đã được compile sẵn, không cần gọi `mvn compile` trước.

### 6.4. Phase `package` làm gì cụ thể?

Phase `package` thực hiện 3 việc chính:

1. **Đọc `<packaging>` trong `pom.xml`** để biết format đầu ra (`jar`, `war`, `ear`, `pom`, …). Ở bài này: `<packaging>jar</packaging>`.
2. **Gọi plugin tương ứng**:
   - `jar` → `maven-jar-plugin`
   - `war` → `maven-war-plugin`
   - `ear` → `maven-ear-plugin`
3. **Plugin gom tất cả file vào archive**:
   - Bytecode từ `target/classes/`
   - Resources từ `src/main/resources/` (nếu có)
   - Tự sinh `META-INF/MANIFEST.MF` (với cấu hình `Main-Class` đã chỉ định)
   - Đặt file kết quả vào `target/<finalName>.jar`

### 6.5. Vì sao gọi là "package" mà không phải "build"?

Trong thuật ngữ phần mềm:

- **Compile** = chuyển source code → bytecode (cấp thấp, từng file)
- **Package** = đóng gói bytecode + metadata thành **một đơn vị phân phối được** (cấp cao, một file duy nhất có thể gửi đi)

`package` đúng nghĩa là **phase chuyển từ "đống bytecode rời rạc" thành "một artifact distributable"** — sẵn sàng để chạy, để gửi, để publish.

### 6.6. So sánh `mvn compile` vs `mvn package`

| Lệnh             | Sinh ra `target/classes/` | Sinh ra `target/*.jar` | Có thể `java -jar`? |
| ---------------- | ------------------------- | ---------------------- | ------------------- |
| `mvn compile`    | ✅                        | ❌                     | ❌                  |
| `mvn package`    | ✅                        | ✅                     | ✅                  |
| `mvn install`    | ✅                        | ✅ + copy vào `~/.m2`  | ✅                  |

### 6.7. Vì sao thường dùng `mvn clean package` thay vì chỉ `mvn package`?

`clean` thuộc lifecycle khác (`clean lifecycle`), không tự động chạy trước `package`. Việc gộp `mvn clean package` đảm bảo build từ đầu, tránh artifact cũ còn sót lại trong `target/` gây nhiễu kết quả.

---

## 7. Tóm tắt

| Khái niệm        | Vai trò                                                                    |
| ---------------- | -------------------------------------------------------------------------- |
| `target/`        | Thư mục output mặc định, chứa bytecode + artifact cuối cùng                |
| Phase `package`  | Bước đóng gói bytecode thành `.jar` thực thi, đặt vào `target/`            |
| `maven-jar-plugin` | Plugin thực thi phase `package` cho dự án `<packaging>jar</packaging>`   |
| `Main-Class`     | Thuộc tính trong `MANIFEST.MF` cho biết class nào chứa `main()` để JVM gọi |

Sự kết hợp 4 yếu tố trên cho phép một project Java được "đóng gói thực thi" hoàn chỉnh — đúng yêu cầu đề bài.
