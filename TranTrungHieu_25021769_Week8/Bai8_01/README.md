# Bài 1 - The Smell Hunter

## 1. Phân tích code smell và đề xuất refactor

### Đoạn A - `calculateFee`
- **Smells:**
  - *Mysterious Name*: `t`, `h`, `r`, `m`, `f` không nói lên ý nghĩa.
  - *Magic Number*: `0.9` không rõ là tỷ lệ gì.
  - *Unused Parameter*: `String t` không được sử dụng trong method.
- **Tại sao là vấn đề:** Người đọc phải đoán ý nghĩa tham số; khi thay đổi
  business rule (ví dụ đổi discount từ 10% sang 15%) phải lục lại literal
  `0.9` trong code thay vì sửa một hằng số duy nhất.
- **Refactor:** *Rename Variable*, *Extract Constant* (`MEMBER_DISCOUNT_RATE`),
  *Remove Parameter* với `t`.

### Đoạn B - `UserService`
- **Smell:** *Large Class / God Class*, vi phạm *Single Responsibility Principle*.
  Class này gánh 4 trách nhiệm khác nhau: persistence (`findById`), email
  (`sendWelcomeEmail`, `sendPasswordResetEmail`), UI (`renderUserProfile`),
  file export (`exportUserToCsv`).
- **Tại sao là vấn đề:** Mỗi trách nhiệm thay đổi vì một lý do khác nhau —
  sửa template email không nên đụng chạm UI rendering. Unit test cũng khó
  viết vì phải mock nhiều dependency không liên quan.
- **Refactor:** *Extract Class* thành `UserRepository`, `EmailService`,
  `UserProfileRenderer`, `UserCsvExporter`.

### Đoạn C - `getArea`
- **Smells:**
  - *Switch Statements on Type Code*: if-chain dựa trên chuỗi `"rectangle"`,
    `"triangle"`, `"circle"`.
  - *Primitive Obsession*: dùng `String` để biểu diễn loại hình, dùng `double a`,
    `double b` cho cả cạnh lẫn bán kính (nhập nhèm).
  - *Magic Number*: `0.5`, `3.14159`.
  - Trả `-1` để báo lỗi là anti-pattern (error code thay vì exception).
- **Tại sao là vấn đề:** Thêm hình mới (hình thang, ellipse...) phải sửa
  method này — vi phạm *Open/Closed Principle*. Client có thể truyền
  `shapeType` viết sai chính tả và chỉ biết khi run-time.
- **Refactor:** *Replace Conditional with Polymorphism* — abstract class
  `Shape` với các subclass `Rectangle`, `Triangle`, `Circle`. Dùng `Math.PI`
  thay cho `3.14159`.

### Đoạn D - `Report`
- **Smell:** *Data Clumps*. Bốn field `authorName`, `authorEmail`, `authorPhone`,
  `authorAddress` luôn đi cùng nhau — đây là dấu hiệu kinh điển cho thấy
  có một khái niệm (`Author`) chưa được đặt tên trong hệ thống.
- **Tại sao là vấn đề:** Khi thêm field mới cho tác giả (ví dụ `authorDob`)
  phải sửa cả class `Report`, và mọi nơi tạo `Report` đều phải truyền 4 tham
  số `String` liên tiếp dễ bị nhầm thứ tự.
- **Refactor:** *Extract Class* thành `Author`, `Report` chỉ giữ một field
  `Author author`.

## 2. Cách chạy

```bash
./run.sh
```

Script biên dịch toàn bộ `src/*.java` vào `build/` rồi chạy class `Main`,
yêu cầu môi trường JDK 25.

## 3. Cấu trúc thư mục

```
Bai01/
├── src/
│   ├── Main.java                 # entry point, demo cả 4 section
│   ├── FeeCalculator.java        # Section A
│   ├── User.java                 # Section B - entity
│   ├── UserRepository.java       # Section B
│   ├── EmailService.java         # Section B
│   ├── UserProfileRenderer.java  # Section B
│   ├── UserCsvExporter.java      # Section B
│   ├── Shape.java                # Section C - abstract
│   ├── Rectangle.java            # Section C
│   ├── Triangle.java             # Section C
│   ├── Circle.java               # Section C
│   ├── Author.java               # Section D
│   └── Report.java               # Section D
├── lib/                          # rỗng, chưa có dependency
└── run.sh
```