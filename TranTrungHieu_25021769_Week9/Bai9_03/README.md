# Bai 03 - CI/CD Automation cho Fibonacci

## 1. Tom tat y tuong

Tai su dung ham `Solution.fibonacci(long n)` da viet o cac bai truoc, dong goi
lai thanh **mot du an Maven hoan chinh**, tich hop:

- **Logging**: SLF4J 2.x (API) + Logback 1.5.x (implementation), cau hinh tai `src/main/resources/logback.xml`.
- **Unit Tests**: JUnit 5 (Jupiter), bao phu cac case: input am, base case, gia tri chuan,
  overflow boundary tai F(92) va F(93).
- **Build**: Maven, plugin `maven-compiler-plugin` voi `<release>25</release>`,
  va `maven-shade-plugin` de tao "fat jar" chay duoc bang `java -jar`.
- **CI/CD**: workflow GitHub Actions (`.github/workflows/ci.yml`) tu dong build,
  test va upload JAR moi khi co `push` hoac `pull_request`.

## 2. Ly do chon huong tiep can nay

| Quyet dinh | Ly do |
|---|---|
| SLF4J + Logback | Cap doi tieu chuan trong nganh; SLF4J cho phep doi backend khong sua code; Logback co cau hinh XML linh hoat. |
| JUnit 5 (Jupiter) | API hien dai (`@ParameterizedTest`, `@DisplayName`); chuan thuc te. |
| `maven-shade-plugin` | Tao mot file `.jar` duy nhat chua moi dependency, de upload artifact va de chay tren may giao vien. |
| Workflow trigger ca `push` va `pull_request` | Chan loi som ngay khi mo PR, truoc khi merge vao branch chinh. |
| `actions/setup-java@v4` voi Temurin 25 | Trung khop voi `<release>25</release>` trong pom.xml; `cache: maven` tang toc cac lan run sau. |

## 3. Cau truc thu muc

```
Bai03/
├── .github/workflows/ci.yml        # GitHub Actions workflow
├── src/
│   ├── main/
│   │   ├── java/{Main,Solution}.java
│   │   └── resources/logback.xml
│   └── test/java/SolutionTest.java
├── lib/                             # Trong - Maven tu quan ly
├── pom.xml
├── run.sh
└── README.md
```

## 4. Cach chay

### Tren may co Maven (khuyen nghi)

```bash
chmod +x run.sh
./run.sh
```

`run.sh` se chay `mvn package` roi `java -jar target/fibonacci-1.0.0.jar`.

### Chay tests rieng

```bash
mvn test
```

### Chay tay khong qua run.sh

```bash
mvn package
java -jar target/fibonacci-1.0.0.jar
```

## 5. Kiem thu CI bang cach gay loi build

De chung minh kha nang debug qua execution logs cua GitHub Actions, da test 2 kich ban:

### Kich ban A: Loi compile

Thay doi `Solution.java`, them dong sai cu phap, vi du:

```java
public static long fibonacci(long n) {
    int x = "not an int";   // <-- type error
    ...
}
```

`mvn test` se fail tai phase **compile** (truoc ca khi chay test). Trong tab
*Actions* tren GitHub, log se hien chinh xac dong va loi cua compiler.

### Kich ban B: Loi unit test

Sua mot expected value trong `SolutionTest.java`, vi du:

```java
"10, 56",   // dung phai la 55
```

`mvn test` se fail tai phase **test** voi message `expected: <56> but was: <55>`.
Phase `package` khong duoc thuc thi, va `upload-artifact` cung khong chay
(buoc nay dat sau `package`).

Sau khi xem log de hieu nguyen nhan, revert lai commit la build pass tro lai.

## 6. Yeu cau moi truong

- JDK 25
- Maven 3.9+ (neu chay `run.sh` voi Maven)
- Bash (Git Bash tren Windows)
