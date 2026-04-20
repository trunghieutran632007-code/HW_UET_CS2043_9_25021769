# Phân tích Code Smells và Refactoring

## A. Các vấn đề cơ bản (Naming, Magic Number, Unused Parameter)
### 🚨 Code Smells - Tại sao là vấn đề:
1. **Tên biến không rõ ràng**: Các biến `t`, `h`, `r`, `m`, `f` khiến người đọc khó hiểu ý nghĩa và mục đích sử dụng.
2. **Magic number**: Sử dụng số `0.9` trực tiếp trong code khiến người đọc không hiểu ý nghĩa biểu diễn của nó là gì.
3. **Parameter không được sử dụng**: Tham số `String t` truyền vào nhưng không được sử dụng, gây thừa thãi.

### 🛠 Refactor:
1. **Sửa tên biến**: Xoá hoặc đổi tên biến lại cho rõ ràng và có ý nghĩa.
2. **Tạo hằng số**: Đặt tên hằng số cho `0.9` (ví dụ: `MEMBER_DISCOUNT_RATE = 0.9;`) để dễ hiểu và dễ bảo trì.
3. **Xoá tham số thừa**: Loại bỏ `String t` khỏi định nghĩa.

---

## B. Vi phạm Single Responsibility Principle (SRP)
### 🚨 Code Smells - Tại sao là vấn đề:
* **Class chứa quá nhiều methods**: Class đảm nhận quá nhiều trách nhiệm, vi phạm nguyên tắc Đơn trách nhiệm (Single Responsibility Principle).
* **Hậu quả**: Khiến mã nguồn phức tạp, người đọc khó hiểu, khó thay đổi và bảo trì về sau.

### 🛠 Refactor:
* **Tách class (Extract Class)**: Tách class lớn đó thành 4 class riêng biệt theo từng trách nhiệm cụ thể:
  * `UserRepository`
  * `EmailService`
  * `UserProfileRenderer`
  * `UserCsvExporter`

---

## C. Lạm dụng câu lệnh điều kiện và Vi phạm Open/Closed Principle (OCP)
### 🚨 Code Smells - Tại sao là vấn đề:
1. **Lạm dụng lệnh điều kiện `if`**: Kiểm tra điều kiện loại hình học thay vì tận dụng tính đa hình. Việc này gây khó khăn khi bảo trì và thêm/bớt các loại hình học mới, vi phạm nguyên tắc Đóng/Mở (Open/Closed Principle).
2. **Magic number**: Sử dụng `0.5` và `3.14159` làm người đọc khó hiểu nguồn gốc và ý nghĩa của các con số này.

### 🛠 Refactor:
1. **Sử dụng đa hình (Polymorphism)**: Tạo Abstract class `Shape`, các loại hình cụ thể sẽ `extends` class đó và tự ghi đè các hàm tính toán, tận dụng tính đa hình.
2. **Thay thế Magic numbers**:
   * Dùng hằng số có sẵn `Math.PI` thay cho `3.14159`.
   * Tạo hằng số `HALF = 0.5` để tránh dùng số trực tiếp.

---

## D. Cụm dữ liệu (Data Clumps)
### 🚨 Code Smells - Tại sao là vấn đề:
* **Dữ liệu luôn đi cùng nhau**: Bốn field `authorName`, `authorEmail`, `authorPhone`, `authorAddress` luôn xuất hiện cùng nhau khi khởi tạo đối tượng `Report`.
* **Hậu quả**: 
  * Gây ra quá nhiều tham số khi truyền vào constructor, dễ nhầm lẫn về thứ tự tham số.
  * Khi muốn bảo trì (ví dụ: thêm field `authorDob`), sẽ rất mất thời gian để tìm và sửa ở mọi nơi khởi tạo đối tượng `Report`.

### 🛠 Refactor:
* **Tách Class (Extract Class)**: Gom các field liên quan đến tác giả vào chung 1 class tên là `Author`. Trong class `Report` lúc này chỉ cần giữ một field là `Author author`. Số lượng tham số truyền vào constructor của Report sẽ giảm đi đáng kể.
