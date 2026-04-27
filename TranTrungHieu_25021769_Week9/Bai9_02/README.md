# Bài 2 — Code Quality với Checkstyle

Tái cấu trúc dự án `BankSystem` để áp dụng **Google Java Style** thông qua
**Maven Checkstyle Plugin** và bổ sung **observability** bằng **SLF4J + Logback**.

---

## 1. Tóm tắt ý tưởng

Dự án gốc có rất nhiều lỗi: từ **lỗi syntax thật** (`Transaction.java` có dòng
`/ Vi phạm:` chỉ 1 dấu `/` → không compile được), `package bank_system;` chỉ ở
một file, đến hàng loạt vi phạm style (naming xấu, magic numbers, wildcard
imports, `catch (Exception e)`, `System.out.println` debug, cộng chuỗi trong
loop, thiếu Javadoc...).

Hướng giải quyết gồm 3 mảng:

1. **Tích hợp Maven Checkstyle Plugin** (`pom.xml`) bind vào phase `validate`
   với `failsOnError=true`. Ruleset đặt trong `checkstyle.xml` — phiên bản đã
   tinh chỉnh của Google Java Style để phù hợp dự án nhỏ (vẫn giữ các check
   quan trọng: naming, indent, line length, braces, imports, whitespace,
   modifier order, magic numbers).

2. **Refactor toàn bộ `src/`** để pass `mvn checkstyle:check`:
   sửa lỗi syntax, đổi tên (`_accNum`→`accountNumber`, `B`→`balance`,
   `c_list`→`customerList`, `get_type_string`→`getTypeString`...), đưa
   magic numbers (`1000.0`, `5000.0`, `3`, `4`) vào hằng số có Javadoc, thay
   `String += ...` bằng `StringBuilder`, thay anonymous `Comparator` bằng
   `Comparator.comparing(...)`, tách method dài (`Bank.readCustomerList` từ
   nested-if 5 tầng → 4 method nhỏ), thay `catch (Exception e)` bằng exception
   cụ thể, gom toàn bộ về default package.

3. **Logging có cấu trúc** thay cho `System.out.println`/`System.err.println`:
   dùng SLF4J facade + Logback backend. Phân tầng level rõ ràng (xem mục 4),
   ghi cả `CONSOLE` (cho người vận hành) và `FILE` rolling theo ngày
   (`logs/bank-system.%d.log`, giữ 7 ngày — phục vụ audit ngắn hạn).

---

## 2. Lý do lựa chọn hướng tiếp cận

### 2.1. Tại sao Google Java Style?

Đề cho phép chọn Google Java Style hoặc Sun Code Conventions. Chọn **Google**
vì: (a) còn được maintain (Sun Conventions không cập nhật từ 1999),
(b) Maven Checkstyle Plugin đi kèm sẵn `google_checks.xml` làm baseline,
(c) Google rules nghiêm hơn (2-space indent, line 100, javadoc bắt buộc trên
public API) → buộc code phải tốt thật sự.

### 2.2. Tại sao SLF4J + Logback (không dùng `java.util.logging`)?

| Tiêu chí | `j.u.l` | SLF4J + Logback |
|---|---|---|
| Tách facade khỏi backend | Không | **Có** — dễ thay backend |
| Parameterized logging (`{}`) | Không | **Có** — không phải `+` chuỗi |
| Rolling file appender | Phức tạp | **Có sẵn** |
| MDC, async appender | Không | **Có** |
| Cộng đồng / tài liệu | Ít | Nhiều |

Parameterized logging quan trọng vì: `LOG.debug("attempt: {}", amount)` chỉ
build chuỗi khi DEBUG đang bật → tiết kiệm CPU khi log level thấp ở production.

### 2.3. Tại sao giữ default package?

File gốc inconsistent: chỉ `Account.java` có `package bank_system;`. Submission
guide yêu cầu `java -cp build Main` → `Main` phải ở default package. Để toàn
bộ về default package vừa fix được inconsistency, vừa khớp với `run.sh` mẫu.

### 2.4. Tại sao có `pom.xml` mà `run.sh` không gọi `mvn`?

Đề yêu cầu Maven Checkstyle Plugin (cần `pom.xml`), guide submission yêu cầu
`run.sh` chạy được standalone với `javac`. Hai yêu cầu coexist được: `pom.xml`
phục vụ `mvn checkstyle:check`, `run.sh` chỉ dùng `javac` để biên dịch — máy
thầy không cần Maven vẫn chạy được.

### 2.5. Cấu trúc lib/ và auto-download

`run.sh` tự download 3 JAR (`slf4j-api`, `logback-core`, `logback-classic`) về
`lib/` lần chạy đầu, sau đó cache lại. Tránh: (a) nhồi 3 JAR vào file zip nộp
bài (mỗi tuần lại nặng vô lý), (b) lệ thuộc vào Maven local repo của thầy.

---

## 3. Cách chạy

### Yêu cầu môi trường

* JDK 25 (theo đề; code không dùng tính năng đặc thù JDK 25 nên JDK 21+ chạy
  được)
* `curl` hoặc `wget`
* Internet (cho lần chạy đầu tiên — để download deps về `lib/`)

### Chạy chương trình

```bash
chmod +x run.sh
./run.sh
```

Quy trình bên trong `run.sh`:

1. Download `slf4j-api`, `logback-core`, `logback-classic` về `lib/` nếu thiếu.
2. `javac -d build -cp "build:lib/*" src/*.java`
3. Sao chép `src/logback.xml` vào `build/` (để Logback đọc được cấu hình).
4. `java -cp "build:lib/*" Main`

### Verify Checkstyle (không bắt buộc khi chấm)

```bash
mvn checkstyle:check
```

Kỳ vọng: `BUILD SUCCESS` — toàn bộ source pass ruleset trong `checkstyle.xml`.

### Output mẫu

Khi chạy `./run.sh`, console sẽ in lần lượt:

* Log INFO: bắt đầu/kết thúc đọc customer list, các giao dịch thành công.
* Log WARN: 4 giao dịch bị reject (số tiền âm, vượt MAX_WITHDRAW, không đủ số
  dư, vi phạm MIN_BALANCE).
* Log DEBUG: parsed customer, attempt deposit (chỉ hiển thị do
  `logback.xml` đặt level DEBUG cho package ứng dụng).
* Danh sách khách hàng theo CMND và theo họ tên.
* Lịch sử giao dịch tài khoản đầu tiên.

File log đầy đủ ghi vào `logs/bank-system.log`.

---

## 4. Chiến lược logging (chi tiết phần đề bài hỏi)

### 4.1. Cấp độ log

| Level | Khi nào dùng | Ví dụ trong code |
|---|---|---|
| `ERROR` | Lỗi không tự phục hồi được, cần can thiệp | `Bank.readCustomerList` khi gặp `IOException` |
| `WARN` | Vi phạm nghiệp vụ — app vẫn chạy bình thường | `*Account` reject khi số tiền âm / không đủ số dư |
| `INFO` | Sự kiện audit, mốc nghiệp vụ | Bắt đầu/kết thúc đọc data, giao dịch thành công |
| `DEBUG` | Chi tiết flow — chỉ bật khi điều tra | `Bank.parseCustomer`, `SavingsAccount.deposit` attempt |
| `TRACE` | Vết rất chi tiết — production thường tắt | `Transaction.getTransactionSummary` build chuỗi |

### 4.2. Lý do chọn từng điểm dữ liệu để log

* **`accountNumber`, `amount`** ở mọi mức INFO/WARN: khóa truy vết — không có
  hai trường này thì log vô dụng cho audit.
* **`balance` sau giao dịch** ở mức INFO: kiểm tra tính toàn vẹn — đối chiếu
  được với DB nếu nghi ngờ lỗi.
* **`customer.idNumber`, `customer.fullName`** ở mức DEBUG: chỉ cần khi debug
  parser — production không nên log PII ở mức cao hơn.
* **Số khách hàng/tài khoản đã đọc** ở INFO: smoke test sau import — phát hiện
  ngay nếu file input bị cắt.
* **Stack trace**: chỉ truyền `Throwable` vào `LOG.error(msg, ex)` cho lỗi thật
  sự, không log stack trace cho WARN nghiệp vụ (gây nhiễu).

### 4.3. Cấu hình `logback.xml`

* `CONSOLE` appender: pattern ngắn (`HH:mm:ss.SSS`), dùng khi chạy
  `./run.sh` để thầy thấy ngay output.
* `FILE` appender: rolling theo ngày (`bank-system.%d{yyyy-MM-dd}.log`), giữ
  7 ngày — đủ cho audit ngắn hạn của bài tập.
* Logger riêng cho `Account`, `Bank`, `*Account` ở mức `DEBUG` để demo show
  được DEBUG; `Transaction`/`Main` ở `INFO` để không spam.
* Root ở `INFO` — nguyên tắc least surprise: ai đọc log lần đầu không bị ngợp.

---

## 5. Cấu trúc thư mục

```
Bai02/
├── pom.xml                  # Maven config (Checkstyle Plugin + deps)
├── checkstyle.xml           # Ruleset Google Java Style đã tinh chỉnh
├── README.md                # File này
├── run.sh                   # Compile + run theo guide submission
├── lib/                     # JAR phụ thuộc (tự download)
└── src/
    ├── Main.java            # Entry point, demo các kịch bản
    ├── Account.java         # Abstract base
    ├── CheckingAccount.java
    ├── SavingsAccount.java
    ├── Customer.java
    ├── Bank.java            # Quản lý customers, parse input
    ├── Transaction.java
    ├── BankException.java   # Hierarchy exception
    ├── InsufficientFundsException.java
    ├── InvalidFundingAmountException.java
    └── logback.xml          # Cấu hình appender / level / pattern
```
