# Bài 9_03 - CI/CD Automation cho Fibonacci

## 1. Tóm tắt ý tưởng

Tái sử dụng hàm `Solution.fibonacci(long n)` đã viết ở các bài trước, đóng gói
lại thành **một dự án Maven hoàn chỉnh**, tích hợp:

- **Logging**: SLF4J 2.x (API) + Logback 1.5.x (implementation), cấu hình tại `src/main/resources/logback.xml`.
- **Unit Tests**: JUnit 5 (Jupiter) — bao phủ các case: input âm, base case, giá trị chuẩn,
  ranh giới tràn số tại F(92) và F(93).
- **Build**: Maven, plugin `maven-compiler-plugin` với `<release>25</release>`,
  và `maven-shade-plugin` để tạo "fat jar" chạy được bằng `java -jar`.
- **CI/CD**: workflow GitHub Actions tại `.github/workflows/ci-bai9-03.yml` (ở ROOT repo)
  tự động build, test và upload JAR mỗi khi có `push` hoặc `pull_request`
  chạm vào thư mục này.

## 2. Vị trí trong repo

File workflow **không** nằm trong `Bai9_03/`. Nó phải ở root của repository:

```
HW_UET_CS2043_9_25021769/                          (root repo)
├── .github/
│   └── workflows/
│       ├── ci-bai9-04.yml                         (workflow của Bài 04)
│       └── ci-bai9-03.yml                         (workflow của bài NÀY)
└── TranTrungHieu_25021769_Week9/
    ├── Bai9_04/                                   (bài trước đó)
    └── Bai9_03/                                   (THƯ MỤC NÀY)
        ├── src/
        │   ├── main/java/{Main,Solution}.java
        │   ├── main/resources/logback.xml
        │   └── test/java/SolutionTest.java
        ├── lib/
        ├── pom.xml
        ├── run.sh
        └── README.md
```

Workflow dùng `paths:` filter để chỉ chạy khi có thay đổi trong
`TranTrungHieu_25021769_Week9/Bai9_03/**`, tránh chạy CI lặp lại cho mỗi commit
ở các bài khác trong cùng repo.

## 3. Lý do lựa chọn hướng tiếp cận này

| Quyết định | Lý do |
|---|---|
| SLF4J + Logback | Cặp đôi tiêu chuẩn trong ngành; SLF4J cho phép đổi backend mà không phải sửa code; Logback có cấu hình XML linh hoạt. |
| JUnit 5 (Jupiter) | API hiện đại (`@ParameterizedTest`, `@DisplayName`); chuẩn thực tế hiện nay. |
| `maven-shade-plugin` | Tạo một file `.jar` duy nhất chứa mọi dependency, dễ upload artifact và dễ chạy trên máy giáo viên. |
| Workflow trigger cả `push` và `pull_request` | Chặn lỗi sớm ngay khi mở PR, trước khi merge vào branch chính. |
| `actions/setup-java@v4` với Temurin 25 | Trùng khớp với `<release>25</release>` trong pom.xml; `cache: maven` tăng tốc các lần run sau. |
| `paths:` filter | Mỗi bài có workflow riêng, chỉ build lại bài bị chỉnh sửa — không lãng phí runner cho các bài không liên quan. |

## 4. Cách chạy local

Vào thư mục `Bai9_03/` rồi chạy:

```bash
chmod +x run.sh
./run.sh
```

`run.sh` sẽ chạy `mvn package` rồi `java -jar target/fibonacci-1.0.0.jar`.
Yêu cầu: JDK 25 + Maven 3.9+.

### Chạy tests riêng

```bash
mvn test
```

## 5. Kiểm thử CI bằng cách cố tình gây lỗi build

### Kịch bản A: Lỗi compile

Sửa `src/main/java/Solution.java`, thêm dòng sai cú pháp:

```java
public static long fibonacci(long n) {
    int x = "not an int";   // <-- type error
    ...
}
```

Commit & push. Workflow sẽ fail tại phase **compile**. Trong tab Actions, log
hiện rõ dòng và lỗi của compiler.

### Kịch bản B: Lỗi unit test

Sửa expected value trong `src/test/java/SolutionTest.java`:

```java
"10, 56",   // đúng phải là 55
```

Workflow fail tại phase **test** với message `expected: <56> but was: <55>`.
Phase `package` và `upload-artifact` không được thực thi (do step trước fail
nên các step sau bị skip).

Sau khi xem log để hiểu nguyên nhân, revert lại commit là build pass trở lại.

## 6. Workflow runs (điền link sau khi push)

- **Bug run** (build fail): _<paste URL run ở đây>_
- **Fix run** (build pass + artifact uploaded): _<paste URL run ở đây>_

## 7. Yêu cầu môi trường

- JDK 25
- Maven 3.9+
- Bash (Git Bash trên Windows)