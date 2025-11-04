-- Database Schema for Apartment Management System (Quản lý Khu Chung cư)
-- MySQL Database: resident_management

CREATE DATABASE IF NOT EXISTS resident_management;
USE resident_management;

-- Table: apartments (Căn hộ)
CREATE TABLE IF NOT EXISTS apartments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    apartment_number VARCHAR(20) NOT NULL UNIQUE,
    building VARCHAR(50) NOT NULL,
    floor INT NOT NULL,
    area DECIMAL(10, 2) NOT NULL,
    type VARCHAR(20),
    price DECIMAL(10, 2) NOT NULL,
    owner_name VARCHAR(50),
    owner_phone VARCHAR(20),
    status VARCHAR(20) DEFAULT 'Trống',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table: residents (Cư dân)
CREATE TABLE IF NOT EXISTS residents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    citizen_id VARCHAR(20) UNIQUE,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(10),
    email VARCHAR(100),
    phone_number VARCHAR(20),
    apartment_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (apartment_id) REFERENCES apartments(id) ON DELETE SET NULL
);

-- Table: management_boards (Ban quản lý)
CREATE TABLE IF NOT EXISTS management_boards (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(200),
    phone_number VARCHAR(20),
    email VARCHAR(100),
    director_name VARCHAR(50),
    director_phone VARCHAR(20),
    established_date DATE NOT NULL,
    description VARCHAR(500),
    status VARCHAR(20) DEFAULT 'Hoạt động',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table: accounts (Tài khoản)
CREATE TABLE IF NOT EXISTS accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    phone_number VARCHAR(20),
    role VARCHAR(50),
    status VARCHAR(20) DEFAULT 'Active',
    resident_id BIGINT,
    management_board_id BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_login_at TIMESTAMP NULL,
    FOREIGN KEY (resident_id) REFERENCES residents(id) ON DELETE SET NULL,
    FOREIGN KEY (management_board_id) REFERENCES management_boards(id) ON DELETE SET NULL
);

-- Sample data
INSERT INTO apartments (apartment_number, building, floor, area, type, price, owner_name, owner_phone, status) VALUES
('A101', 'Tòa A', 1, 75.5, '2PN', 2500000000.00, 'Nguyễn Văn A', '0901234567', 'Đã bán'),
('A202', 'Tòa A', 2, 90.0, '3PN', 3200000000.00, 'Trần Thị B', '0907654321', 'Đã bán'),
('B101', 'Tòa B', 1, 60.0, '1PN', 1800000000.00, NULL, NULL, 'Trống');

INSERT INTO residents (full_name, citizen_id, date_of_birth, gender, email, phone_number, apartment_id) VALUES
('Nguyễn Văn A', '001234567890', '1980-01-15', 'Nam', 'nguyenvana@email.com', '0901234567', 1),
('Trần Thị B', '001234567891', '1985-05-20', 'Nữ', 'tranthib@email.com', '0907654321', 2);

INSERT INTO management_boards (name, address, phone_number, email, director_name, director_phone, established_date, status) VALUES
('Ban Quản lý Khu Chung cư ABC', '123 Đường ABC, Phường XYZ', '0281234567', 'bql@chungcuabc.com', 'Lê Văn C', '0901111111', '2020-01-01', 'Hoạt động');

INSERT INTO accounts (username, password, email, role, status, resident_id, management_board_id) VALUES
('admin', 'admin123', 'admin@chungcuabc.com', 'Admin', 'Active', NULL, 1),
('resident1', 'resident123', 'nguyenvana@email.com', 'Resident', 'Active', 1, NULL);