
document.addEventListener('DOMContentLoaded', function() {
   
    const tableForm = document.getElementById('tableForm');
    const submitBtn = document.getElementById('submitBtn');
    const cancelBtn = document.getElementById('cancelBtn');
    const statusFilter = document.getElementById('statusFilter');
    
    
    let currentTableId = null;

    
    initializeApp();

    
    function initializeApp() {
        
        loadTables();
        
       
        setupEventListeners();
    }

   
    function setupEventListeners() {
        
        tableForm.addEventListener('submit', handleFormSubmit);
        
        
        cancelBtn.addEventListener('click', resetForm);
        
        
        statusFilter.addEventListener('change', loadTables);
    }

    /**
     * Handle form submission for create/update operations
     * @param {Event} e - Form submission event
     */
    function handleFormSubmit(e) {
        e.preventDefault();
        
        const tableData = {
            tableNumber: parseInt(document.getElementById('tableNumber').value),
            seats: parseInt(document.getElementById('seats').value),
            status: document.getElementById('status').value,
            assignedWaiterId: parseInt(document.getElementById('waiterId').value)
        };

        if (currentTableId) {
            updateTable(currentTableId, tableData);
        } else {
            createTable(tableData);
        }
    }

    
    function loadTables() {
        let url = '/api/tables';
        const status = statusFilter.value;
        
        if (status) {
            url += `/status/${status}`;
        }

        fetch(url)
            .then(response => response.json())
            .then(tables => renderTables(tables))
            .catch(error => {
                console.error('Error loading tables:', error);
            });
    }

    /**
     * Render tables in the table body
     * @param {Array} tables - Array of table objects
     */
    function renderTables(tables) {
        const tableBody = document.querySelector('#tablesTable tbody');
        tableBody.innerHTML = '';
        
        tables.forEach(table => {
            const row = createTableRow(table);
            tableBody.appendChild(row);
        });
    }

    /**
     * Create a table row element for a given table
     * @param {Object} table - Table object
     * @returns {HTMLElement} - Table row element
     */
    function createTableRow(table) {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${table.id}</td>
            <td>${table.tableNumber}</td>
            <td>${table.seats}</td>
            <td class="status-${table.status.toLowerCase()}">${table.status}</td>
            <td>${table.assignedWaiterId}</td>
            <td>
                <button class="action-btn edit-btn" data-id="${table.id}">Edit</button>
                <button class="action-btn delete-btn" data-id="${table.id}">Delete</button>
            </td>
        `;
        
        
        row.querySelector('.edit-btn').addEventListener('click', () => editTable(table));
        row.querySelector('.delete-btn').addEventListener('click', () => deleteTable(table.id));
        
        return row;
    }

    /**
     * Create a new table via API
     * @param {Object} tableData - Table data to create
     */
    function createTable(tableData) {
        fetch('/api/tables', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(tableData)
        })
        .then(response => response.json())
        .then(() => {
            alert('Table created successfully!');
            resetForm();
            loadTables();
        })
        .catch(error => {
            console.error('Error creating table:', error);
            alert('Error creating table. Please try again.');
        });
    }

    /**
     * Update an existing table via API
     * @param {number} id - Table ID
     * @param {Object} tableData - Updated table data
     */
    function updateTable(id, tableData) {
        fetch(`/api/tables/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(tableData)
        })
        .then(response => {
            if (response.ok) {
                alert('Table updated successfully!');
                resetForm();
                loadTables();
            } else {
                alert('Error updating table.');
            }
        })
        .catch(error => {
            console.error('Error updating table:', error);
            alert('Error updating table. Please try again.');
        });
    }

    /**
     * Delete a table via API
     * @param {number} id - Table ID to delete
     */
    function deleteTable(id) {
        if (confirm('Are you sure you want to delete this table?')) {
            fetch(`/api/tables/${id}`, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    alert('Table deleted successfully!');
                    loadTables();
                } else {
                    alert('Error deleting table.');
                }
            })
            .catch(error => {
                console.error('Error deleting table:', error);
                alert('Error deleting table. Please try again.');
            });
        }
    }

    /**
     * Populate form with table data for editing
     * @param {Object} table - Table object to edit
     */
    function editTable(table) {
        currentTableId = table.id;
        document.getElementById('tableId').value = table.id;
        document.getElementById('tableNumber').value = table.tableNumber;
        document.getElementById('seats').value = table.seats;
        document.getElementById('status').value = table.status;
        document.getElementById('waiterId').value = table.assignedWaiterId;
        
        submitBtn.textContent = 'Update Table';
        cancelBtn.style.display = 'inline-block';
    }

    
    function resetForm() {
        currentTableId = null;
        tableForm.reset();
        submitBtn.textContent = 'Save Table';
        cancelBtn.style.display = 'none';
    }
});