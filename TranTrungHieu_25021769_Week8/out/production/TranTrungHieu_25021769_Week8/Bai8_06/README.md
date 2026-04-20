# Phân Tích & Tái Cấu Trúc Mã Nguồn (Refactoring)

## 1. Vấn đề hiện tại
Class `Student Manager` đang đảm nhiệm quá nhiều công việc cùng một lúc, bao gồm:
* Lưu trữ thông tin sinh viên.
* Lưu trữ thông tin học phần.
* Phương thức tính điểm học phần.
* Phương thức xét loại.
* Phương thức in ra màn hình.

## 3. Giải pháp
Để giải quyết bài toán trên và tối ưu hóa tính kế thừa trong OOP:
* Tạo abstract class `SchoolMember`.
* Các class `Student` và `Teaching Assistant` sẽ kế thừa (extends) từ class đó.