warehousesApiUrl = "http://localhost:8080/warehouses";
let allWarehouses = [];

let showingAddWarehouseForm = false;

document.addEventListener('DOMContentLoaded', () => {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange= () => {

        if (xhr.readyState === 4) {

            let warehouses = JSON.parse(xhr.responseText);

            // add warehouses to table
            warehouses.forEach(newWarehouse => {
                addWarehouseCard(newWarehouse);
            });
            
        }

    };

    xhr.open('GET', warehousesApiUrl);

    xhr.send();

});

document.getElementById('new-warehouse-form').addEventListener('submit', async (event) => {

    // prevent refreshes on submit
    event.preventDefault();

    let inputData = new FormData(document.getElementById('new-warehouse-form'));

    // add location
    let newWarehouseLocation = {
        location : inputData.get('new-warehouse-location'),
        maxCapacity : inputData.get('new-warehouse-max-capacity')
    };

    let locationId =  await createWarehouseLocation(newWarehouseLocation);
    console.log('locationId: ', locationId);

    // add name
    let newWarehouseName = {
        name : inputData.get('new-warehouse-name'),
        warehouseLocation : {
            id : locationId
        }
    };

    let warehouseNameId = await createWarehouseName(newWarehouseName);

    // add warehouse
    let newWarehouse = {
        warehouseName : {
            id : warehouseNameId
        },
        capacity : 0
    };

    await addWarehouse(newWarehouse);

    // reset and clear add new warehouse form
    event.targe.reset();

    // hide form
    toggleAddWarehouseForm();
});

function addWarehouseCard(newWarehouse) {

    // create warehouse cards
    let div = document.createElement('div');
    let row = document.createElement('div');
    let title = document.createElement('h2');
    let bar = document.createElement('div');
    let maxCap = document.createElement('div');
    let deleteBtn = document.createElement('div');

    // stylize and dynamically grab card data
    div.className = 'col-5 border rounded mx-auto p-4';
    row.className = 'row';
    title.innerText = newWarehouse.warehouseName.name;

    // progress bar container
    bar.className = 'progress';
    bar.role = 'progressbar';

    // fill container based on current warehouse capacity and max capacity
    let percentage = (newWarehouse.capacity / newWarehouse.warehouseName.warehouseLocation.maxCapacity) * 100
    bar.innerHTML = `<div class='progress-bar bg-info' style='width:${percentage}%'">${Math.floor(percentage)}%</div>`

    maxCap.innerText = newWarehouse.warehouseName.warehouseLocation.maxCapacity;
    maxCap.className = 'text-end';      // position max cap at bottom-right of stat card

    deleteBtn.innerHTML =
    `<button class="btn btn-danger" id="deleteBtn${newWarehouse.id}" onclick="deleteWarehouse(${newWarehouse.id})">DELETE</button>`

    // build row
    row.appendChild(title);
    row.appendChild(bar);
    row.appendChild(maxCap);
    row.appendChild(deleteBtn);

    // set row within parent div
    div.appendChild(row);

    document.getElementById('warehouse-cards').appendChild(div);

    allWarehouses.push(newWarehouse);
}

function toggleAddWarehouseForm() {

    const newWarehouseFormContainer = document.getElementById('new-warehouse-form-container');
    const addWarehouseBtn = document.getElementById('add-warehouse-button');

    if (!showingAddWarehouseForm) {

        // show warehouse form
        newWarehouseFormContainer.setAttribute('class', 'container');

        // hide button
        addWarehouseBtn.setAttribute('class', 'd-none');
    }
    else {
        
        // hide warehouse form
        newWarehouseFormContainer.setAttribute('class', 'd-none');

        // show button
        addWarehouseBtn.setAttribute('class', 'btn btn-info text-light');
    }
}

async function createWarehouseLocation(newWarehouseLocation) {

    console.log(newWarehouseLocation);      // debug

    // send HTTP POST request
    let response = await fetch(warehousesApiUrl + '/locations', {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(newWarehouseLocation)
    });

    // only parse JSON if there *is* a body
    const text = await response.text();
    if (text) {
        let warehouseLocationJson = JSON.parse(text);
        newWarehouseLocation.id = warehouseLocationJson.id;
    }
    
    console.log('returned location: ' + newWarehouseLocation)
    return newWarehouseLocation.id;
}

async function createWarehouseName(newWarehouseName) {

    console.log(newWarehouseName)       // debug

    let response = await fetch(warehousesApiUrl + '/names', {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(newWarehouseName)
    });

    const text = await response.text();
    if (text) {
        let warehouseNameJson = JSON.parse(text);
        newWarehouseName.id = warehouseNameJson.id;
    }

    return newWarehouseName.id;
}

async function addWarehouse(newWarehouse) {

    console.log(newWarehouse)       // debug

    let response = await fetch(warehousesApiUrl, {
        method : 'POST',
        headers : {
            'Content-Type' : 'application/json'
        },
        body : JSON.stringify(newWarehouse)
    });

    const text = await response.text();
    if (text) {
        let newWarehouseJson = JSON.parse(text);
        newWarehouse.id = newWarehouseJson.id;
    }

    addWarehouseCard(newWarehouse);
}

async function deleteWarehouse(warehouseId) {

    let foundWarehouse;

    // loop through warehouses to find current selection
    allWarehouses.forEach(warehouse => {
        if (warehouse.id == warehouseId) {

            foundWarehouse = warehouse;
        }
    })
    
    let response = await fetch(warehousesApiUrl + '/id/' + warehouseId, {
        method : 'DELETE',
        headers : {
            'Content-Type' : 'application/json'
        }
    });

    if (response.status === 204) {
        deleteWarehouseName(foundWarehouse);
    }
}

async function deleteWarehouseName(warehouse) {

    let response = await fetch(warehousesApiUrl + '/names/id/' + warehouse.warehouseName.id, {
        method : 'DELETE',
        headers : {
            'Content-Type' : 'application/json'
        }
    });

    if (response.status === 204) {
        deleteWarehouseLocation(warehouse);
    }
}

async function deleteWarehouseLocation(warehouse) {
    
    let response = await fetch(warehousesApiUrl + '/locations/id/' + warehouse.warehouseName.warehouseLocation.id, {
        method : 'DELETE',
        headers : {
            'Content-Type' : 'application/json'
        }
    });

    if (response.status === 204) {
        removeWarehouseCard(warehouse);
    }
}

function removeWarehouseCard(warehouse) {
    console.log('Removing: ', warehouse);
}