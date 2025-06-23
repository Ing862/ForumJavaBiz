------Po pobraniu Wildfly------

Zakładanie profilu dla admina servera wildfly:
/bin/add-user.bat

Pliki konfiguracji do podmienienia:
/standalone/configuration/standalone.xml

Folder w tym katalogu to folder którzy trzeba dodać do tej ścieżki: /modules/system/layers/base/org/(o tutaj)

Dla zgodności przy konfiguracji servera postgresql:
nazwa serwera: forumdb
nazwa użytkownika: postgres
hasło: admin