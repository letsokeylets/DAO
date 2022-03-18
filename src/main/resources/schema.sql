create table CUSTOMERS
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(30),
    surname VARCHAR(30),
    age INTEGER,
    phone_number INTEGER
);
create table ORDERS
(
    id      SERIAL PRIMARY KEY,
    date    DATE,
    customer_id INTEGER REFERENCES CUSTOMERS (id),
    product_name VARCHAR(50),
    amount INTEGER
);
