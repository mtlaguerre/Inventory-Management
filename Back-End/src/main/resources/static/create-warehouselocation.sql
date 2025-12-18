-- DROP TABLE IF EXISTS warehouse_location;

CREATE TABLE IF NOT EXISTS warehouse_location(
	warehouse_location_id SERIAL PRIMARY KEY,
	warehouse_location_location CHAR(255) NOT NULL,
	warehouse_location_max_capacity DECIMAL(10,2) NOT NULL
);
