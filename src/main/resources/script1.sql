select product_name
from CUSTOMERS
         join ORDERS ON CUSTOMERS.id = ORDERS.customer_id
where lower(name) = lower('alexey');