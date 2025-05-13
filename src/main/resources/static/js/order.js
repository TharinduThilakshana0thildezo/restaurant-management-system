document.addEventListener('DOMContentLoaded', function() {
    
    loadOrders();
    
    
    document.getElementById('addItem').addEventListener('click', function() {
        const orderItems = document.getElementById('orderItems');
        const newItem = document.createElement('div');
        newItem.className = 'order-item';
        newItem.innerHTML = `
            <input type="number" class="menuItemId" placeholder="Menu Item ID" required>
            <input type="number" class="quantity" placeholder="Quantity" min="1" required>
            <input type="number" class="price" placeholder="Price" step="0.01" required>
            <input type="text" class="instructions" placeholder="Special Instructions">
            <button type="button" class="remove-item">Remove</button>
        `;
        orderItems.appendChild(newItem);
        
        
        newItem.querySelector('.remove-item').addEventListener('click', function() {
            orderItems.removeChild(newItem);
        });
    });
    
    
    document.getElementById('orderForm').addEventListener('submit', function(e) {
        e.preventDefault();
        createOrder();
    });
    
    
    document.getElementById('statusFilter').addEventListener('change', loadOrders);
    document.getElementById('tableFilter').addEventListener('input', loadOrders);
});

function loadOrders() {
    const status = document.getElementById('statusFilter').value;
    const table = document.getElementById('tableFilter').value;
    
    let url = '/api/orders';
    if (status || table) {
        url += '?';
        if (status) url += `status=${status}`;
        if (table) url += `${status ? '&' : ''}tableNumber=${table}`;
    }
    
    fetch(url)
        .then(response => response.json())
        .then(orders => {
            const tableBody = document.querySelector('#ordersTable tbody');
            tableBody.innerHTML = '';
            
            orders.forEach(order => {
                const row = document.createElement('tr');
                
                row.innerHTML = `
                    <td>${order.id}</td>
                    <td>${order.tableNumber}</td>
                    <td>${order.customerName || '-'}</td>
                    <td class="status-${order.status.toLowerCase().replace('_', '-')}">${order.status.replace('_', ' ')}</td>
                    <td>$${order.totalAmount.toFixed(2)}</td>
                    <td>${new Date(order.orderDate).toLocaleString()}</td>
                    <td>
                        <button class="action-btn update-btn" data-id="${order.id}">Update</button>
                        <button class="action-btn delete-btn" data-id="${order.id}">Delete</button>
                    </td>
                `;
                
                tableBody.appendChild(row);
                
               
                row.querySelector('.update-btn').addEventListener('click', () => showUpdateForm(order));
                row.querySelector('.delete-btn').addEventListener('click', () => deleteOrder(order.id));
            });
        })
        .catch(error => console.error('Error loading orders:', error));
}

function createOrder() {
    const tableNumber = document.getElementById('tableNumber').value;
    const customerName = document.getElementById('customerName').value;
    const notes = document.getElementById('notes').value;
    
    const items = Array.from(document.querySelectorAll('.order-item')).map(item => ({
        menuItemId: parseInt(item.querySelector('.menuItemId').value),
        quantity: parseInt(item.querySelector('.quantity').value),
        price: parseFloat(item.querySelector('.price').value),
        specialInstructions: item.querySelector('.instructions').value
    }));
    
    const order = {
        tableNumber,
        customerName,
        notes,
        items
    };
    
    fetch('/api/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(order)
    })
    .then(response => response.json())
    .then(() => {
        alert('Order created successfully!');
        document.getElementById('orderForm').reset();
        document.getElementById('orderItems').innerHTML = `
            <div class="order-item">
                <input type="number" class="menuItemId" placeholder="Menu Item ID" required>
                <input type="number" class="quantity" placeholder="Quantity" min="1" required>
                <input type="number" class="price" placeholder="Price" step="0.01" required>
                <input type="text" class="instructions" placeholder="Special Instructions">
                <button type="button" class="remove-item">Remove</button>
            </div>
        `;
        loadOrders();
    })
    .catch(error => {
        console.error('Error creating order:', error);
        alert('Error creating order. Please try again.');
    });
}

function showUpdateForm(order) {
    
    const newStatus = prompt('Enter new status (PENDING, IN_PROGRESS, COMPLETED):', order.status);
    
    if (newStatus && ['PENDING', 'IN_PROGRESS', 'COMPLETED'].includes(newStatus.toUpperCase())) {
        const updatedOrder = {...order, status: newStatus.toUpperCase()};
        
        fetch(`/api/orders/${order.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedOrder)
        })
        .then(response => {
            if (response.ok) {
                alert('Order updated successfully!');
                loadOrders();
            } else {
                alert('Error updating order.');
            }
        })
        .catch(error => {
            console.error('Error updating order:', error);
            alert('Error updating order. Please try again.');
        });
    }
}

function deleteOrder(orderId) {
    if (confirm('Are you sure you want to delete this order?')) {
        fetch(`/api/orders/${orderId}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                alert('Order deleted successfully!');
                loadOrders();
            } else {
                alert('Error deleting order.');
            }
        })
        .catch(error => {
            console.error('Error deleting order:', error);
            alert('Error deleting order. Please try again.');
        });
    }
}