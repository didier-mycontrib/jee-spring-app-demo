INSERT INTO produit(numero,label,prix)  VALUES  (1,'cahier',1.55);
INSERT INTO produit(numero,label,prix)  VALUES  (2,'stylo',2.1);
INSERT INTO produit(numero,label,prix)  VALUES  (3,'ordinateur',602.1);
INSERT INTO produit(numero,label,prix)  VALUES  (4,'imprimante',103.1);
INSERT INTO produit(numero,label,prix)  VALUES  (5,'chose',5.1);

INSERT INTO categorie(numero,label) VALUES  (1,'fourniture de bureau');
INSERT INTO categorie_produits(categorie,produit) VALUES(1,1);
INSERT INTO categorie_produits(categorie,produit) VALUES(1,2);

INSERT INTO categorie(numero,label) VALUES  (2,'informatique');
INSERT INTO categorie_produits(categorie,produit) VALUES(2,3);
INSERT INTO categorie_produits(categorie,produit) VALUES(2,4);


INSERT INTO client(numero,nom)  VALUES  (1,'toto');
INSERT INTO client(numero,nom)  VALUES  (2,'titi');

INSERT INTO adresseDuClient(numClient,voie,codePostal,ville)  VALUES  (1,'12 rue elle' , '75000' , 'Paris');

INSERT INTO commande(numero,dateCmde)  VALUES  (1,'2018-01-01');
                   