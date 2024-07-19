CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(320) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE customers (
	id BIGINT NOT NULL AUTO_INCREMENT,
	user_id BIGINT NOT NULL UNIQUE,
	name VARCHAR(35) NOT NULL,
	last_name VARCHAR(40) NOT NULL,
	cellphone VARCHAR(15) NOT NULL UNIQUE,
	cellphone_2 VARCHAR(15),
	external_id BINARY(16) NOT NULL,
	created_at DATETIME NOT NULL,
	modified_at DATETIME,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE addresses (
	id BIGINT NOT NULL AUTO_INCREMENT,
	customer_id BIGINT NOT NULL,
	address_1 VARCHAR(80) NOT NULL,
	address_2 VARCHAR(35),
	city VARCHAR(35) NOT NULL,
	state CHAR(2) NOT NULL,
	postal_code VARCHAR(10) NOT NULL,
	country VARCHAR(35) NOT NULL,
	external_id BINARY(16) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE payments_details (
	id BIGINT NOT NULL AUTO_INCREMENT,
	order_id BIGINT NOT NULL,
	provider VARCHAR(40) NOT NULL,
	status ENUM(
		'Pending Payment',
		'Processing',
		'Paid',
		'Failed',
		'Cancelled',
		'Refunded',
		'Partially Refunded',
		'On Hold',
		'Completed',
		'Authorized',
		'Declined'
		) NOT NULL,
	created_at DATETIME NOT NULL,
	modified_at DATETIME,
	PRIMARY KEY (id)
);

CREATE TABLE orders (
	id BIGINT NOT NULL AUTO_INCREMENT,
	customer_id BIGINT NOT NULL,
	address_id BIGINT NOT NULL,
	payment_id BIGINT NOT NULL,
	total DECIMAL(12,2) NOT NULL,
	created_at DATETIME NOT NULL,
	modified_at DATETIME,
	annotation VARCHAR(150),
	external_id BINARY(16) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (customer_id) REFERENCES customers(id),
	FOREIGN KEY (address_id) REFERENCES addresses(id),
	FOREIGN KEY (payment_id) REFERENCES payments_details(id)
);

CREATE TABLE product_inventory (
	id BIGINT NOT NULL AUTO_INCREMENT,
	quantity INT NOT NULL,
	created_at DATETIME NOT NULL,
	modified_at DATETIME,
	deleted_at DATETIME,
	PRIMARY KEY (id)
);

CREATE TABLE shopping_sessions (
	id BIGINT NOT NULL AUTO_INCREMENT,
	customer_id BIGINT NOT NULL,
	total DECIMAL(12,2),
	created_at DATETIME NOT NULL,
	modified_at DATETIME,
	PRIMARY KEY (id),
	FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE products (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(160) NOT NULL UNIQUE,
	description TEXT NOT NULL,
	main_picture_id BIGINT NOT NULL,
	inventory_id BIGINT NOT NULL,
	created_at DATETIME NOT NULL,
	modified_at DATETIME,
	external_id BINARY(16) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (inventory_id) REFERENCES product_inventory(id)
);

CREATE TABLE cart_items (
	id BIGINT NOT NULL AUTO_INCREMENT,
	session_id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	quantity INT NOT NULL,
	created_at DATETIME NOT NULL,
	modified_at DATETIME,
	PRIMARY KEY (id),
	FOREIGN KEY (session_id) REFERENCES shopping_sessions(id),
	FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE categories (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(40) NOT NULL UNIQUE,
	description VARCHAR(80) UNIQUE,
	PRIMARY KEY (id)
);

CREATE TABLE orders_items (
	id BIGINT NOT NULL AUTO_INCREMENT,
	order_id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	quantity INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (order_id) REFERENCES orders(id),
	FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE categories_by_products (
	category_id BIGINT NOT NULL,
	product_id BIGINT NOT NULL,
	PRIMARY KEY (category_id, product_id),
	FOREIGN KEY (category_id) REFERENCES categories(id),
	FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE product_pictures (
	id BIGINT NOT NULL AUTO_INCREMENT,
	product_id BIGINT NOT NULL,
	url VARCHAR(700) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(product_id) REFERENCES products(id)
);

ALTER TABLE products
ADD FOREIGN KEY (main_picture_id) REFERENCES product_pictures(id);

ALTER TABLE payments_details
ADD FOREIGN KEY (order_id) REFERENCES orders(id);