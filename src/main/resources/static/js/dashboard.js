

document.addEventListener('DOMContentLoaded', function() {
    console.log('Dashboard initialized');
    
    
    setupDataRefresh();
    
    
    initTooltips();
    
    
    setupMobileSidebar();
});


function setupDataRefresh() {
    
    setInterval(() => {
        refreshDashboardData();
    }, 30000); 
}


function refreshDashboardData() {
    fetch('/api/dashboard/summary')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            
            updateCardValues(data);
            
            
            updateRecentOrders(data.recentOrders);
            
            
            updateCharts(data);
            
            console.log('Dashboard data refreshed');
        })
        .catch(error => {
            console.error('Error refreshing dashboard data:', error);
        });
}


function updateCardValues(data) {
    
    const todaysOrdersElement = document.querySelector('.card-title:contains("Today\'s Orders")').nextElementSibling;
    if (todaysOrdersElement) {
        todaysOrdersElement.textContent = data.todaysOrders;
    }
    
    
    const todaysRevenueElement = document.querySelector('.card-title:contains("Today\'s Revenue")').nextElementSibling;
    if (todaysRevenueElement) {
        todaysRevenueElement.textContent = '$' + data.todaysRevenue;
    }
    
    
    const availableTablesElement = document.querySelector('.card-title:contains("Available Tables")').nextElementSibling;
    if (availableTablesElement) {
        availableTablesElement.textContent = data.tableCounts.Available;
    }
    
    
    const pendingOrdersElement = document.querySelector('.card-title:contains("Pending Orders")').nextElementSibling;
    if (pendingOrdersElement) {
        pendingOrdersElement.textContent = data.orderStatusCounts.Pending;
    }
}


function updateRecentOrders(orders) {
    const tableBody = document.querySelector('.table-striped tbody');
    if (!tableBody || !orders) return;
    
    
    tableBody.innerHTML = '';
    
    
    orders.forEach(order => {
        const row = document.createElement('tr');
        
        
        row.innerHTML = `
            <td>${order.orderId}</td>
            <td>${order.table ? order.table.tableNumber : 'N/A'}</td>
            <td>${order.customerName}</td>
            <td>${formatTime(order.orderTime)}</td>
            <td>
                <span class="badge ${getStatusBadgeClass(order.status)}">${order.status}</span>
            </td>
            <td>$${order.totalAmount}</td>
        `;
        
        tableBody.appendChild(row);
    });
}


function formatTime(isoString) {
    const date = new Date(isoString);
    return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
}


function getStatusBadgeClass(status) {
    switch (status) {
        case 'Pending': return 'bg-warning';
        case 'Preparing': return 'bg-info';
        case 'Ready': return 'bg-primary';
        case 'Served': return 'bg-success';
        case 'Paid': return 'bg-secondary';
        default: return 'bg-secondary';
    }
}


function updateCharts(data) {
    if (!window.revenueChart || !window.tableStatusChart) return;
    
    
    if (data.revenueChart && data.revenueChart.length > 0) {
        const labels = data.revenueChart.map(item => {
            const date = new Date(item.date);
            return date.toLocaleDateString('en-US', { month: 'short', day: 'numeric' });
        });
        
        const revenues = data.revenueChart.map(item => item.totalRevenue);
        const orders = data.revenueChart.map(item => item.totalOrders);
        
        window.revenueChart.data.labels = labels;
        window.revenueChart.data.datasets[0].data = revenues;
        window.revenueChart.data.datasets[1].data = orders;
        window.revenueChart.update();
    }
    
    
    if (data.tableCounts) {
        const tableStatusLabels = Object.keys(data.tableCounts);
        const tableStatusValues = Object.values(data.tableCounts);
        
        window.tableStatusChart.data.labels = tableStatusLabels;
        window.tableStatusChart.data.datasets[0].data = tableStatusValues;
        window.tableStatusChart.update();
    }
}


function initTooltips() {
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
}


function setupMobileSidebar() {
    const sidebarToggle = document.querySelector('.navbar-toggler');
    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', function() {
            document.querySelector('#sidebar').classList.toggle('show');
        });
    }
}


jQuery.expr[':'].contains = function(a, i, m) {
    return jQuery(a).text().toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
};