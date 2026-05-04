# Bài 10: The Broken Pipeline — PHIÊN BẢN LỖI

## Mục đích của zip này

Đây là phiên bản **chứa toàn bộ 4 lỗi** dùng để push lên GitHub và chụp ảnh pipeline đỏ làm bằng chứng. Sau khi chụp đủ ảnh, sửa từng lỗi theo hướng dẫn bên dưới (hoặc dùng zip phiên bản đã sửa).

- 3 lỗi đầu là lỗi gốc của đề bài (workflow, logback, surefire).
- Lỗi 4 là lỗi tự nghĩ — assertion sai trong `testStandard()`.

## Cấu trúc

```
Bai9_10/
├── .github/workflows/ci.yml      ← chứa lỗi 1
├── src/
│   ├── main/java/com/lab/ShippingCalculator.java   (không lỗi)
│   └── test/java/com/lab/ShippingCalculatorTest.java   ← chứa lỗi 4
├── pom.xml                        ← chứa lỗi 2 và lỗi 3
├── run.sh
└── README.md
```

---

# Lỗi 1 — Workflow thiếu bước `actions/checkout`

**File:** `.github/workflows/ci.yml`

**Log GitHub Actions dự kiến:**
```
Run mvn package
[ERROR] The goal you specified requires a project to execute but there is no POM
in this directory (/home/runner/work/<repo>/<repo>). Please verify you invoked
Maven from the correct directory.
```

**Nguyên nhân kỹ thuật:** Mỗi job trên GitHub Actions chạy trong runner mới hoàn toàn rỗng — chỉ có hệ điều hành, không có code. `actions/checkout` là bước `git clone` repo về runner. Thiếu nó → runner không có `pom.xml` → Maven không tìm thấy project descriptor → fail trước cả khi compile.

**Code lỗi (đang có trong file):**
```yaml
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Build with Maven
        run: mvn package
```

**Code đúng (sửa thành):**
```yaml
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout code
        uses: actions/checkout@v4

      - name: Set up JDK 25
        uses: actions/setup-java@v4
        with:
          java-version: '25'
          distribution: 'temurin'
          cache: maven

      - name: Build and test with Maven
        run: mvn -B verify
```

Đồng thời nâng JDK 17 → 25 cho khớp với yêu cầu môn học, đổi `mvn package` thành `mvn -B verify` để chạy đầy đủ phase test.

---

# Lỗi 2 — `logback-classic` version `9.9.9` không tồn tại

**File:** `pom.xml`

**Log GitHub Actions dự kiến:**
```
[ERROR] Failed to execute goal on project shipping-app:
Could not resolve dependencies for project com.lab:shipping-app:jar:1.0-SNAPSHOT:
The following artifacts could not be resolved: ch.qos.logback:logback-classic:jar:9.9.9
(absent): Could not find artifact ch.qos.logback:logback-classic:jar:9.9.9
in central (https://repo.maven.apache.org/maven2)
```

**Nguyên nhân kỹ thuật:** Logback chưa bao giờ phát hành version `9.9.9`. Maven dependency resolver query Maven Central, không tìm thấy artifact → fail toàn bộ build trong giai đoạn dependency resolution. Phiên bản stable hiện tại là dòng `1.5.x`.

**Code lỗi (đang có trong file):**
```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>9.9.9</version>
</dependency>
```

**Code đúng (sửa thành):**
```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.5.13</version>
</dependency>
```

---

# Lỗi 3 — `maven-surefire-plugin` version `2.12.4` quá cũ, không nhận JUnit 5

**File:** `pom.xml`

**Log GitHub Actions dự kiến:**
```
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ shipping-app ---
[INFO] No tests to run.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
```

**Nguyên nhân kỹ thuật:** Surefire `2.12.4` phát hành năm 2012, ra đời trước JUnit 5 nhiều năm. Nó chỉ nhận class kế thừa `junit.framework.TestCase` (JUnit 3) hoặc annotation `@org.junit.Test` (JUnit 4). Annotation `@org.junit.jupiter.api.Test` của JUnit 5 **hoàn toàn vô hình** với nó. Surefire scan class path, không tìm thấy test class nào hợp lệ → bỏ qua hết, in `No tests to run`. JUnit 5 yêu cầu Surefire `>= 2.22.0` (lý tưởng là `3.x`).

Lỗi này nguy hiểm hơn lỗi 1 và lỗi 2 vì pipeline vẫn **báo xanh** dù không chạy test nào — sẽ che giấu bugs trong code thật. Đây là dạng "false green" cần đặc biệt cảnh giác.

**Code lỗi (đang có trong file):**
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.12.4</version>
</plugin>
```

**Code đúng (sửa thành):**
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.5</version>
</plugin>
```

---

# Lỗi 4 — Tự tạo: assertion sai trong `testStandard()`

**File:** `src/test/java/com/lab/ShippingCalculatorTest.java`

**Log GitHub Actions dự kiến:**
```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.lab.ShippingCalculatorTest
[ERROR] Tests run: 3, Failures: 1, Errors: 0, Skipped: 0
[ERROR] Failures:
[ERROR]   ShippingCalculatorTest.testStandard:14
        expected: <15500.0> but was: <15000.0>
[INFO]
[INFO] BUILD FAILURE
```

**Nguyên nhân kỹ thuật:** Method `calculate(5, "STANDARD")` trong `ShippingCalculator` thực hiện `5 * 3000 = 15000.0` theo công thức cố định. Test assert giá trị `15500.0` là sai về mặt giá trị mong đợi — không khớp với spec mà code triển khai. JUnit 5 dùng `Object.equals()` cho `Double` so sánh, hai giá trị khác nhau → `AssertionFailedError` được throw, test fail, Surefire đánh dấu phase test là FAILURE, build dừng với exit code khác 0 → pipeline đỏ.

Lỗi này khác về bản chất so với 3 lỗi trên:
- Lỗi 1, 2, 3 thuộc về **infrastructure** (workflow, dependency, plugin) — dự án không build/test được.
- Lỗi 4 thuộc về **logic correctness** — dự án build và chạy bình thường, nhưng kết quả không đúng kỳ vọng.

Đây chính là giá trị cốt lõi của CI: chặn code không correct trước khi merge vào main branch.

**Code lỗi (đang có trong file):**
```java
@Test
void testStandard() {
    assertEquals(15500.0, calc.calculate(5, "STANDARD"));
}
```

**Code đúng (sửa thành):**
```java
@Test
void testStandard() {
    assertEquals(15000.0, calc.calculate(5, "STANDARD"));
}
```

Trong trường hợp này, công thức `weight * 3000` cho STANDARD là đúng theo spec, nên test mới là phần cần sửa (đổi expected từ `15500.0` về `15000.0`).

---

# Quy trình thực hiện

1. Push folder này lên GitHub → quan sát pipeline đỏ (lỗi 1)
2. Sửa lỗi 1 (thêm `actions/checkout`) → push → quan sát pipeline (vẫn đỏ vì lỗi 2)
3. Sửa lỗi 2 (logback version) → push → quan sát pipeline ("xanh giả" vì lỗi 3)
4. Sửa lỗi 3 (surefire version) → push → pipeline đỏ vì test fail (lỗi 4 lộ ra)
5. Sửa lỗi 4 (đổi `15500.0` về `15000.0`) → push → pipeline xanh thật, 3 tests pass

Mỗi bước commit nên có message rõ ràng:
- `fix: add checkout step to workflow`
- `fix: correct logback version to 1.5.13`
- `fix: upgrade surefire to 3.2.5 for JUnit 5 support`
- `fix: correct expected value in testStandard`

Lưu ý thứ tự xuất hiện log của lỗi 3 và lỗi 4: Sau khi sửa lỗi 2, pipeline sẽ xanh giả (không chạy test). Khi sửa lỗi 3, Surefire mới bắt đầu thực sự chạy test và phát hiện lỗi 4. Đây chính là lý do đề bài yêu cầu "pipeline phải xanh hoàn toàn trước khi chuyển sang lỗi tiếp theo" — nếu không sửa lỗi 3 thì không bao giờ thấy lỗi 4.
