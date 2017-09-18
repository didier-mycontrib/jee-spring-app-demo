

USE minibank_db_ex1;


#########################  INSERT INTO   #####################################

INSERT INTO Adresse (codePostal,idAdr,rue,ville)  VALUES ('75000',1,'2 rue elle','Paris');
INSERT INTO Client (numClient,nom,prenom,dateNaissance,ref_adressePrincipale,password,telephone,email)
              VALUES (1,'Defrance','Didier','1969-07-11',1,'mypwd','0102030405','didier@ici_ou_la');
INSERT INTO Client (numClient,nom,prenom,dateNaissance,ref_adressePrincipale,password,telephone,email)
              VALUES (2,'Therieur','Alex','1969-07-12',1,'mypwd','010900909','alex@ici_ou_la');              
INSERT INTO Compte (label,numCpt,solde) VALUES ('compte courant',1,1200.0);
INSERT INTO Compte (label,numCpt,solde) VALUES ('compte codevi',2,50.0);   
INSERT INTO Compte (label,numCpt,solde) VALUES ('compte 3',3,52.0);  
INSERT INTO Operation (dateOp,label,montant,numOp,ref_compte)  VALUES ('2011-01-20','achat yy',-50.0,1,1);
INSERT INTO Operation (dateOp,label,montant,numOp,ref_compte)  VALUES ('2011-01-21','achat zz',-30.0,2,1);
INSERT INTO ClientCompte (numCli,numCpt) VALUES (1,1);
INSERT INTO ClientCompte (numCli,numCpt) VALUES (1,2);
INSERT INTO ClientCompte (numCli,numCpt) VALUES (2,3);

###################### VERIFICATIONS ###########################################
show tables;
SELECT * FROM Client;
SELECT * FROM Adresse;
SELECT * FROM Compte;
SELECT * FROM Operation;
SELECT * FROM ClientCompte;


