document.getElementById("addIngredientForm").addEventListener("submit", function(event) {
    event.preventDefault();

    let ingredientData = {
        name: document.getElementById("name").value,
        quantity: document.getElementById("quantity").value,
        unit: document.getElementById("unit").value,
        reorderLevel: document.getElementById("reorderLevel").value
    };

    fetch("/inventory/ingredient/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(ingredientData)
    })
        .then(response => response.json())
        .then(data => {
            alert("Ingredient added successfully!");
            window.location.reload();
        });
});