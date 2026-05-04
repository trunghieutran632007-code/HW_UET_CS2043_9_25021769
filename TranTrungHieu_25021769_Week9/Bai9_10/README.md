# Bài 10: The Broken Pipeline

## Tóm tắt

Dự án Maven `shipping-app` được cung cấp với pipeline CI báo đỏ. Bài này yêu cầu đọc log GitHub Actions, xác định nguyên nhân kỹ thuật của từng lỗi, sửa và push lại cho đến khi pipeline xanh hoàn toàn. Sau đó tự tạo thêm 1 lỗi mới (lỗi 4) để demo workflow debug.

## Cấu trúc

```
Bai9_10/
├── .github/workflows/ci.yml
├── src/
│   ├── main/java/com/lab/ShippingCalculator.java
│   └── test/java/com/lab/ShippingCalculatorTest.java
├── pom.xml
├── run.sh
└── README.md
```

> **Lưu ý về repo riêng:** Trong repo `HW_UET_CS2043_9_25021769`, workflow YAML đặt ở `.github/workflows/ci-bai9-10.yml` ở **root repo** với `paths:` filter trỏ về `TranTrungHieu_25021769_Week9/Bai9_10/**` và `defaults.run.working-directory` cùng giá trị. File `.github/workflows/ci.yml` trong zip này là phiên bản gốc theo đề bài, dùng khi project là độc lập.

## Cách chạy

```bash
chmod +x run.sh
./run.sh
```

`run.sh` chạy `mvn -B verify` - compile, chạy 3 unit test, đóng gói JAR.

---

# 3 lỗi có sẵn trong project

## Lỗi 1 — Workflow thiếu bước `actions/checkout`

**File:** `.github/workflows/ci.yml`, mục `steps`

**Log GitHub Actions:**
```
Run mvn package
[ERROR] The goal you specified requires a project to execute but there is no POM
in this directory (/home/runner/work/<repo>/<repo>). Please verify you invoked
Maven from the correct directory.
```

**Nguyên nhân kỹ thuật:** Mỗi job trên GitHub Actions chạy trong runner mới hoàn toàn rỗng - chỉ có hệ điều hành, không có code. `actions/checkout` là bước `git clone` repo về runner. Thiếu nó → runner không có `pom.xml` → Maven không tìm thấy project descriptor → fail trước cả khi compile.

**Code cũ (lỗi):**
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

**Code mới (đã sửa):**
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

Đồng thời nâng JDK từ 17 lên 25 cho khớp với yêu cầu môn học, đổi `mvn package` thành `mvn -B verify` để chạy đầy đủ phase test, thêm `cache: maven` để tăng tốc lần build sau.

---

## Lỗi 2 — `logback-classic` version `9.9.9` không tồn tại

**File:** `pom.xml`, dependency `ch.qos.logback:logback-classic`

**Log GitHub Actions:**
```
[ERROR] Failed to execute goal on project shipping-app:
Could not resolve dependencies for project com.lab:shipping-app:jar:1.0-SNAPSHOT:
The following artifacts could not be resolved: ch.qos.logback:logback-classic:jar:9.9.9
(absent): Could not find artifact ch.qos.logback:logback-classic:jar:9.9.9
in central (https://repo.maven.apache.org/maven2)
```

**Nguyên nhân kỹ thuật:** Logback chưa bao giờ phát hành version `9.9.9`. Maven dependency resolver query Maven Central, không tìm thấy artifact → fail toàn bộ build trong giai đoạn dependency resolution. Phiên bản stable của Logback hiện tại là dòng `1.5.x`.

**Code cũ (lỗi):**
```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>9.9.9</version>
</dependency>
```

**Code mới (đã sửa):**
```xml
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.5.13</version>
</dependency>
```

---

## Lỗi 3 — `maven-surefire-plugin` version `2.12.4` quá cũ, không nhận JUnit 5

**File:** `pom.xml`, plugin `maven-surefire-plugin`

**Log GitHub Actions:**
```
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ shipping-app ---
[INFO] No tests to run.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
```

**Nguyên nhân kỹ thuật:** Surefire `2.12.4` phát hành năm 2012, ra đời trước JUnit 5 nhiều năm. Nó chỉ nhận class kế thừa `junit.framework.TestCase` (JUnit 3) hoặc annotation `@org.junit.Test` (JUnit 4). Annotation `@org.junit.jupiter.api.Test` của JUnit 5 **hoàn toàn vô hình** với nó. Surefire scan class path, không tìm thấy test class nào hợp lệ → bỏ qua hết, in `No tests to run`. JUnit 5 yêu cầu Surefire `>= 2.22.0` (lý tưởng là `3.x`).

Lỗi này nguy hiểm hơn lỗi 1 và lỗi 2 vì pipeline vẫn **báo xanh** dù không chạy test nào - sẽ che giấu bugs trong code thật. Đây là dạng "false green" cần đặc biệt cảnh giác.

**Code cũ (lỗi):**
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.12.4</version>
</plugin>
```

**Code mới (đã sửa):**
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>3.2.5</version>
</plugin>
```

Sau khi sửa, log sẽ là:
```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.lab.ShippingCalculatorTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
```

---

# Lỗi 4 — Tự tạo (assertion sai trong test)

Sau khi pipeline xanh hoàn toàn với 3 lỗi đã sửa ở trên, mình cố tình đưa vào 1 lỗi assertion để demo flow CI bắt regression.

**File:** `src/test/java/com/lab/ShippingCalculatorTest.java`, method `testStandard()`

**Để demo lỗi này, thay đoạn code đã sửa (15000.0) bằng đoạn lỗi (15500.0), commit và push:**

**Code lỗi (dùng để chụp ảnh pipeline đỏ):**
```java
@Test
void testStandard() {
    assertEquals(15500.0, calc.calculate(5, "STANDARD"));
}
```

**Code đúng (giá trị hiện tại trong file):**
```java
@Test
void testStandard() {
    assertEquals(15000.0, calc.calculate(5, "STANDARD"));
}
```

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

**Nguyên nhân kỹ thuật:** Method `calculate(5, "STANDARD")` trong `ShippingCalculator` thực hiện `5 * 3000 = 15000.0` theo công thức cố định. Test assert giá trị `15500.0` là sai về mặt giá trị mong đợi - không khớp với spec mà code triển khai. JUnit 5 dùng `Object.equals()` cho `Double` so sánh, hai giá trị khác nhau → `AssertionFailedError` được throw, test fail, Surefire đánh dấu phase test là FAILURE, build dừng với exit code khác 0 → pipeline đỏ.

Lỗi này khác về bản chất so với 3 lỗi trên:
- Lỗi 1, 2, 3 thuộc về **infrastructure** (workflow, dependency, plugin) - dự án không build/test được.
- Lỗi 4 thuộc về **logic correctness** - dự án build và chạy bình thường, nhưng kết quả không đúng kỳ vọng.

Đây chính là giá trị cốt lõi của CI: chặn code không correct trước khi merge vào main branch.

**Cách sửa:** Đổi `15500.0` về `15000.0` để khớp với behavior thực của `ShippingCalculator.calculate()`. Nếu logic của method là sai, sửa method (không sửa test). Trong trường hợp này, công thức `weight * 3000` cho STANDARD là đúng theo spec, nên test mới là phần cần sửa.

---

# Quy trình thực hiện

1. Tạo repo `HW_UET_CS2043_9_25021769` (hoặc dùng repo có sẵn), push folder `Bai9_10` ban đầu (với cả 3 lỗi) → quan sát pipeline đỏ
2. Sửa lỗi 1 (thêm `actions/checkout`) → push → quan sát pipeline (vẫn đỏ vì lỗi 2)
3. Sửa lỗi 2 (logback version) → push → quan sát pipeline (xanh giả vì lỗi 3)
4. Sửa lỗi 3 (surefire version) → push → pipeline xanh thật, 3 test pass
5. Đưa lỗi 4 (đổi `15000.0` thành `15500.0` trong `testStandard()`) → push → pipeline đỏ → chụp ảnh
6. Sửa lỗi 4 (đổi lại `15000.0`) → push → pipeline xanh

Mỗi bước commit nên có message rõ ràng: `fix: add checkout step`, `fix: correct logback version`, `fix: upgrade surefire for JUnit 5`, `test: introduce wrong expected value (demo)`, `fix: correct expected value in testStandard`.
