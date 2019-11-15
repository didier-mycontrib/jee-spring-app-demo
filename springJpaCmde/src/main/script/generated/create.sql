create table AdresseDuClient (numClient bigint not null, codePostal varchar(12), ville varchar(32), voie varchar(64), primary key (numClient))
create table Categorie (numero bigint generated by default as identity, label varchar(64), primary key (numero))
create table categorie_produits (categorie bigint not null, produit bigint not null)
create table Client (numero bigint generated by default as identity, nom varchar(32), primary key (numero))
create table Commande (numero bigint generated by default as identity, dateCmde date, client bigint, primary key (numero))
create table LigneCommande (numLigneCmde bigint generated by default as identity, quantite integer, cmde bigint, prod bigint, primary key (numLigneCmde))
create table Produit (numero bigint generated by default as identity, label varchar(64), prix double, primary key (numero))
alter table categorie_produits add constraint UK_t2thmmva79k2qorah9686grnu unique (produit)
alter table categorie_produits add constraint FKm4lbxecc2ye6af7wxpvmqdv9a foreign key (produit) references Produit
alter table categorie_produits add constraint FKaxg86llf661m7qdqbikuovtc9 foreign key (categorie) references Categorie
alter table Commande add constraint FKg1ifakhtryqjynk78wf5ux8lb foreign key (client) references Client
alter table LigneCommande add constraint FK6n2xallymc53sm3kppqfu6vf7 foreign key (cmde) references Commande
alter table LigneCommande add constraint FKmoq2ochp42yrb5ouwgo4u1bvy foreign key (prod) references Produit
