cd /d "%~dp0/target"
REM NB: in META-INF/MANIFEST.MF of .jar
REM Main-Class: org.springframework.boot.loader.PropertiesLauncher
REM Start-Class: org.mycontrib.tp.serverRest.ServerRestApplication
java -jar restDeviseApiOAuth2-0.0.1-SNAPSHOT.jar
pause