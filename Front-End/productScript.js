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

document.getElementById('new-product-form').addEventListener('submit', (event) => {

    // event stores info by default
    event.preventDefault();     // preventDefault() prevents form from refreshing page

    let inputData = new FormData(document.getElementById('new-product-form'));

    let newProduct = {
        rm : {
            rm : inputData.get('new-product-rm')
        },
        capacity : inputData.get('new-product-capacity'),
        warehouse : {
            id : inputData.get('new-product-warehouse-id')
        }
    };

    addProduct(newProduct);

})

function addProductToTable(newProduct) {

    
    let tr = document.createElement('tr');
    let rm = document.createElement('td');
    let name = document.createElement('td');
    let capacity = document.createElement('td');
    let warehouse = document.createElement('td');
    let editBtn = document.createElement('td');
    let deleteBtn = document.createElement('td');

    rm.setAttribute('id', 'product-' + newProduct.id + '-rm');
    rm.innerText = newProduct.rm.rm;

    name.setAttribute('id', 'product-' + newProduct.id + '-name')
    name.innerText = newProduct.rm.name;

    capacity.setAttribute('id', 'product-' + newProduct.id + '-capacity')
    capacity.innerText = newProduct.capacity + '/' + newProduct.rm.maxCapacity;

    warehouse.setAttribute('id', 'product-' + newProduct.id + '-warehouse')
    warehouse.innerText = newProduct.warehouse.warehouseName.name;

    editBtn.innerHTML = 
    `<button class="btn btn-success" id="editButton${newProduct.id}" onclick="editProduct(${newProduct.id})">Edit</button>`;

    deleteBtn.innerHTML = 
    `<button class="btn btn-success" id="deleteButton${newProduct.id}" onclick="deleteProduct(${newProduct.id})">Delete</button>`;

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

async function addProduct(newProduct) {

    // send HTTP POST request
    let response = await fetch(productsApiUrl, {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(newProduct)
    });


    if (!response.status === 201) {
        throw new Error(`HTTP error ${response.status}`);
    }
    
    // only parse JSON if there *is* a body
    const text = await response.text();
    if (text) {
        let productJson = JSON.parse(text);
        console.log('PRODUCT JSON', productJson);
        addProductToTable(productJson);
    }
}

function toggleAddForm() {

    // show form and save button, hide add product button
    const formContainer = document.getElementById('new-product-form-container');
    const addProductBtn = document.getElementById('add-product-button');
    formContainer.setAttribute('class', 'container');
    addProductBtn.setAttribute('class', 'd-none');
}