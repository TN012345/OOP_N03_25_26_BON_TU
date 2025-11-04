const API_URL = '/api/management-boards';

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

async function loadManagementBoards() {
    try {
        const response = await fetch(API_URL);
        const data = await response.json();
        const tbody = document.getElementById('managementBoardTableBody');
        
        if (data.length === 0) {
            tbody.innerHTML = '<tr><td colspan="9" class="text-center">Không có dữ liệu</td></tr>';
            return;
        }
        
        tbody.innerHTML = data.map(board => `
            <tr>
                <td>${board.id}</td>
                <td>${board.name || ''}</td>
                <td>${board.address || ''}</td>
                <td>${board.phoneNumber || ''}</td>
                <td>${board.email || ''}</td>
                <td>${board.directorName || ''}</td>
                <td>${board.establishedDate || ''}</td>
                <td><span class="badge bg-${board.status === 'Hoạt động' ? 'success' : 'danger'}">${board.status || ''}</span></td>
                <td>
                    <button class="btn btn-sm btn-warning" onclick="editManagementBoard(${board.id})">Sửa</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteManagementBoard(${board.id})">Xóa</button>
                </td>
            </tr>
        `).join('');
    } catch (error) {
        console.error('Error loading management boards:', error);
        showAlert('Lỗi khi tải danh sách ban quản lý: ' + error.message, 'danger');
    }
}

async function editManagementBoard(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const data = await response.json();
        
        document.getElementById('managementBoardId').value = data.id;
        document.getElementById('name').value = data.name || '';
        document.getElementById('address').value = data.address || '';
        document.getElementById('phoneNumber').value = data.phoneNumber || '';
        document.getElementById('email').value = data.email || '';
        document.getElementById('directorName').value = data.directorName || '';
        document.getElementById('directorPhone').value = data.directorPhone || '';
        document.getElementById('establishedDate').value = data.establishedDate || '';
        document.getElementById('status').value = data.status || 'Hoạt động';
        document.getElementById('description').value = data.description || '';
        document.getElementById('formTitle').textContent = 'Chỉnh sửa Ban quản lý';
        
        document.getElementById('managementBoardForm').scrollIntoView({ behavior: 'smooth' });
    } catch (error) {
        console.error('Error loading management board:', error);
        showAlert('Lỗi khi tải thông tin ban quản lý: ' + error.message, 'danger');
    }
}

async function deleteManagementBoard(id) {
    if (!confirm('Bạn có chắc chắn muốn xóa ban quản lý này?')) return;
    
    try {
        const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        const data = await response.json();
        
        if (response.ok) {
            showAlert(data.message || 'Xóa ban quản lý thành công');
            loadManagementBoards();
        } else {
            showAlert(data.error || 'Lỗi khi xóa ban quản lý', 'danger');
        }
    } catch (error) {
        console.error('Error deleting management board:', error);
        showAlert('Lỗi khi xóa ban quản lý: ' + error.message, 'danger');
    }
}

document.getElementById('managementBoardForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('managementBoardId').value;
    const boardData = {
        name: document.getElementById('name').value,
        address: document.getElementById('address').value,
        phoneNumber: document.getElementById('phoneNumber').value,
        email: document.getElementById('email').value,
        directorName: document.getElementById('directorName').value,
        directorPhone: document.getElementById('directorPhone').value,
        establishedDate: document.getElementById('establishedDate').value,
        status: document.getElementById('status').value || 'Hoạt động',
        description: document.getElementById('description').value
    };
    
    try {
        const url = id ? `${API_URL}/${id}` : API_URL;
        const method = id ? 'PUT' : 'POST';
        
        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(boardData)
        });
        
        const data = await response.json();
        
        if (response.ok || response.status === 201) {
            showAlert(data.message || (id ? 'Cập nhật thành công' : 'Thêm mới thành công'));
            document.getElementById('managementBoardForm').reset();
            document.getElementById('managementBoardId').value = '';
            document.getElementById('formTitle').textContent = 'Thêm Ban quản lý Mới';
            loadManagementBoards();
        } else {
            showAlert(data.error || 'Lỗi khi lưu dữ liệu', 'danger');
        }
    } catch (error) {
        console.error('Error saving management board:', error);
        showAlert('Lỗi khi lưu dữ liệu: ' + error.message, 'danger');
    }
});

document.getElementById('resetBtn').addEventListener('click', () => {
    document.getElementById('managementBoardForm').reset();
    document.getElementById('managementBoardId').value = '';
    document.getElementById('formTitle').textContent = 'Thêm Ban quản lý Mới';
});

loadManagementBoards();
