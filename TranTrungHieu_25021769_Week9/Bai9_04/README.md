# Bai9_04 — Kiểm thử đa hệ điều hành với Matrix Strategy

## Tóm tắt ý tưởng

Vấn đề **"It works on my machine"** thường xảy ra khi code phụ thuộc ngầm vào hệ điều hành — kinh điển nhất là **path separator** (`\` trên Windows vs `/` trên Linux/macOS). Bài này:

1. Cấu hình **GitHub Actions Matrix Strategy** chạy cùng 1 unit test trên `ubuntu-latest`, `windows-latest`, `macos-latest` song song.
2. **Cố ý** viết code có hardcoded `\` để CI trên Linux & macOS sẽ fail → quan sát lỗi trực tiếp trên tab Actions.
3. **Refactor** dùng `java.nio.file.Path` để cả 3 OS đều pass.

## Cấu trúc

```
Bai9_04/
├── .github/workflows/ci.yml    # Matrix Strategy: 3 OS song song
├── src/
│   ├── Main.java               # Entry point
│   ├── PathUtils.java          # Da refactor (Path API)
│   └── PathUtilsTest.java      # Unit test tu viet
├── run.sh                      # Build & run
└── README.md
```

## Phần 1 — Bug cố ý (đã được sửa, ghi lại tại đây để truy vết)

Phiên bản đầu của `PathUtils.buildLogFilePath`:

```java
// PHIEN BAN BUG - hardcode separator cua Windows
public static String buildLogFilePath(String baseDir, String filename) {
    return baseDir + "\\logs\\" + filename;
}
```

Test so sánh kết quả với `File.separator`:

```java
String expected =
    "var" + File.separator + "logs" + File.separator + "app.log";
```

### Quan sát trên GitHub Actions (matrix run)

| OS               | `File.separator` | Output của bug         | Job status |
|------------------|------------------|------------------------|------------|
| `windows-latest` | `\`              | `var\logs\app.log`     | ✅ PASS    |
| `ubuntu-latest`  | `/`              | `var\logs\app.log`     | ❌ FAIL    |
| `macos-latest`   | `/`              | `var\logs\app.log`     | ❌ FAIL    |

Log fail (Linux/macOS):

```
[FAIL] buildLogFilePath uses OS-correct separator -> expected=<var/logs/app.log> actual=<var\logs\app.log>
```

→ Đúng mục tiêu của Matrix Strategy: bug cross-platform được phát hiện ngay từ CI thay vì đợi user báo "máy tôi không chạy được".

## Phần 2 — Refactor

```java
// PHIEN BAN HIEN TAI - dung java.nio.file.Path
public static String buildLogFilePath(String baseDir, String filename) {
    return Paths.get(baseDir, "logs", filename).toString();
}
```

`Paths.get(...)` chọn separator phù hợp tại runtime → cả 3 job đều xanh.

### Lý do chọn `Path` API thay vì `File.separator`

- **Đọc dễ hơn**: `Paths.get("data", "input", "users.csv")` rõ ràng là một path multi-segment, không cần ghép chuỗi tay.
- **Tự normalize**: bỏ separator thừa, chuẩn hóa `..` và `.`.
- **Tích hợp cả NIO.2 ecosystem**: `Files.exists`, `Files.readAllLines`, `Path.resolve`, `Path.relativize`...
- **API hiện đại** kể từ Java 7 — `File.separator` cộng chuỗi là cách cũ.

`File.separator` vẫn dùng được, nhưng chỉ nên giữ cho code legacy hoặc khi thực sự cần thao tác chuỗi.

## Cách chạy

```bash
chmod +x ./run.sh
./run.sh
```

Output mẫu (Ubuntu):

```
=== Bai9_04: Matrix Strategy CI Test ===
OS name       : Linux
File.separator: "/"

[PASS] buildLogFilePath uses OS-correct separator
[PASS] join with multiple segments uses OS-correct separator
[PASS] getFileName extracts only the file name

=== Test Summary ===
Total : 3
Passed: 3
Failed: 0
```

## Yêu cầu môi trường

- JDK 25
- Bash (Git Bash trên Windows)

## Ghi chú khi đặt workflow vào repo

File `.github/workflows/ci.yml` chỉ được GitHub Actions trigger nếu nó nằm ở **gốc của repository git**, không phải bên trong `Bai9_04/`. Khi tích hợp vào repo của tuần, copy file ra `<repo-root>/.github/workflows/ci.yml`. `working-directory: ./Bai9_04` trong workflow đã trỏ đúng tới thư mục bài này.
