cd /d %~dp0
call set_env.bat
java -classpath %H2_CLASSPATH% org.h2.tools.RunScript -url %MY_H2_DB_URL%  -user sa -script ./generated/drop.sql -showResults
java -classpath %H2_CLASSPATH% org.h2.tools.RunScript -url %MY_H2_DB_URL%  -user sa -script ./generated/create.sql -showResults
java -classpath %H2_CLASSPATH% org.h2.tools.RunScript -url %MY_H2_DB_URL%  -user sa -script ../resources/META-INF/load.sql -showResults

pause