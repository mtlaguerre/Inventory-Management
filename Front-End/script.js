const productsApiUrl = 'http://localhost:8080/products';
let allProducts = [];


document.addEventListener('DOMContentLoaded', () => {

    let xhr = new XMLHttpRequest();     // creating a new XHR object (this puts it into state 0 - unsent)

    /**
     * onreadystatechange - is a callback the fires everytime the state of the xhr changes
     * 
     *      5 stages (ready states):
     *          0 - unsent
     *          1 - opened
     *          2 - headers received
     *          3 - loading
     *          4 - done        ---> this is the main one we care about
     */

    xhr.onreadystatechange= () => {

        if(xhr.readyState === 4) {
            // at readyState 4, we have our response from the server

            // responseText contains the response from the server
            // JSON.parse() parses the JSON into js objects
            let products = JSON.parse(xhr.responseText);

            // add products to table
            products.forEach(newProduct => {
                addProductToTable(newProduct);
            });
        }
    };

    // use open to set the request method and the url to send the request to (state changed to 1 - opened)
    xhr.open('GET', productsApiUrl);

    // this sends the request
    xhr.send();

});


document.getElementById('new-product-form').addEventListener('submit', (event) => {

    // event object gives info about the event we are listening for
    event.preventDefault();             // preventDefault() is going to prevent the form from refreshing the page

    // FormData takes in the html tags for your form
    let inputData = new FormData(document.getElementById('new-product-form'));

    let newProduct = {
        // use .get() to retrieve a field from from data and pass in the NAME attribute from the <input> tag
        rm : inputData.get("new-product-rm"),
        warehouse : inputData.get("new-warehouse"),
        capacity : inputData.get("new-product-capacity")
        
    }

    doPostRequest(newProduct);
});

async function doPostRequest(newProduct) {

    let returnedData = await fetch(productsApiUrl, {
        method : 'POST',
        headers : {
            "Content-Type" : 'application/json'     // make sure your server is expecting to receive JSON in the body
        },
        body : JSON.stringify(newProduct)           // turns a js object into JSON
    });

    // .json() to deserialize the JSON back into js object - this ALSO returns a promise
    let productJson = await returnedData.json();

    // just need to add product to table
    addProductToTable(productJson);
}



function addProductToTable(newProduct) {

    // DOM Manipulation - where we manually change the DOM
    
    let tr = document.createElement('tr');   // will create a <tr> tag
    let rm = document.createElement('td');      // will create a <td> tag
    let capacity = document.createElement('td');      // will create a <td> tag
    let warehouse = document.createElement('td');      // will create a <td> tag
    let editBtn = document.createElement('td');      // will create a <td> tag
    let deleteBtn = document.createElement('td');      // will create a <td> tag

    rm.innerText = newProduct.rm.rm;
    capacity.innerText = newProduct.capacity;
    warehouse.innerText = newProduct.warehouse.warehouseName.name;

    editBtn.innerHTML = 
    `<button class="btn btn-primary" id="edit-button" onclick="activateEditForm(${newProduct.id})">Edit</button>`

    deleteBtn.innerHTML = 
    `<button class="btn btn-primary" id="delete-button" onclick="activateDeleteForm(${newProduct.id})">Delete</button>`

    // adds the <td> tags as nested children to the <tr> tag
    tr.appendChild(rm);
    tr.appendChild(capacity);
    tr.appendChild(warehouse);
    tr.appendChild(editBtn);
    tr.appendChild(deleteBtn);

    // adds the <tr> tag to the <tbody> tag
    document.getElementById('product-table-body').appendChild(tr);

    // adding the new product to the list of all the products
    allProducts.push(newProduct);
}

function activateEditForm(productId) {

}

function activateDeleteForm(productId) {
    
}