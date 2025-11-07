const API_URL = '/api/apartments';

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

async function loadApartments() {
    try {
        const response = await fetch(API_URL);
        const data = await response.json();
        const tbody = document.getElementById('apartmentTableBody');
        
        if (data.length === 0) {
            tbody.innerHTML = '<tr><td colspan="10" class="text-center">Không có dữ liệu</td></tr>';
            return;
        }
        
        tbody.innerHTML = data.map(apt => `
            <tr>
                <td>${apt.id}</td>
                <td>${apt.apartmentNumber || ''}</td>
                <td>${apt.building || ''}</td>
                <td>${apt.floor || ''}</td>
                <td>${apt.area ? apt.area + ' m²' : ''}</td>
                <td>${apt.type || ''}</td>
                <td>${apt.price ? new Intl.NumberFormat('vi-VN').format(apt.price) + ' USD' : ''}</td>
                <td>${apt.ownerName || ''}</td>
                <td><span class="badge bg-${apt.status === 'Trống' ? 'warning' : apt.status === 'Đã bán' ? 'success' : 'info'}">${apt.status || ''}</span></td>
                <td>
                    <button class="btn btn-sm btn-warning" onclick="editApartment(${apt.id})">Sửa</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteApartment(${apt.id})">Xóa</button>
                </td>
            </tr>
        `).join('');
    } catch (error) {
        console.error('Error loading apartments:', error);
        showAlert('Lỗi khi tải danh sách căn hộ: ' + error.message, 'danger');
    }
}

async function editApartment(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const data = await response.json();
        
        document.getElementById('apartmentId').value = data.id;
        document.getElementById('apartmentNumber').value = data.apartmentNumber || '';
        document.getElementById('building').value = data.building || '';
        document.getElementById('floor').value = data.floor || '';
        document.getElementById('area').value = data.area || '';
        document.getElementById('type').value = data.type || '';
        document.getElementById('price').value = data.price || '';
        document.getElementById('ownerName').value = data.ownerName || '';
        document.getElementById('ownerPhone').value = data.ownerPhone || '';
        document.getElementById('status').value = data.status || 'Trống';
        document.getElementById('formTitle').textContent = 'Chỉnh sửa Căn hộ';
        
        document.getElementById('apartmentForm').scrollIntoView({ behavior: 'smooth' });
    } catch (error) {
        console.error('Error loading apartment:', error);
        showAlert('Lỗi khi tải thông tin căn hộ: ' + error.message, 'danger');
    }
}

async function deleteApartment(id) {
    if (!confirm('Bạn có chắc chắn muốn xóa căn hộ này?')) return;
    
    try {
        const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        const data = await response.json();
        
        if (response.ok) {
            showAlert(data.message || 'Xóa căn hộ thành công');
            loadApartments();
        } else {
            showAlert(data.error || 'Lỗi khi xóa căn hộ', 'danger');
        }
    } catch (error) {
        console.error('Error deleting apartment:', error);
        showAlert('Lỗi khi xóa căn hộ: ' + error.message, 'danger');
    }
}

document.getElementById('apartmentForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('apartmentId').value;
    const apartmentData = {
        apartmentNumber: document.getElementById('apartmentNumber').value,
        building: document.getElementById('building').value,
        floor: parseInt(document.getElementById('floor').value),
        area: parseFloat(document.getElementById('area').value),
        type: document.getElementById('type').value,
        price: parseFloat(document.getElementById('price').value),
        ownerName: document.getElementById('ownerName').value,
        ownerPhone: document.getElementById('ownerPhone').value,
        status: document.getElementById('status').value || 'Trống'
    };
    
    try {
        const url = id ? `${API_URL}/${id}` : API_URL;
        const method = id ? 'PUT' : 'POST';
        
        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(apartmentData)
        });
        
        const data = await response.json();
        
        if (response.ok || response.status === 201) {
            showAlert(data.message || (id ? 'Cập nhật thành công' : 'Thêm mới thành công'));
            document.getElementById('apartmentForm').reset();
            document.getElementById('apartmentId').value = '';
            document.getElementById('formTitle').textContent = 'Thêm Căn hộ Mới';
            loadApartments();
        } else {
            showAlert(data.error || 'Lỗi khi lưu dữ liệu', 'danger');
        }
    } catch (error) {
        console.error('Error saving apartment:', error);
        showAlert('Lỗi khi lưu dữ liệu: ' + error.message, 'danger');
    }
});

document.getElementById('resetBtn').addEventListener('click', () => {
    document.getElementById('apartmentForm').reset();
    document.getElementById('apartmentId').value = '';
    document.getElementById('formTitle').textContent = 'Thêm Căn hộ Mới';
});

loadApartments();
