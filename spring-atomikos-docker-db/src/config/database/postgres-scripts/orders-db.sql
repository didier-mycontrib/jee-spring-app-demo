CREATE DATABASE orders;
\c orders; 
-- \c in postgres like USE in mysql

DROP TABLE IF EXISTS order_line;
DROP TABLE IF EXISTS "orders";
DROP TABLE IF EXISTS product_ref;

-- SERIAL or BIGSERIAL postgres TYPE are auto_increment Integer

CREATE TABLE product_ref
	(product_id integer primary key,
     label VARCHAR(128),
     price real);

CREATE TABLE "orders"
	(order_id SERIAL  primary key,
     order_date VARCHAR(32),
     customer_id integer,
     total_price real);
     
CREATE TABLE order_line
	(order_id integer,
	 line_number integer,
     product_ref integer,
     quantity integer,
     primary key(order_id,line_number)); 
     
ALTER TABLE order_line ADD CONSTRAINT validProductRef
FOREIGN KEY(product_ref) REFERENCES product_ref(product_id);

ALTER TABLE order_line ADD CONSTRAINT validOrderId
FOREIGN KEY(order_id) REFERENCES "orders"(order_id);

INSERT INTO product_ref(product_id,label,price) 
   VALUES (1,'smartPhone xy' , 120.5),
          (2,'micro SD memory card' , 8);

INSERT INTO "orders"(order_date,customer_id,total_price) 
   VALUES ('2016-10-18' , 1 , 136.5);
   
INSERT INTO order_line(order_id,line_number,product_ref,quantity) 
   VALUES (1 , 1 , 1 , 1),
          (1 , 2 , 2 , 2);   
   

select * from product_ref;
select * from "orders";
select * from order_line;
