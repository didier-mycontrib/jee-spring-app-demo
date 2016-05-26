si fichier de meme nom dans /src/main/resources et /src/test/resources alors ...


... comportement selon framework qui interprète:

si fichier recherché dans l'ensemble du classpath alors conflit potentiel
(ex: /META-INF/persistence.xml est chargé 2 fois si dans  /src/main/resources et /src/test/resources
     avec conflits ...)
     
si fichier recherché en relatif par rapport à une classe de /src/test/java
alors OK (la version "test" l'emporte sur la version "main" , ex: fichier spring) .     