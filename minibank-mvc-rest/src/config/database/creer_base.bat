REM lancer si besoin le service mysql (net start mysql)
set MYSQL_HOME=C:\Program Files\MySQL\MySQL Server 5.7
cd /d %~dp0
"%MYSQL_HOME%\bin\mysql"  -u root -p < create_minibank_db.sql
pause