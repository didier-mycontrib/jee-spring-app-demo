

DROP TABLE IF EXISTS Pays;
DROP TABLE IF EXISTS Devise;

CREATE TABLE Devise(
	codeDevise VARCHAR(8),
	monnaie VARCHAR(64),
	dChange double,
	PRIMARY KEY(codeDevise));	 

CREATE TABLE Pays(
	codePays VARCHAR(8),
	capitale VARCHAR(64),
	nomPays VARCHAR(64),
	ref_devise VARCHAR(64),
	PRIMARY KEY(codePays));	 


ALTER TABLE Pays ADD CONSTRAINT Pays_avec_devise_valide 
FOREIGN KEY (ref_devise) REFERENCES Devise(codeDevise);

INSERT INTO Devise (codeDevise,dChange,monnaie) VALUES ('EUR',1.11570,'euro');
INSERT INTO Devise (codeDevise,dChange,monnaie) VALUES ('JPY',0.00961816 ,'yen');
INSERT INTO Devise (codeDevise,dChange,monnaie) VALUES ('USD',1.0,'dollar');
INSERT INTO Devise (codeDevise,dChange,monnaie) VALUES ('GBP',1.32940,'livre');

INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
               VALUES ('Paris','fr','France','EUR');
 INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
               VALUES ('Berlin','de','Allemagne','EUR');
 INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
               VALUES ('Rome','it','Italie','EUR');      
 INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
               VALUES ('Londres','uk','Royaumes unis','GBP');           
 INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
               VALUES ('Washingtown','us','USA','USD');     
 INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
               VALUES ('Pekin','china','Chine','USD');         
 INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
               VALUES ('Tokyo','JP','Japon','JPY');                 

SELECT * FROM Devise;
SELECT * FROM Pays;
