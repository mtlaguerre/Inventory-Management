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

function findTotalCount(object, callback) {

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
            buildStat(object);

            // proper scheduling for updated warehouse array
            if (object.name === 'warehouses') findUtilization(object.items);
        }
    }

    // make the HTTP GET request to the find all [object] endpoint (products, warehouses, etc.)
    xhr.open('GET', inventoryApiUrl + object.name);

    // send the request
    xhr.send();
}

function buildStat(object) {

    let stat = document.createElement('h2');  // create an <h2> element

    // update fields
    stat.className = 'h2';                  // add bootstrap h2 styling

    // populate stat fields accordingly
    stat.innerText = object.name == 'products' || object.name == 'warehouses' ? object.items.length :
    object.name == 'utilization' ? object.stat + '%' : object.stat;

    // add stat to total appropriate card
    document.getElementById(`total-${object.name}-col`).appendChild(stat);
}

function findUtilization(warehouseList) {

    // loop through each warehouse and find total sum
    let sum = 0;
    let max = 0;
    
    warehouseList.forEach(warehouse => {

        sum += warehouse.capacity;      // add warehouse capacity to sum
        
        max += warehouse.warehouseName.warehouseLocation.maxCapacity;       // add warehouse capacity to max
    });

    // create object to build stat (to reuse buildStat())
    let util = {
        name : 'utilization',
        stat : ((sum / max) * 100).toFixed(2)     // convert from unformatted decimal to 2 digit decimal percentage
    };

    // update total utilization stat
    buildStat(util);
}