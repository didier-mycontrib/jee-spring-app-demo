DROP DATABASE IF EXISTS purchases;
CREATE DATABASE IF NOT EXISTS purchases;
USE purchases;


DROP TABLE IF EXISTS purchase;


CREATE TABLE purchase
	(purchase_id integer primary key auto_increment,
     purchase_date_time VARCHAR(32),
     customer_id integer,
     amount double);
     

INSERT INTO purchase(purchase_date_time,customer_id,amount) 
   VALUES ('2016-10-18-10:30:00' , 1 , 136.5);
   

select * from purchase;

