# OOP_Project

Tên dự án: Xây dựng ứng dụng quản lý khu chung cư

## 1. Members (Thành viên dự án)

Nguyễn Anh Tú -23010645

Đinh Văn Bốn -22010067

## 2. Yêu cầu chính

Giao diện: Java Console Application (có thể mở rộng sang Spring Boot trong tương lai).

Mô tả: Hệ thống quản lý khu chung cư bao gồm các nhóm chức năng chính như: quản lý cư dân, quản lý căn hộ, quản lý tài khoản, quản lý ban quản lý, và lưu trữ dữ liệu.

Đối tượng sử dụng: Ban quản lý tòa nhà.

## 3. Mô tả cụ thể

Quản lý tài khoản:

Đăng nhập, đăng xuất.

Mã hóa mật khẩu SHA-256.

Phân quyền tài khoản (admin, nhân viên, cư dân).

Quản lý cư dân:

Thêm, sửa, xóa cư dân.

Liệt kê danh sách cư dân.

Tìm kiếm cư dân theo mã/ tên.

Quản lý căn hộ:

Thêm, sửa, xóa căn hộ.

Quản lý tình trạng căn hộ (đã bán, còn trống, đang thuê).

Tìm kiếm căn hộ theo mã.

Quản lý ban quản lý:

Thêm, sửa, xóa nhân viên ban quản lý.

Liệt kê danh sách nhân sự ban quản lý.

Quản lý dữ liệu:

Đọc/ghi dữ liệu bằng file nhị phân (.dat).

Sử dụng Collection (ArrayList, HashMap) để lưu trữ dữ liệu trong bộ nhớ.

## 4. Objects (Đối tượng)
4.1. Cư dân (CuDan)
Attribute (Thuộc tính)
- String maCuDan
- String hoTen
- String ngaySinh
- String sdt
- String maCanHo

Methods (Phương thức)
- CuDan()
- set(), get() cho từng thuộc tính
- editCuDan()
- removeCuDan()
- display()

4.2. Căn hộ (CanHo)
Attribute (Thuộc tính)
- String maCanHo
- double dienTich
- int soPhongNgu
- String tinhTrang

Methods (Phương thức)
- CanHo()
- set(), get() cho từng thuộc tính
- updateTinhTrang()
- display()

4.3. Tài khoản (TaiKhoan)
Attribute (Thuộc tính)
- String username
- String passwordHash
- String role

Methods (Phương thức)
- TaiKhoan()
- login()
- logout()
- changePassword()

4.4. Ban quản lý (BanQuanLy)
Attribute (Thuộc tính)
- String maNV
- String hoTen
- String chucVu
- String sdt

Methods (Phương thức)
- BanQuanLy()
- set(), get() cho từng thuộc tính
- editNhanVien()
- removeNhanVien()
- display()
##5. Sơ đồ UML
5.1 UML Class Diagram

(ví dụ: vẽ bằng draw.io hoặc PlantUML và chèn ảnh)

<img width="1200" alt="uml_class" src="https://github.com/user-attachments/assets/xxxxxxx" />
5.2 UML Sequence Diagram
5.2.1 Chức năng thêm cư dân
<img width="1200" alt="uml_add" src="https://github.com/user-attachments/assets/yyyyyyy" />
5.2.2 Chức năng xóa căn hộ
<img width="1200" alt="uml_delete" src="https://github.com/user-attachments/assets/zzzzzzz" />
5.2.3 Chức năng đăng nhập tài khoản
<img width="1200" alt="uml_login" src="https://github.com/user-attachments/assets/wwwwwww" />
