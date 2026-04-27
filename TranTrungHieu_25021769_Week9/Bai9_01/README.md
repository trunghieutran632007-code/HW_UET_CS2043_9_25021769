# Bài 9_01: Hướng dẫn Nâng cấp Dependency và Giải quyết Lỗi Build Maven

Tài liệu này tóm tắt các vấn đề kỹ thuật và giải pháp khi nâng cấp các thư viện (dependencies) quan trọng trong một dự án Java Maven, dựa trên nội dung bài học.

---

## 1. 5 Dấu hiệu "cũ kỹ" (Legacy Issues)

Khi thêm các dependency mới, các cấu hình cũ sẽ gây ra xung đột hoặc lỗi build:

| Hiện trạng | Vấn đề khi thêm 3 dependency mới |
| :--- | :--- |
| **Java 1.8** (`<source>1.8</source>`) | Logback 1.4.x và Hibernate 6.x bắt buộc Java 11+. Sẽ gây lỗi `UnsupportedClassVersionError` khi chạy. |
| **maven-compiler-plugin 3.1** (2013) | Quá cũ, không nhận diện được Java 17, dẫn đến lỗi biên dịch (compile fail). |
| **junit 4.10** | Đề bài yêu cầu JUnit Jupiter (JUnit 5) là công cụ duy nhất. Cần loại bỏ JUnit 4. |
| **Thiếu maven-surefire-plugin** | Bản mặc định cũ không nhận diện được `junit-jupiter-engine`. Test sẽ bị bỏ qua (skip) âm thầm. |
| **Hibernate GroupId cũ** | Từ Hibernate 6, GroupId chuyển từ `org.hibernate` sang `org.hibernate.orm`. |

---

## 2. Chi tiết các Giải pháp

### 2.1. Logback Classic 1.4.11
* **Dependencies đi kèm:** `logback-core` và `slf4j-api:2.0.x`.
* **Lưu ý:** Từ bản 1.4 trở lên, Logback chính thức bỏ hỗ trợ Java 8.
* **Cách sửa:** Nâng `maven.compiler.source` và `target` lên **17** (bản LTS an toàn).

### 2.2. Hibernate Core 6.2.0.Final
* **Thay đổi quan trọng:** * Chuyển từ namespace `javax.persistence` sang `jakarta.persistence`.
    * Đổi GroupId thành `org.hibernate.orm`.
* **Yêu cầu:** Bắt buộc Java 11+.

### 2.3. JUnit Jupiter 5.9.2
* **Kiến trúc:** Chạy trên `junit-platform`, hoàn toàn khác với JUnit 4.
* **Vấn đề Surefire:** Phải khai báo `maven-surefire-plugin` phiên bản **>= 2.22.0** (khuyên dùng **3.1.2**) để thực thi được test. Nếu không, build sẽ báo SUCCESS nhưng thực tế không có test nào được chạy.

### 2.4. Chuỗi Domino giữa các Fix
Khi nâng cấp Java lên 17, bạn **bắt buộc** phải nâng cấp `maven-compiler-plugin` lên phiên bản mới (ví dụ **3.11.0**) vì các bản cũ (như 3.1) không hiểu được các cờ (flags) của Java 17.

### 2.5. Xử lý Xung đột Logging
* **Logback** yêu cầu `slf4j-api 2.0.x`.
* **Hibernate** sử dụng `jboss-logging` (có thể bridge sang SLF4J).
* **Kết quả:** Maven sử dụng quy tắc "nearest definition wins", chọn `slf4j-api 2.0.x`. Hibernate tương thích tốt với bản này nên không gây xung đột.

---

## 3. Tổng kết: 6 Thay đổi trong `pom.xml`

| Vị trí | Trước | Sau | Lý do |
| :--- | :--- | :--- | :--- |
| **properties** | `<source>1.8</source>` | `<source>17</source>` | Đáp ứng yêu cầu Java 11+ của Logback/Hibernate. |
| **properties** | `<target>1.8</target>` | `<target>17</target>` | Đồng bộ với source. |
| **dependencies** | `junit:junit:4.10` | Thay bằng `org.junit.jupiter:junit-jupiter:5.9.2` | Dùng chuẩn JUnit 5 theo yêu cầu. |
| **dependencies** | (Không có) | `ch.qos.logback:logback-classic:1.4.11` | Thêm thư viện logging mới. |
| **dependencies** | (Không có) | `org.hibernate.orm:hibernate-core:6.2.0.Final` | Thêm Hibernate 6 (chú ý GroupId mới). |
| **plugins** | `maven-compiler-plugin:3.1` | Nâng lên **3.11.0** & thêm **maven-surefire-plugin:3.1.2** | Hỗ trợ biên dịch Java 17 và thực thi test Jupiter. |

---
*Tài liệu được trích xuất từ file Bài 9_01.pdf*
