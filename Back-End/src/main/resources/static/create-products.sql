-- DROP TABLE IF EXISTS products;

CREATE TABLE IF NOT EXISTS products(
	products_id SERIAL PRIMARY KEY,
	FOREIGN KEY (products_rm) REFERENCES product_rm(product_rm_rm),
	products_capacity DECIMAL(10, 2) NOT NULL,
	FOREIGN KEY (products_wh) REFERENCES warehouses(warehouses_id)
);
