DROP DATABASE IF EXISTS devises;
CREATE DATABASE IF NOT EXISTS devises;
USE devises;

DROP TABLE IF EXISTS devise;

create table devise (code varchar(32) not null, 
					name varchar(64), 
					taux_change double check (taux_change>=0), 
					primary key (code));

INSERT INTO devise(code , name , taux_change) 
   VALUES ('USD' , 'dollar' , 1.0),
		  ('EUR' , 'euro' , 0.9),
          ('GBP' , 'livre' , 0.82),
          ('JPY' , 'yen' , 123.3);

select * from devise;
