# Phân tích thiết kế hệ thống tính phí giao hàng

1. Nếu cần thêm loại hàng mới, ta cần sửa code ở 2 methods, đó là if else trong `getDeliveryFee()` và if trong `getLabel()`. Điều đó sẽ khiến ta phải sửa class `Order` nhiều lần, vi phạm quy tắc OCP.

2. So sánh cách mới và cách cũ:

| Tiêu chí | Thiết kế cũ (if/else) | Thiết kế mới (Đa hình) |
| :--- | :--- | :--- |
| **Vị trí cần can thiệp** | Sửa trực tiếp vào 2 phương thức bên trong class `Order` gốc. | Tạo class mới kế thừa `Order`. Không chạm vào code cũ. |
| **Rủi ro gây lỗi (Bug)** | Cao. Có thể vô tình làm hỏng logic của nhánh `STANDARD`, `EXPRESS` cũ. | Rất thấp. Logic mới được cô lập hoàn toàn trong class `BulkyOrder`. |
| **Khả năng mở rộng** | Kém. File code càng ngày càng dài và rối rắm (Code smell). | Tốt. Tuân thủ chuẩn Open-Closed Principle, dễ dàng chia việc làm nhóm. |
