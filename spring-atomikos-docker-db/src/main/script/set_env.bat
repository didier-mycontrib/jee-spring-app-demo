REM set MVN_REPOSITORY=C:\ext\mvn-repository
set MVN_REPOSITORY=C:\Users\didier\.m2\repository
REM set MVN_REPOSITORY=C:\Users\formation\.m2\repository

#set MY_H2_DB_URL=jdbc:h2:./h2-data/customers
set MY_H2_DB_URL=jdbc:h2:~/backendApiDb;
set MY_H2_REALM_DB_URL=jdbc:h2:~/realmdb

set H2_VERSION=1.4.199
set H2_CLASSPATH=%MVN_REPOSITORY%\com\h2database\h2\%H2_VERSION%\h2-%H2_VERSION%.jar
