-- DROP TABLE IF EXISTS product_rm;

CREATE TABLE IF NOT EXISTS product_rm(
	product_rm_rm INTEGER PRIMARY KEY,
	product_rm_name CHAR(50) NOT NULL,
	product_rm_description CHAR(255) NOT NULL,
	product_rm_max_capacity DECIMAL(10, 2) NOT NULL
);
