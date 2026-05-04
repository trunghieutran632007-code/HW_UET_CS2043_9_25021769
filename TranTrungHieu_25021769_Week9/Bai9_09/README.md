# Bai 9: Trien khai Logging chuyen nghiep

## Y tuong chinh

Mo phong he thong xu ly giao dich ngan hang da luong, su dung
**SLF4J + Logback** thay cho `System.out.println()`. Cac diem chinh:

- **0 dong** `System.out.println` trong toan bo source code.
- **Parameterized logging** (`{}`) o moi cho — khong cong chuoi.
- **Hai muc log**:
  - `INFO` cho cac moc quan trong: khoi dong, tao thread pool, bat dau / hoan tat moi giao dich, tong ket.
  - `ERROR` cho exception — kem stack trace bang cach truyen `Throwable` lam doi so cuoi cung.
- **Ghi ra ca console va tep**: cau hinh `ConsoleAppender` + `FileAppender` trong `src/logback.xml`. Tep log nam o `logs/app.log`.

## Tai sao chon huong nay

| So sanh | `System.out.println` | SLF4J + Logback |
|---|---|---|
| Tat / bat theo muc do | Khong | Co (`<root level="...">`) |
| Co thoi gian, ten thread, ten class | Phai tu them | Tu dong qua pattern |
| Ghi ra nhieu noi cung luc | Phai viet code | Cau hinh `<appender>` |
| Hieu suat khi log bi tat | Van phai goi method | `{}` chi format khi can |
| Tien chuan industry | Khong | Co |

Parameterized logging (`log.info("a={}, b={}", a, b)`) co loi the lon so voi noi
chuoi (`log.info("a=" + a + ", b=" + b)`):
- Khong tao chuoi tam khi log level bi tat (vd. `DEBUG` bi tat thi
  args khong duoc `toString()`).
- Format chi xay ra khi message thuc su duoc ghi ra.

## Cau truc thu muc

```
Bai9_09/
├── src/
│   ├── Main.java          # Entry point
│   ├── BankAccount.java   # Tai khoan thread-safe
│   ├── TransferTask.java  # Callable<Boolean> chuyen khoan
│   └── logback.xml        # Cau hinh logger
├── lib/                   # Chua 3 JAR (tu tai ve, xem ben duoi)
├── run.sh                 # Bien dich + chay
└── README.md
```

## Thu vien can tai vao `lib/`

Tai 3 file JAR sau va dat truc tiep vao thu muc `lib/`:

| File JAR | Phien ban | Link tai |
|---|---|---|
| `slf4j-api-2.0.16.jar` | 2.0.16 | https://repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.16/slf4j-api-2.0.16.jar |
| `logback-core-1.5.12.jar` | 1.5.12 | https://repo1.maven.org/maven2/ch/qos/logback/logback-core/1.5.12/logback-core-1.5.12.jar |
| `logback-classic-1.5.12.jar` | 1.5.12 | https://repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.5.12/logback-classic-1.5.12.jar |

Tren Linux/Mac/Git Bash co the tai nhanh:

```bash
cd lib/
curl -O https://repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.16/slf4j-api-2.0.16.jar
curl -O https://repo1.maven.org/maven2/ch/qos/logback/logback-core/1.5.12/logback-core-1.5.12.jar
curl -O https://repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.5.12/logback-classic-1.5.12.jar
cd ..
```

Sau khi tai:

```
lib/
├── logback-classic-1.5.12.jar
├── logback-core-1.5.12.jar
└── slf4j-api-2.0.16.jar
```

> **Quan trong:** khi nop bai phai zip ca thu muc `lib/` kem JAR vao file zip,
> de may giao vien khong can tai lai.

## Cach chay

```bash
chmod +x run.sh   # neu chua co quyen thuc thi
./run.sh
```

Khi chay xong se thay:
- Console hien log voi format: `2026-05-04 10:23:45.123 [pool-1-thread-1] INFO  TransferTask - [TX001] Bat dau...`
- File `logs/app.log` chua noi dung tuong tu (append moi lan chay).

## Format log mau

```
2026-05-04 10:23:45.123 [main] INFO  Main - === Khoi dong he thong xu ly giao dich ===
2026-05-04 10:23:45.145 [main] INFO  BankAccount - Khoi tao tai khoan ALICE voi so du ban dau 1000.0
2026-05-04 10:23:45.146 [main] INFO  BankAccount - Khoi tao tai khoan BOB voi so du ban dau 500.0
2026-05-04 10:23:45.150 [main] INFO  Main - Tao thread pool: corePoolSize=3, queueCapacity=10
2026-05-04 10:23:45.155 [pool-1-thread-1] INFO  TransferTask - [TX001] Bat dau chuyen 200.0 tu ALICE sang BOB
2026-05-04 10:23:45.156 [pool-1-thread-1] INFO  BankAccount - Tai khoan ALICE bi rut 200.0 -> so du moi 800.0
...
2026-05-04 10:23:45.180 [pool-1-thread-3] ERROR TransferTask - [TX005] Loi khi chuyen -10.0 tu ALICE sang CHARLIE: So tien rut phai > 0, nhan duoc: -10.0
java.lang.IllegalArgumentException: So tien rut phai > 0, nhan duoc: -10.0
    at BankAccount.withdraw(BankAccount.java:33)
    ...
```

## Ghi chu ve `mvn test`

De bai noi "chay `mvn test`" nhung huong dan nop bai bat buoc cau truc
`src/` + `lib/` + `run.sh` (khong co cho cho `pom.xml`). Bai nay tuan thu
huong dan nop bai — giao vien chay `./run.sh`. Neu can phien ban Maven
thi can hoi lai giao vien ve cau truc nop.
