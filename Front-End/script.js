inventoryApiUrl = "http://localhost:8080/";

let products = {
    name : 'products',      // name of list (for finding endpoints and for html id 'total-[name]-col')
    items : []
}

let warehouses = {
    name : 'warehouses',    // name of list (for finding endpoints and for html id 'total-[name]-col')
    items : []
}

// every time the page loads, run this function
document.addEventListener('DOMContentLoaded', () => {

    findTotalCount(products);

    findTotalCount(warehouses);
    
})

function findTotalCount(object) {

    let xhr = new XMLHttpRequest();

    // through every stage of loading the page, run this function
    xhr.onreadystatechange = () => {

        // if state is 4 (page finished loading), do the following
        if (xhr.readyState === 4) {

            // store request response
            let responseJSON = JSON.parse(xhr.responseText);

            // loop through each item
            responseJSON.forEach(item => {

                // add item to current object's list
                object.items.push(item);
            });

            // update total stat of current object
            buildTotalStat(object);
        }
    }

    // make the HTTP GET request to the find all [object] endpoint (products, warehouses, etc.)
    xhr.open('GET', inventoryApiUrl + object.name);

    // send the request
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