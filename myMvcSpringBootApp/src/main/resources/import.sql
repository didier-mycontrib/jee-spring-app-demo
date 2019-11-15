INSERT INTO devise (code_devise,d_change,monnaie) VALUES ('EUR',1.11570,'euro');
INSERT INTO devise (code_devise,d_change,monnaie) VALUES ('JPY',0.00961816 ,'yen');
INSERT INTO devise (code_devise,d_change,monnaie) VALUES ('USD',1.0,'dollar');
INSERT INTO devise (code_devise,d_change,monnaie) VALUES ('GBP',1.32940,'livre');

INSERT INTO client (num_client,nom,prenom,date_naissance) VALUES (1,'Defrance','Didier','1969-07-11');
INSERT INTO client (num_client,nom,prenom,date_naissance) VALUES (2,'Therieur','Alex','1980-05-20');

INSERT INTO compte (label,num_cpt,solde) VALUES ('compte courant',1,1200.0);
INSERT INTO compte (label,num_cpt,solde) VALUES ('compte codevi',2,50.0);    
INSERT INTO compte (label,num_cpt,solde) VALUES ('compte 3',3,850.0); 

INSERT INTO operation (date_op,label,montant,num_op,ref_compte)  VALUES ('2011-01-20','achat yy',-50.0,1,1);
INSERT INTO operation (date_op,label,montant,num_op,ref_compte)  VALUES ('2011-01-21','achat zz',-30.0,2,1);

INSERT INTO client_compte (num_cli,num_cpt) VALUES (1,1);
INSERT INTO client_compte (num_cli,num_cpt) VALUES (1,2);
INSERT INTO client_compte (num_cli,num_cpt) VALUES (2,3);
