
CREATE DATABASE IF NOT EXISTS devise_db_ex1;
USE devise_db_ex1;


DROP TABLE IF EXISTS Pays;
DROP TABLE IF EXISTS Devise;


######################## CREATE  TABLE ########################################

CREATE TABLE Devise(
	codeDevise VARCHAR(8),
	monnaie VARCHAR(64),
	dChange double,
	
	PRIMARY KEY(codeDevise));	 

# CREATE TABLE Pays(
#	codePays VARCHAR(8),
#	capitale VARCHAR(64),
#	nomPays VARCHAR(64),
#	ref_devise VARCHAR(64),
#	PRIMARY KEY(codePays));	 



#######################   FOREIGN KEY       ####################################



# ALTER TABLE Pays ADD CONSTRAINT Pays_avec_devise_valide 
# FOREIGN KEY (ref_devise) REFERENCES Devise(codeDevise);


###################### VERIFICATIONS ###########################################
show tables;





