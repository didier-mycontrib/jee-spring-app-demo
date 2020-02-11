pour debug/verif:
---------------------------------------------
docker container exec -ti mysql-container sh
--------------------------------------------
	mysql -u root -p
		use customers;
		select * from Customer;
		select * from Address;
	exit
exit

docker container exec -ti postgres-container sh
--------------------------------------------
	psql -U postgres
		\c customers;
		select * from Customer;
		select * from Address;
	exit
exit

docker container exec -ti oracle-container sh
--------------------------------------------
        export ORACLE_HOME=/u01/app/oracle/product/11.2.0/xe
	export ORACLE_SID=XE
	/u01/app/oracle/product/11.2.0/xe/bin/sqlplus  system/oracle
		select * from Purchase;
	exit
exit