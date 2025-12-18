-- DROP TABLE IF EXISTS warehouse_name;

CREATE TABLE IF NOT EXISTS warehouse_name(
	warehouse_name_id SERIAL PRIMARY KEY,
	warehouse_name_name CHAR(255) NOT NULL,
	FOREIGN KEY (warehouse_name_loc_id) REFERENCES warehouse_location(warehouse_location_id)
);
