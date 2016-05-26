
USE devise_db_ex1;
#########################  INSERT INTO   #####################################

INSERT INTO Devise (codeDevise,dChange,monnaie) VALUES ('E',1.2,'euro');
INSERT INTO Devise (codeDevise,dChange,monnaie) VALUES ('Y',0.2,'yen');
INSERT INTO Devise (codeDevise,dChange,monnaie) VALUES ('D',1.0,'dollar');
INSERT INTO Devise (codeDevise,dChange,monnaie) VALUES ('L',1.1,'livre');

# INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
#               VALUES ('Paris','fr','France','E');
# INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
#               VALUES ('Berlin','de','Allemagne','E');
# INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
#               VALUES ('Rome','it','Italie','E');
               
# INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
#               VALUES ('Londres','uk','Royaumes unis','L'); 
               
# INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
#               VALUES ('Washingtown','us','USA','D');     
# INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
#               VALUES ('Pekin','china','Chine','D');  
               
# INSERT INTO Pays (capitale,codePays,nomPays,ref_devise)
#               VALUES ('Tokyo','JP','Japon','Y');                 

###################### VERIFICATIONS ###########################################
show tables;
SELECT * FROM Devise;
# SELECT * FROM Pays;
