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

document.getElementById('delete-button')


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
    `<button class="btn btn-success" id="editButton" onclick="editProduct(${newProduct.id})">Edit</button>`

    deleteBtn.innerHTML = 
    `<button class="btn btn-success" id="deleteButton" onclick="deleteProduct(${newProduct.id})">Delete</button>`

    tr.appendChild(rm);
    tr.appendChild(name);
    tr.appendChild(capacity);
    tr.appendChild(warehouse);
    tr.appendChild(editBtn);
    tr.appendChild(deleteBtn);

    // set id attribute for <tr>
    tr.setAttribute('id', 'TR' + newProduct.id);

    document.getElementById('products-table-body').appendChild(tr);

    allProducts.push(newProduct);
}

function editProduct(productId) {

}

function deleteProduct(productId) {

    // setup product for deletion
    let product = {
        id : productId
    };

    // send HTTP DELETE request
    fetch(productsApiUrl + '/id/' + product.id, {
        method : 'DELETE',
        headers : {
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(product)
    })
    .then(data => {

        // fetch response (status code, json, etc)
        if (data.status === 204) {
            // remove product from table
            removeProductFromTable(product);
        }
    })
    .catch(error => console.error(error));
}

function removeProductFromTable(product) {

    // find specific <tr> by the id that equals product id
    const element = document.getElementById('TR' + product.id);
    element.remove();   // remove the element
}