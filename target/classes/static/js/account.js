const API_URL = '/api/accounts';
let residents = [];
let managementBoards = [];

async function loadDropdowns() {
    try {
        const [residentsRes, boardsRes] = await Promise.all([
            fetch('/api/residents'),
            fetch('/api/management-boards')
        ]);
        
        residents = await residentsRes.json();
        managementBoards = await boardsRes.json();
        
        const residentSelect = document.getElementById('residentId');
        residentSelect.innerHTML = '<option value="">Chọn cư dân</option>';
        residents.forEach(resident => {
            const option = document.createElement('option');
            option.value = resident.id;
            option.textContent = `${resident.fullName || ''} (${resident.citizenId || ''})`;
            residentSelect.appendChild(option);
        });
        
        const boardSelect = document.getElementById('managementBoardId');
        boardSelect.innerHTML = '<option value="">Chọn ban quản lý</option>';
        managementBoards.forEach(board => {
            const option = document.createElement('option');
            option.value = board.id;
            option.textContent = board.name || '';
            boardSelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading dropdowns:', error);
    }
}

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

async function loadAccounts() {
    try {
        const response = await fetch(API_URL);
        const data = await response.json();
        const tbody = document.getElementById('accountTableBody');
        
        if (data.length === 0) {
            tbody.innerHTML = '<tr><td colspan="9" class="text-center">Không có dữ liệu</td></tr>';
            return;
        }
        
        tbody.innerHTML = data.map(account => `
            <tr>
                <td>${account.id}</td>
                <td>${account.username || ''}</td>
                <td>${account.email || ''}</td>
                <td><span class="badge bg-primary">${account.role || ''}</span></td>
                <td><span class="badge bg-${account.status === 'Active' ? 'success' : account.status === 'Locked' ? 'danger' : 'warning'}">${account.status || ''}</span></td>
                <td>${account.resident ? account.resident.fullName : ''}</td>
                <td>${account.managementBoard ? account.managementBoard.name : ''}</td>
                <td>${account.createdAt ? new Date(account.createdAt).toLocaleDateString('vi-VN') : ''}</td>
                <td>
                    <button class="btn btn-sm btn-warning" onclick="editAccount(${account.id})">Sửa</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteAccount(${account.id})">Xóa</button>
                </td>
            </tr>
        `).join('');
    } catch (error) {
        console.error('Error loading accounts:', error);
        showAlert('Lỗi khi tải danh sách tài khoản: ' + error.message, 'danger');
    }
}

async function editAccount(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const data = await response.json();
        
        document.getElementById('accountId').value = data.id;
        document.getElementById('username').value = data.username || '';
        document.getElementById('password').value = ''; // Không hiển thị password cũ
        document.getElementById('email').value = data.email || '';
        document.getElementById('phoneNumber').value = data.phoneNumber || '';
        document.getElementById('role').value = data.role || '';
        document.getElementById('status').value = data.status || 'Active';
        document.getElementById('residentId').value = data.resident ? data.resident.id : '';
        document.getElementById('managementBoardId').value = data.managementBoard ? data.managementBoard.id : '';
        document.getElementById('formTitle').textContent = 'Chỉnh sửa Tài khoản';
        
        document.getElementById('accountForm').scrollIntoView({ behavior: 'smooth' });
    } catch (error) {
        console.error('Error loading account:', error);
        showAlert('Lỗi khi tải thông tin tài khoản: ' + error.message, 'danger');
    }
}

async function deleteAccount(id) {
    if (!confirm('Bạn có chắc chắn muốn xóa tài khoản này?')) return;
    
    try {
        const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        const data = await response.json();
        
        if (response.ok) {
            showAlert(data.message || 'Xóa tài khoản thành công');
            loadAccounts();
        } else {
            showAlert(data.error || 'Lỗi khi xóa tài khoản', 'danger');
        }
    } catch (error) {
        console.error('Error deleting account:', error);
        showAlert('Lỗi khi xóa tài khoản: ' + error.message, 'danger');
    }
}

document.getElementById('accountForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('accountId').value;
    const accountData = {
        username: document.getElementById('username').value,
        password: document.getElementById('password').value,
        email: document.getElementById('email').value,
        phoneNumber: document.getElementById('phoneNumber').value,
        role: document.getElementById('role').value,
        status: document.getElementById('status').value || 'Active'
    };
    
    const residentId = document.getElementById('residentId').value;
    const managementBoardId = document.getElementById('managementBoardId').value;
    
    if (residentId) {
        accountData.resident = { id: parseInt(residentId) };
    }
    if (managementBoardId) {
        accountData.managementBoard = { id: parseInt(managementBoardId) };
    }
    
    try {
        const url = id ? `${API_URL}/${id}` : API_URL;
        const method = id ? 'PUT' : 'POST';
        
        const response = await fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(accountData)
        });
        
        const data = await response.json();
        
        if (response.ok || response.status === 201) {
            showAlert(data.message || (id ? 'Cập nhật thành công' : 'Thêm mới thành công'));
            document.getElementById('accountForm').reset();
            document.getElementById('accountId').value = '';
            document.getElementById('formTitle').textContent = 'Thêm Tài khoản Mới';
            loadAccounts();
        } else {
            showAlert(data.error || 'Lỗi khi lưu dữ liệu', 'danger');
        }
    } catch (error) {
        console.error('Error saving account:', error);
        showAlert('Lỗi khi lưu dữ liệu: ' + error.message, 'danger');
    }
});

document.getElementById('resetBtn').addEventListener('click', () => {
    document.getElementById('accountForm').reset();
    document.getElementById('accountId').value = '';
    document.getElementById('formTitle').textContent = 'Thêm Tài khoản Mới';
});

loadDropdowns();
loadAccounts();
