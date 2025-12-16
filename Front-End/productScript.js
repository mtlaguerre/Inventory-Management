productsApiUrl = "http://localhost:8080/products";
let allProducts = [];

document.addEventListener('DOMContentLoaded', () => {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange= () => {
        
        if (xhr.readyState === 4) {
            
            let products = JSON.parse(xhr.responseText);

            // add products to table
            products.forEach(newProduct => {
                addProductToTable(newProduct);
            });
        }
    };

    xhr.open('GET', productsApiUrl);

    xhr.send();
    
    
});



function addProductToTable(newProduct) {

    
    let tr = document.createElement('tr');
    let rm = document.createElement('td');
    let name = document.createElement('td');
    let capacity = document.createElement('td');
    let warehouse = document.createElement('td');
    let editBtn = document.createElement('td');
    let deleteBtn = document.createElement('td');

    rm.innerText = newProduct.rm.rm;
    name.innerText = newProduct.rm.name;
    capacity.innerText = newProduct.capacity + '/' + newProduct.rm.maxCapacity;
    warehouse.innerText = newProduct.warehouse.warehouseName.name;

    editBtn.innerHTML = 
    `<button class="btn btn-success" id="edit-button" onclick="activateEditForm(${newProduct.id})">Edit</button>`

    deleteBtn.innerHTML = 
    `<button class="btn btn-success" id="delete-button" onclick="activateDeleteForm(${newProduct.id})">Delete</button>`

    tr.appendChild(rm);
    tr.appendChild(name);
    tr.appendChild(capacity);
    tr.appendChild(warehouse);
    tr.appendChild(editBtn);
    tr.appendChild(deleteBtn);

    document.getElementById('products-table-body').appendChild(tr);

    allProducts.push(newProduct);
}