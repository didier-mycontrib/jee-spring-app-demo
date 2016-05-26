REM lancer si besoin le service mysql (net start mysql)
set MYSQL_HOME=C:\Prog\DB\MySql5
cd /d %~dp0
"%MYSQL_HOME%\bin\mysql"  -u root -p < create_devise_db.sql
pause