productsApiUrl = "http://localhost:8080/products";
warehousesApiUrl = "http://localhost:8080/warehouses";

let products = {
    name : 'products',      // name of list (for future reference to html id 'total-[name]-col')
    items : []
}

let warehouses = {
    name : 'warehouses',    // name of list (for future reference to html id 'total-[name]-col')
    items : []
}

// every time the page loads, run this function
document.addEventListener('DOMContentLoaded', () => {

    findTotalProductCount();

    findTotalWarehouseCount();
    
})

function findTotalProductCount() {

    let xhr = new XMLHttpRequest();

    // through every stage of loading the page, run this function
    xhr.onreadystatechange = () => {

        // if state is 4 (page finished loading), do the following
        if (xhr.readyState === 4) {

            // store request response
            let productsJSON = JSON.parse(xhr.responseText);

            // loop through each product
            productsJSON.forEach(product => {
                
                // add product to list of products
                products.items.push(product);
            });

            // update total products stat info
            buildTotalStat(products);
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
            warehouseJSON.forEach(warehouse => {

                // add warehouse to list of warehouses
                warehouses.items.push(warehouse);
            });

            // update warehouses stat info
            buildTotalStat(warehouses);
        }
    }

    // make the HTTP GET request to the find all warehouses endpoint
    xhr.open('GET', warehousesApiUrl);

    xhr.send();
}

function buildTotalStat(object) {

    let stat = document.createElement('h2');  // create an <h2> element

    // update fields
    stat.className = 'h2';        // add bootstrap h2 styling
    stat.innerText = object.items.length;    // add number of products in the inner text field

    // add stat to total appropriate card
    document.getElementById(`total-${object.name}-col`).appendChild(stat);
}