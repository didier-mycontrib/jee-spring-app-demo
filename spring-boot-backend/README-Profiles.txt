Profile actuel (automatiquement activé ) : "dev" .
--------------------------------------------------

Idées de profiles unitaires fondamentaux:
----------------------------
- embeddedDb (ex:H2 or mem) ou bien remoteDb (ex:mysql_ou_cassandra)
  avec eventuellement -fastMemDb comme complement eventuel de embeddedDB 
- reInit (Schema and DefaultDataSet) (ddl-auto=create) ou bien noReInit (Schema and DefaultDataSet) (ddl-auto=none)
  [ NB: si noReInit, prévoir éventuellement une post-activation via menu de l'appli ou autre ]
        ou bien une pre_initialisation via script .bat/.sh ou autre ]
- permitAllSecurity ou_bien basicSecurity(mem+file/env) ou_bien appDbSecurity(jdbc,...) ou_bien orgSecurity (ex:oauth2)

Idées de profiles unitaires annexes/secondaires:
-----------------------------------------------
- cors? , noCors ?...
- swagger? , noSwagger?
- metric? , noMetric?
- ...

Et pour certaines applications seulement:
-----------------------------------------
- cluster? / noCluster ? , 
- jta? , noJta?...

   
Idées de profiles composés:
------------------
-DEV ou PROD
---------------
DevOps :
a) reinitLocal (dev, unitTest, ...)
b) standAlone (with embedded_h2_or...)
c) basicProd (without cluster , with docker_container, ...)
d) advancedProd (with cluster , rancher/kubernetes ou ..., ...)

---> 
(mega/composite) profiles revisités :
    -DEV (noSecurity or basicSecurity(default) , embeddedDB , reInit)
    -STANDALONE (basicSecurity or appDbSecurity(default/h2) , embeddedDb , noReInit)
    -PROD (appDbSecurity(default) or orgSecurity , remoteDb , noReInit)
        