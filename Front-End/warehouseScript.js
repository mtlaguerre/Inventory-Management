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

    // build row
    row.appendChild(title);
    row.appendChild(bar);
    row.appendChild(maxCap);

    // set row within parent div
    div.appendChild(row);

    document.getElementById('warehouse-cards').appendChild(div);

    allWarehouses.push(newWarehouse);
}