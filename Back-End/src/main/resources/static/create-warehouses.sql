-- DROP TABLE IF EXISTS warehouses;

CREATE TABLE IF NOT EXISTS warehouses(
	warehouses_id SERIAL PRIMARY KEY,
	FOREIGN KEY (warehouses_name_id) REFERENCES warehouse_name(warehouse_name_id),
	warehouses_capacity DECIMAL(10, 2)
);
