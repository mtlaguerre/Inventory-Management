warehousesApiUrl = "http://localhost:8080/warehouses";
let allWarehouses = [];

document.addEventListener('DOMContentLoaded', () => {

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange= () => {

        if (xhr.readyState === 4) {

            let warehouses = JSON.parse(xhr.responseText);

            // add warehouses to table
            warehouses.forEach(newWarehouse => {
                addWarehouseToList(newWarehouse);
            });
            
        }

    };

    xhr.open('GET', warehousesApiUrl);

    xhr.send();

});

function addWarehouseComponent(newWarehouse) {

    // create warehouse card
}