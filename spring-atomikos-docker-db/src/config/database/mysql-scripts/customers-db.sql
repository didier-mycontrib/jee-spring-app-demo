DROP DATABASE IF EXISTS customers;
CREATE DATABASE IF NOT EXISTS customers;
USE customers;

DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS address;

CREATE TABLE address
	(id integer primary key auto_increment,
     number_and_street VARCHAR(255),
     zip VARCHAR(32),
     town VARCHAR(64),
     country VARCHAR(64));

CREATE TABLE customer
	(id integer primary key auto_increment,
     first_name VARCHAR(64),
     last_name VARCHAR(64),
     address_id integer,
     email VARCHAR(255),
     phone_number VARCHAR(32));
     
ALTER TABLE customer ADD CONSTRAINT validAddressId
FOREIGN KEY(address_id) REFERENCES address(id); 

INSERT INTO address(number_and_street , zip , town , country) 
   VALUES ('12 rue elle ' , '75000' , 'Paris' , 'France');

INSERT INTO customer(first_name , last_name , address_id ,email , phone_number) 
   VALUES ('alex' , 'Therieur' , 1 , 'alex-therieur@iciOulaBas.fr' , '0102030405');


select * from address;
select * from customer;
