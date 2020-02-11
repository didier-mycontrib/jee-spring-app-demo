CREATE DATABASE purchases;
\c purchases; 
-- \c in postgres like USE in mysql

DROP TABLE IF EXISTS purchase;


CREATE TABLE purchase
	(purchase_id SERIAL primary key,
     purchase_date_time VARCHAR(32),
     customer_id integer,
     amount real);
     

INSERT INTO purchase(purchase_date_time,customer_id,amount) 
   VALUES ('2016-10-18-10:30:00' , 1 , 136.5);
   

select * from purchase;