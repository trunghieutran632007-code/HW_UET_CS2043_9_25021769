# Bai 5: Test Coverage & Quality Enforcement (JaCoCo)

## Y tuong chinh
Su dung **JaCoCo** (Java Code Coverage) de do luong code coverage cua unit tests,
dam bao toi thieu **80%** line coverage va branch coverage.

## Cau truc project
```
Bai05/
├── pom.xml                          # Maven config + JaCoCo plugin
├── src/
│   ├── main/java/com/example/
│   │   ├── Calculator.java          # Class chinh voi cac phep tinh
│   │   └── Main.java                # Entry point
│   └── test/java/com/example/
│       └── CalculatorTest.java      # Unit tests (JUnit 5)
├── .github/workflows/
│   └── ci.yml                       # GitHub Actions workflow
├── run.sh                           # Script chay mvn verify
└── README.md
```

## Cach chay
```bash
chmod +x run.sh
./run.sh
```

Hoac truc tiep:
```bash
mvn verify
```

## Xem bao cao coverage
Sau khi chay, mo file: `target/site/jacoco/index.html` trong trinh duyet.

## Huong tiep can
- **JaCoCo plugin** duoc cau hinh voi 3 execution:
  1. `prepare-agent`: Gan agent vao JVM de thu thap coverage data
  2. `report`: Tao bao cao HTML/XML
  3. `check`: Strict rule - fail build neu LINE hoac BRANCH coverage < 80%
- **GitHub Actions**: Chay `mvn verify` trong CI, upload bao cao lam artifact
