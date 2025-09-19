# OOP_Project

Tên dự án: Xây dựng ứng dụng quản lý khu chung cư

## 1. Thành viên dự án

Nguyễn Anh Tú - 23010645

Đinh Văn Bốn - 22010067

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
  
## 5. Sơ đồ UML

5.1 UML Class Diagram
Sơ đồ lớp thể hiện mối quan hệ giữa các đối tượng trong hệ thống quản lý khu chung cư.

```plantuml
@startuml

class CuDan {
  - maCuDan: String
  - hoTen: String
  - ngaySinh: String
  - sdt: String
  - maCanHo: String
  + get/set
  + display(): void
}

class CanHo {
  - maCanHo: String
  - dienTich: double
  - soPhongNgu: int
  - tinhTrang: String
  + get/set
  + updateTinhTrang(): void
  + display(): void
}

class TaiKhoan {
  - username: String
  - passwordHash: String
  - role: String
  + login(): boolean
  + logout(): void
  + changePassword(): void
}

class BanQuanLy {
  - maNV: String
  - hoTen: String
  - chucVu: String
  - sdt: String
  + get/set
  + display(): void
}

class CuDanService {
  - danhSach: List<CuDan>
  + menu(): void
  + themCuDan(): void
  + hienThi(): void
}

class CanHoService {
  - danhSach: List<CanHo>
  + menu(): void
  + themCanHo(): void
  + timCanHoTrong(): void
}

class TaiKhoanService {
  - danhSach: List<TaiKhoan>
  + menu(): void
  + dangKy(): void
  + dangNhap(): void
}

class BQLService {
  - danhSach: List<BanQuanLy>
  + menu(): void
  + themNhanVien(): void
  + hienThi(): void
}

CuDanService "1" *-- "n" CuDan
CanHoService "1" *-- "n" CanHo
TaiKhoanService "1" *-- "n" TaiKhoan
BQLService "1" *-- "n" BanQuanLy

CuDan "n" --> "1" CanHo : sống tại
TaiKhoan "1" --> "1" CuDan : thuộc về

@enduml

5.2 UML Sequence Diagram
5.2.1 Chức năng thêm cư dân

@startuml
actor "Người dùng" as User
User -> MenuController : chọn "Thêm cư dân"
MenuController -> CuDanService : gọi themCuDan()
CuDanService -> CuDan : tạo đối tượng cư dân
CuDanService -> FileUtil : ghi xuống file cudan.dat
CuDanService --> MenuController : thông báo thành công
MenuController --> User : hiển thị kết quả
@enduml

5.2.2 Chức năng xóa căn hộ

<img width="468" height="277" alt="image" src="https://github.com/user-attachments/assets/1f3a1c32-0299-4b57-87e6-b05958ee6387" />



5.2.3 Chức năng đăng nhập tài khoản

@startuml
actor "Người dùng" as User
User -> MenuController : chọn "Đăng nhập"
MenuController -> TaiKhoanService : gọi dangNhap(user, pass)
TaiKhoanService -> PasswordUtil : mã hóa password
TaiKhoanService -> FileUtil : kiểm tra trong file taikhoan.dat
TaiKhoanService --> MenuController : kết quả (thành công/thất bại)
MenuController --> User : hiển thị kết quả
@enduml
