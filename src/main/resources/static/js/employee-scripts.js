
const BASE_URL = 'http://localhost:8080/api/employees';


const employeeTableBody = document.getElementById('employeeTableBody');
const searchInput = document.getElementById('searchInput');
const searchBtn = document.getElementById('searchBtn');
const positionFilter = document.getElementById('positionFilter');
const filterBtn = document.getElementById('filterBtn');
const addEmployeeBtn = document.getElementById('addEmployeeBtn');
const employeeForm = document.getElementById('employeeForm');
const saveEmployeeBtn = document.getElementById('saveEmployeeBtn');
const confirmDeleteBtn = document.getElementById('confirmDeleteBtn');


const employeeModal = new bootstrap.Modal(document.getElementById('employeeModal'));
const viewEmployeeModal = new bootstrap.Modal(document.getElementById('viewEmployeeModal'));
const deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));


document.addEventListener('DOMContentLoaded', () => {
    loadEmployees();
    setupEventListeners();
});


function setupEventListeners() {
 
    searchBtn.addEventListener('click', () => {
        const searchQuery = searchInput.value.trim();
        if (searchQuery) {
            searchEmployees(searchQuery);
        } else {
            loadEmployees();
        }
    });

  
    filterBtn.addEventListener('click', () => {
        const position = positionFilter.value;
        if (position) {
            filterEmployeesByPosition(position);
        } else {
            loadEmployees();
        }
    });

 
    addEmployeeBtn.addEventListener('click', () => {
        resetEmployeeForm();
        document.getElementById('employeeModalLabel').textContent = 'Add Employee';
        employeeModal.show();
    });


    saveEmployeeBtn.addEventListener('click', saveEmployee);


    confirmDeleteBtn.addEventListener('click', deleteEmployee);


    searchInput.addEventListener('keyup', (e) => {
        if (e.key === 'Enter') {
            searchBtn.click();
        }
    });
}


async function loadEmployees() {
    try {
        const response = await fetch(BASE_URL);
        const employees = await response.json();
        renderEmployeeTable(employees);
    } catch (error) {
        console.error('Error loading employees:', error);
        showAlert('Failed to load employees. Please try again.', 'danger');
    }
}


async function searchEmployees(query) {
    try {
        const response = await fetch(`${BASE_URL}/search?query=${encodeURIComponent(query)}`);
        const employees = await response.json();
        renderEmployeeTable(employees);
    } catch (error) {
        console.error('Error searching employees:', error);
        showAlert('Search failed. Please try again.', 'danger');
    }
}


async function filterEmployeesByPosition(position) {
    try {
        const response = await fetch(`${BASE_URL}/position/${encodeURIComponent(position)}`);
        const employees = await response.json();
        renderEmployeeTable(employees);
    } catch (error) {
        console.error('Error filtering employees:', error);
        showAlert('Filter failed. Please try again.', 'danger');
    }
}


function renderEmployeeTable(employees) {
    employeeTableBody.innerHTML = '';
    
    if (employees.length === 0) {
        employeeTableBody.innerHTML = `
            <tr>
                <td colspan="8" class="text-center">No employees found</td>
            </tr>
        `;
        return;
    }

    employees.forEach(employee => {
        const statusClass = getStatusClass(employee.status);
        const row = document.createElement('tr');
        
        row.innerHTML = `
            <td>${employee.id}</td>
            <td>${employee.firstName} ${employee.lastName}</td>
            <td>${employee.position}</td>
            <td>${employee.email}</td>
            <td>${employee.phone || '-'}</td>
            <td>${formatDate(employee.hireDate)}</td>
            <td><span class="badge ${statusClass}">${employee.status}</span></td>
            <td>
                <button class="btn btn-sm btn-info btn-action view-btn" data-id="${employee.id}">
                    <i class="fas fa-eye"></i>
                </button>
                <button class="btn btn-sm btn-primary btn-action edit-btn" data-id="${employee.id}">
                    <i class="fas fa-edit"></i>
                </button>
                <button class="btn btn-sm btn-danger btn-action delete-btn" data-id="${employee.id}">
                    <i class="fas fa-trash"></i>
                </button>
            </td>
        `;
        
        employeeTableBody.appendChild(row);
    });


    document.querySelectorAll('.view-btn').forEach(btn => {
        btn.addEventListener('click', () => viewEmployee(btn.getAttribute('data-id')));
    });

    document.querySelectorAll('.edit-btn').forEach(btn => {
        btn.addEventListener('click', () => editEmployee(btn.getAttribute('data-id')));
    });

    document.querySelectorAll('.delete-btn').forEach(btn => {
        btn.addEventListener('click', () => showDeleteConfirmation(btn.getAttribute('data-id')));
    });
}


async function viewEmployee(id) {
    try {
        const response = await fetch(`${BASE_URL}/${id}`);
        const employee = await response.json();
        
        document.getElementById('employeeFullName').textContent = `${employee.firstName} ${employee.lastName}`;
        document.getElementById('employeePosition').textContent = employee.position;
        document.getElementById('detailEmail').textContent = employee.email;
        document.getElementById('detailPhone').textContent = employee.phone || '-';
        document.getElementById('detailHireDate').textContent = formatDate(employee.hireDate);
        document.getElementById('detailSalary').textContent = employee.salary ? `$${employee.salary.toFixed(2)}` : '-';
        document.getElementById('detailAddress').textContent = employee.address || '-';
        document.getElementById('detailEmergencyContact').textContent = employee.emergencyContact || '-';
        
        const statusBadge = document.getElementById('detailStatus');
        statusBadge.textContent = employee.status;
        statusBadge.className = `badge ${getStatusClass(employee.status)}`;
        
        viewEmployeeModal.show();
    } catch (error) {
        console.error('Error loading employee details:', error);
        showAlert('Failed to load employee details.', 'danger');
    }
}


async function editEmployee(id) {
    try {
        const response = await fetch(`${BASE_URL}/${id}`);
        const employee = await response.json();
        
        document.getElementById('employeeId').value = employee.id;
        document.getElementById('firstName').value = employee.firstName;
        document.getElementById('lastName').value = employee.lastName;
        document.getElementById('email').value = employee.email;
        document.getElementById('phone').value = employee.phone || '';
        document.getElementById('position').value = employee.position;
        document.getElementById('hireDate').value = employee.hireDate;
        document.getElementById('salary').value = employee.salary || '';
        document.getElementById('address').value = employee.address || '';
        document.getElementById('emergencyContact').value = employee.emergencyContact || '';
        document.getElementById('status').value = employee.status;
        
        document.getElementById('employeeModalLabel').textContent = 'Edit Employee';
        employeeModal.show();
    } catch (error) {
        console.error('Error loading employee for edit:', error);
        showAlert('Failed to load employee data for editing.', 'danger');
    }
}


async function saveEmployee() {

    const employeeId = document.getElementById('employeeId').value;
    const employee = {
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        email: document.getElementById('email').value,
        phone: document.getElementById('phone').value,
        position: document.getElementById('position').value,
        hireDate: document.getElementById('hireDate').value,
        salary: document.getElementById('salary').value ? parseFloat(document.getElementById('salary').value) : null,
        address: document.getElementById('address').value,
        emergencyContact: document.getElementById('emergencyContact').value,
        status: document.getElementById('status').value
    };
    

    if (!employee.firstName || !employee.lastName || !employee.email || !employee.position || !employee.hireDate) {
        showAlert('Please fill in all required fields.', 'danger');
        return;
    }
    
    try {
        let url = BASE_URL;
        let method = 'POST';
        
        if (employeeId) {
  
            url = `${BASE_URL}/${employeeId}`;
            method = 'PUT';
            employee.id = parseInt(employeeId);
        }
        
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(employee)
        });
        
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Failed to save employee.');
        }
        
        employeeModal.hide();
        loadEmployees();
        showAlert(`Employee successfully ${employeeId ? 'updated' : 'added'}!`, 'success');
    } catch (error) {
        console.error('Error saving employee:', error);
        showAlert(`Failed to save employee: ${error.message}`, 'danger');
    }
}

function showDeleteConfirmation(id) {
    document.getElementById('deleteEmployeeId').value = id;
    deleteModal.show();
}


async function deleteEmployee() {
    const id = document.getElementById('deleteEmployeeId').value;
    
    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'DELETE'
        });
        
        if (!response.ok) {
            throw new Error('Failed to delete employee.');
        }
        
        deleteModal.hide();
        loadEmployees();
        showAlert('Employee successfully deleted!', 'success');
    } catch (error) {
        console.error('Error deleting employee:', error);
        showAlert('Failed to delete employee.', 'danger');
    }
}

function resetEmployeeForm() {
    document.getElementById('employeeId').value = '';
    document.getElementById('firstName').value = '';
    document.getElementById('lastName').value = '';
    document.getElementById('email').value = '';
    document.getElementById('phone').value = '';
    document.getElementById('position').value = '';
    document.getElementById('hireDate').value = '';
    document.getElementById('salary').value = '';
    document.getElementById('address').value = '';
    document.getElementById('emergencyContact').value = '';
    document.getElementById('status').value = 'ACTIVE';
}


function formatDate(dateString) {
    if (!dateString) return '-';
    
    const date = new Date(dateString);
    return date.toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'short',
        day: 'numeric'
    });
}

function getStatusClass(status) {
    switch (status) {
        case 'ACTIVE':
            return 'bg-success';
        case 'INACTIVE':
            return 'bg-danger';
        case 'ON_LEAVE':
            return 'bg-warning text-dark';
        default:
            return 'bg-secondary';
    }
}


function showAlert(message, type) {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show position-fixed`;
    alertDiv.style.top = '20px';
    alertDiv.style.right = '20px';
    alertDiv.style.zIndex = '1050';
    
    alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    `;
    
    document.body.appendChild(alertDiv);
    

    setTimeout(() => {
        if (alertDiv) {
            const bsAlert = new bootstrap.Alert(alertDiv);
            bsAlert.close();
        }
    }, 5000);
}