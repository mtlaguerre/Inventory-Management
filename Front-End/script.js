productsApiUrl = "http://localhost:8080/products";
warehousesApiUrl = "http://localhost:8080/warehouses";

let products = [];
let warehouses = [];

let productCount = 0;
let warehouseCount = 0;

// every time the page loads, run this function
document.addEventListener('DOMContentLoaded', () => {

    findTotalProductCount();

    findTotalWarehouseCount();
    
});

function findTotalProductCount() {

    let xhr = new XMLHttpRequest();

    // through every stage of loading the page, run this function
    xhr.onreadystatechange = () => {

        // if state is 4 (page finished loading), do the following
        if (xhr.readyState === 4) {

            // store request response
            let productsJSON = JSON.parse(xhr.responseText);

            // loop through each product
            productsJSON.forEach(product => productCount++);

            // update total products stat info
            buildProductsStat(productCount);
        }
    }

    // make the HTTP GET request to the find all products endpoint
    xhr.open('GET', productsApiUrl);

    // send the request
    xhr.send();
}

function findTotalWarehouseCount() {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = () => {

        if (xhr.readyState === 4) {

            let warehouseJSON = JSON.parse(xhr.responseText);

            // loop through each warehouse
            warehouseJSON.forEach(warehouse => warehouseCount++);

            // update warehouses stat info
            buildWarehouseStat(warehouseCount);
        }
    }

    // make the HTTP GET request to the find all warehouses endpoint
    xhr.open('GET', warehousesApiUrl);

    xhr.send();
}

function buildProductsStat(count) {

    let productStat = document.createElement('h2');  // create an <h2> element

    // prepare productStat
    productStat.className = 'h2';        // add bootstrap h2 styling
    productStat.innerText = count;    // add number of products in the inner text field

    // add productStat to total products card
    document.getElementById('total-products-col').appendChild(productStat);
}

function buildWarehouseStat(count) {

    let warehouseStat = document.createElement('h2');

    // prepare warehouseStat
    warehouseStat.className = 'h2';
    warehouseStat.innerText = count;   // add number of warehouses in the inner text field

    // add warehouseStat to total warehouses card
    document.getElementById('total-warehouses-col').appendChild(warehouseStat);
}

// function buildTotalStat(object) {

//     let stat = document.createElement('h2');  // create an <h2> element

//     // update fields
//     stat.className = 'h2';        // add bootstrap h2 styling
//     stat.innerText = object.lenth;    // add number of products in the inner text field

//     // add stat to total appropriate card
//     document.getElementById(`total-${object}-col`).appendChild(stat);
// }