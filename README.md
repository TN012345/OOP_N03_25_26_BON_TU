# QuanLyKhuChungCu - Demo project

Mô tả: dự án Spring Boot minh họa quản lý khu chung cư theo yêu cầu.

## Các tính năng sẵn có
- 4 đối tượng: Apartment, Resident, ServiceItem, Bill
- CRUD cho Apartment (Thymeleaf demo). Tương tự có thể tạo controller + template cho các đối tượng khác.
- JPA, MySQL, Thymeleaf

## Hướng dẫn chạy
1. Cài Java 17, Maven, MySQL.
2. Tạo database MySQL tên `quanly`.
3. Cập nhật `src/main/resources/application.properties` với username/password.
4. Tại thư mục dự án chạy:
```bash
mvn spring-boot:run
```
5. Mở `http://localhost:8080/` để truy cập.

## UML (PlantUML files)
- `uml/structural.puml` chứa sơ đồ lớp.
- `uml/crud_sequences.puml` chứa ví dụ sequence.

## Gợi ý nộp bài
- Đóng góp, phân chia công việc: ghi ở trang đầu báo cáo.
- Tạo repo trên GitHub, push code, nêu link demo (nếu deploy).
