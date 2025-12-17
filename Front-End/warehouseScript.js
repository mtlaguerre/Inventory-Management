warehousesApiUrl = "http://localhost:8080/warehouses";
let allWarehouses = [];

document.addEventListener('DOMContentLoaded', () => {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange= () => {

        if (xhr.readyState === 4) {

            let warehouses = JSON.parse(xhr.responseText);

            // add warehouses to table
            warehouses.forEach(newWarehouse => {
                addWarehouseComponent(newWarehouse);
            });
            
        }

    };

    xhr.open('GET', warehousesApiUrl);

    xhr.send();

});

function addWarehouseComponent(newWarehouse) {

    // create warehouse cards
    let div = document.createElement('div');
    let row = document.createElement('div');
    let title = document.createElement('h2');
    let bar = document.createElement('div');
    let maxCap = document.createElement('div');

    div.className = 'col-6 border';
    row.className = 'row';
    title.innerText = newWarehouse.warehouseName.name;
    bar.innerText = '[BAR HERE]';
    maxCap.innerText = newWarehouse.warehouseName.warehouseLocation.maxCapacity;
    maxCap.className = 'text-end';

    // build row
    row.appendChild(title);
    row.appendChild(bar);
    row.appendChild(maxCap);

    // set row within parent div
    div.appendChild(row);

    document.getElementById('warehouse-cards').appendChild(div);

    allWarehouses.push(newWarehouse);
}