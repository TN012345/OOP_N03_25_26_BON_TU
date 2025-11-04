const API_URL = '/api/residents';
let apartments = [];

// Load apartments for dropdown
async function loadApartments() {
    try {
        const response = await fetch('/api/apartments');
        const data = await response.json();
        apartments = data;
        const select = document.getElementById('apartmentId');
        select.innerHTML = '<option value="">Chọn căn hộ</option>';
        data.forEach(apartment => {
            const option = document.createElement('option');
            option.value = apartment.id;
            option.textContent = `${apartment.apartmentNumber || ''} - Tòa ${apartment.building || ''} - Tầng ${apartment.floor || ''}`;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading apartments:', error);
    }
}

// Show alert
function showAlert(message, type = 'success') {
    const alertContainer = document.getElementById('alertContainer');
    alertContainer.innerHTML = `
        <div class="alert alert-${type} alert-dismissible fade show" role="alert">
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    `;
    setTimeout(() => {
        const alert = alertContainer.querySelector('.alert');
        if (alert) {
            const bsAlert = new bootstrap.Alert(alert);
            bsAlert.close();
        }
    }, 5000);
}

// Load residents
async function loadResidents() {
    try {
        const response = await fetch(API_URL);
        const data = await response.json();
        const tbody = document.getElementById('residentTableBody');
        
        if (data.length === 0) {
            tbody.innerHTML = '<tr><td colspan="9" class="text-center">Không có dữ liệu</td></tr>';
            return;
        }
        
        tbody.innerHTML = data.map(resident => `
            <tr>
                <td>${resident.id}</td>
                <td>${resident.fullName || ''}</td>
                <td>${resident.citizenId || ''}</td>
                <td>${resident.dateOfBirth || ''}</td>
                <td>${resident.gender || ''}</td>
                <td>${resident.email || ''}</td>
                <td>${resident.phoneNumber || ''}</td>
                <td>${resident.apartment ? `${resident.apartment.apartmentNumber} - Tòa ${resident.apartment.building}` : ''}</td>
                <td>
                    <button class="btn btn-sm btn-warning" onclick="editResident(${resident.id})">Sửa</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteResident(${resident.id})">Xóa</button>
                    <button class="btn btn-sm btn-info" onclick="viewResident(${resident.id})">Chi tiết</button>
                </td>
            </tr>
        `).join('');
    } catch (error) {
        console.error('Error loading residents:', error);
        showAlert('Lỗi khi tải danh sách cư dân: ' + error.message, 'danger');
    }
}

// Edit resident
async function editResident(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const data = await response.json();
        
        document.getElementById('residentId').value = data.id;
        document.getElementById('fullName').value = data.fullName || '';
        document.getElementById('citizenId').value = data.citizenId || '';
        document.getElementById('dateOfBirth').value = data.dateOfBirth || '';
        document.getElementById('gender').value = data.gender || '';
        document.getElementById('email').value = data.email || '';
        document.getElementById('phoneNumber').value = data.phoneNumber || '';
        document.getElementById('apartmentId').value = data.apartment ? data.apartment.id : '';
        document.getElementById('formTitle').textContent = 'Chỉnh sửa Cư dân';
        
        document.getElementById('residentForm').scrollIntoView({ behavior: 'smooth' });
    } catch (error) {
        console.error('Error loading resident:', error);
        showAlert('Lỗi khi tải thông tin cư dân: ' + error.message, 'danger');
    }
}

// View resident
async function viewResident(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const data = await response.json();
        
        const info = `
            <strong>ID:</strong> ${data.id}<br>
            <strong>Họ và tên:</strong> ${data.fullName || ''}<br>
            <strong>CMND/CCCD:</strong> ${data.citizenId || ''}<br>
            <strong>Ngày sinh:</strong> ${data.dateOfBirth || ''}<br>
            <strong>Giới tính:</strong> ${data.gender || ''}<br>
            <strong>Email:</strong> ${data.email || ''}<br>
            <strong>SĐT:</strong> ${data.phoneNumber || ''}<br>
            <strong>Hộ gia đình:</strong> ${data.apartment ? `${data.apartment.apartmentNumber} - Tòa ${data.apartment.building}` : ''}
        `;
        
        alert(info);
    } catch (error) {
        console.error('Error loading resident:', error);
        showAlert('Lỗi khi xem chi tiết: ' + error.message, 'danger');
    }
}

// Delete resident
async function deleteResident(id) {
    if (!confirm('Bạn có chắc chắn muốn xóa cư dân này?')) {
        return;
    }
    
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });
        
        const data = await response.json();
        
        if (response.ok) {
            showAlert(data.message || 'Xóa cư dân thành công');
            loadResidents();
        } else {
            showAlert(data.error || 'Lỗi khi xóa cư dân', 'danger');
        }
    } catch (error) {
        console.error('Error deleting resident:', error);
        showAlert('Lỗi khi xóa cư dân: ' + error.message, 'danger');
    }
}

// Form submit
document.getElementById('residentForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('residentId').value;
    const apartmentId = document.getElementById('apartmentId').value;
    
    const residentData = {
        fullName: document.getElementById('fullName').value,
        citizenId: document.getElementById('citizenId').value,
        dateOfBirth: document.getElementById('dateOfBirth').value,
        gender: document.getElementById('gender').value,
        email: document.getElementById('email').value,
        phoneNumber: document.getElementById('phoneNumber').value
    };
    
    if (apartmentId) {
        residentData.apartment = { id: parseInt(apartmentId) };
    }
    
    try {
        const url = id ? `${API_URL}/${id}` : API_URL;
        const method = id ? 'PUT' : 'POST';
        
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(residentData)
        });
        
        const data = await response.json();
        
        if (response.ok || response.status === 201) {
            showAlert(data.message || (id ? 'Cập nhật thành công' : 'Thêm mới thành công'));
            document.getElementById('residentForm').reset();
            document.getElementById('residentId').value = '';
            document.getElementById('formTitle').textContent = 'Thêm Cư dân Mới';
            loadResidents();
        } else {
            showAlert(data.error || 'Lỗi khi lưu dữ liệu', 'danger');
        }
    } catch (error) {
        console.error('Error saving resident:', error);
        showAlert('Lỗi khi lưu dữ liệu: ' + error.message, 'danger');
    }
});

// Reset form
document.getElementById('resetBtn').addEventListener('click', () => {
    document.getElementById('residentForm').reset();
    document.getElementById('residentId').value = '';
    document.getElementById('formTitle').textContent = 'Thêm Cư dân Mới';
});

// Initialize
loadApartments();
loadResidents();
