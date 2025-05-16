
document.addEventListener('DOMContentLoaded', function() {
    
    animateEntrance();
    
    
    loadPayments();
    
    
    initializeInputAnimations();
    
    
    const paymentForm = document.getElementById('paymentForm');
    paymentForm.addEventListener('submit', function(e) {
        e.preventDefault();
        const orderId = document.getElementById('orderId').value;
        const amount = document.getElementById('amount').value;
        const paymentMethod = document.getElementById('paymentMethod').value;
        
        
        if (validatePaymentForm()) {
            const submitButton = this.querySelector('button[type="submit"]');
            animateButtonLoading(submitButton, true);
            processPayment(orderId, amount, paymentMethod);
        }
    });
});


function animateEntrance() {
    const h1 = document.querySelector('.payment-container > h1');
    if (h1) {
        h1.style.opacity = '0';
        h1.style.animation = 'fadeIn 0.6s ease forwards';
    }
    
    
}

/**
 * Shows a success message with animation
 * @param {string} message - The success message
 */
function showSuccessMessage(message) {
    showNotification(message, 'success');
}

/**
 * Shows an error message with animation
 * @param {string} message - The error message
 */
function showErrorMessage(message) {
    showNotification(message, 'error');
}

/**
 * Creates and displays an animated notification
 * @param {string} message - The notification message
 * @param {string} type - The notification type (success/error)
 */
function showNotification(message, type) {
    
    const existingNotifications = document.querySelectorAll('.notification');
    existingNotifications.forEach(notification => {
        notification.remove();
    });
    
    
    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.innerHTML = `
        <div class="notification-content">
            <span>${message}</span>
        </div>
    `;
    
    document.body.appendChild(notification);
    
    // Animate in
    setTimeout(() => {
        notification.classList.add('show');
    }, 10);
    
    
    setTimeout(() => {
        notification.classList.remove('show');
        setTimeout(() => {
            notification.remove();
        }, 300);
    }, 4000);
}


function loadPayments() {
    
    const tableBody = document.querySelector('#paymentsTable tbody');
    const loadingRow = document.createElement('tr');
    loadingRow.className = 'loading-row';
    loadingRow.innerHTML = `
        <td colspan="6" class="loading-cell">
            <div class="loading-spinner"></div>
            <span class="loading">Loading payments</span>
        </td>
    `;
    tableBody.innerHTML = '';
    tableBody.appendChild(loadingRow);
    
    fetch('/api/payments')
        .then(response => response.json())
        .then(payments => {
            
            setTimeout(() => {
                tableBody.innerHTML = '';
                
                
                const existingIds = Array.from(tableBody.querySelectorAll('tr')).map(row => 
                    row.cells[0] ? row.cells[0].textContent : null
                );
                
                
                payments.forEach((payment, index) => {
                    const row = document.createElement('tr');
                    const isNew = !existingIds.includes(payment.id.toString());
                    
                    if (isNew) {
                        row.className = 'new-payment-row';
                    }
                    
                   
                    row.style.opacity = '0';
                    row.style.animation = `fadeIn 0.3s ease forwards ${index * 0.05}s`;
                    
                    row.innerHTML = `
                        <td>${payment.id}</td>
                        <td>${payment.orderId}</td>
                        <td>${payment.amount.toFixed(2)}</td>
                        <td>${formatPaymentMethod(payment.paymentMethod)}</td>
                        <td>${payment.paymentStatus}</td>
                        <td>${new Date(payment.paymentDate).toLocaleString()}</td>
                    `;
                    tableBody.appendChild(row);
                });
                
                if (payments.length === 0) {
                    const emptyRow = document.createElement('tr');
                    emptyRow.innerHTML = `
                        <td colspan="6" style="text-align: center;">No payments found</td>
                    `;
                    tableBody.appendChild(emptyRow);
                }
            }, 300);
        })
        .catch(error => {
            console.error('Error loading payments:', error);
            tableBody.innerHTML = '';
            const errorRow = document.createElement('tr');
            errorRow.innerHTML = `
                <td colspan="6" style="text-align: center; color: #e74c3c;">
                    Error loading payment data. Please refresh the page.
                </td>
            `;
            tableBody.appendChild(errorRow);
        });
}


function initializeInputAnimations() {
    const inputs = document.querySelectorAll('input, select');
    
    inputs.forEach(input => {
        
        input.addEventListener('focus', function() {
            this.parentElement.classList.add('input-focused');
        });
        
        input.addEventListener('blur', function() {
            this.parentElement.classList.remove('input-focused');
        });
        
        
        input.addEventListener('input', function() {
            if (this.value) {
                this.classList.add('has-value');
            } else {
                this.classList.remove('has-value');
            }
        });
    });
}

/**
 * Validates the payment form
 * @return {boolean} True if valid, false otherwise
 */
function validatePaymentForm() {
    const orderId = document.getElementById('orderId');
    const amount = document.getElementById('amount');
    const paymentMethod = document.getElementById('paymentMethod');
    let isValid = true;
    
    
    [orderId, amount, paymentMethod].forEach(field => {
        if (!field.value) {
            animateValidationError(field);
            isValid = false;
        } else {
            field.classList.remove('error');
        }
    });
    
    return isValid;
}

/**
 * Animates validation error on a field
 * @param {HTMLElement} field - The field with error
 */
function animateValidationError(field) {
    field.classList.add('error');
    field.style.animation = 'shake 0.5s ease';
    setTimeout(() => {
        field.style.animation = '';
    }, 500);
}

/**
 * Animates button loading state
 * @param {HTMLElement} button - The button element
 * @param {boolean} isLoading - Loading state
 */
function animateButtonLoading(button, isLoading) {
    if (isLoading) {
        button.disabled = true;
        button.classList.add('loading');
        button.dataset.originalText = button.textContent;
        button.textContent = 'Processing';
    } else {
        button.disabled = false;
        button.classList.remove('loading');
        button.textContent = button.dataset.originalText || 'Process Payment';
    }
}

/**
 * Processes a new payment transaction with animations
 * @param {string} orderId - The order identifier
 * @param {number} amount - The payment amount
 * @param {string} paymentMethod - The selected payment method
 */
function processPayment(orderId, amount, paymentMethod) {
    const params = new URLSearchParams();
    params.append('orderId', orderId);
    params.append('amount', amount);
    params.append('paymentMethod', paymentMethod);
    
    const submitButton = document.querySelector('#paymentForm button[type="submit"]');
    
    
    setTimeout(() => {
        fetch('/api/payments/process?' + params.toString(), {
            method: 'POST'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Payment processing failed');
            }
            return response.json();
        })
        .then(payment => {
            animateButtonLoading(submitButton, false);
            showSuccessMessage(`Payment processed successfully! Transaction ID: ${payment.transactionId}`);
            document.getElementById('paymentForm').reset();
            loadPayments();
        })
        .catch(error => {
            animateButtonLoading(submitButton, false);
            console.error('Error processing payment:', error);
            showErrorMessage('Error processing payment. Please try again.');
        });
    }, 800); 
}

/**
 * Formats payment method codes into human-readable text
 * @param {string} method - The payment method code
 * @return {string} Formatted payment method name
 */
function formatPaymentMethod(method) {
    switch(method) {
        case 'CASH': return 'Cash';
        case 'CREDIT_CARD': return 'Credit Card';
        case 'DEBIT_CARD': return 'Debit Card';
        default: return method;
    }
}