cd /d %~dp0
call set_env.bat

java -jar  %H2_CLASSPATH% -user "sa" -url jdbc:h2:~/orders


REM NB: penser à se déconnecter
REM    et options/session actives/arrêt pour éviter des futurs verrous/blocages

pause