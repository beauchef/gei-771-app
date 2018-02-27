# gei-771-app
GEI 771 - Problématique

# Information #

Le projet comprend 3 projets Maven:

* `sondage-parent`: Le projet parent
* `sondage-messages`: Un projet comportant des messages utilisés par le client et le service.
* `sondage-service`: Le projet principal - celui du service pour lequel on nous avait donné une base
* `sondage-client`: Le projet client - un petit projet pour tester le service

Les 2 applications (service et client) sont des applications Spring Boot (raison pour laquelle ils partagent le même parent!).

## Pour builder ##

`mvn clean install`

## Pour exécuter ##

`mvn spring-boot:run`

# Tests #

L'application client peut être utilisée pour tester manuellement le service.

L'appication [Postman](https://www.getpostman.com/) peut aussi être utilisée pour faire des tests manuels plus en profondeur.

Finalement, des tests d'intégration ont été fournis. Ceux-ci ont été réalisés à l'aide de [Karate](https://github.com/intuit/karate) et sont exécutés chaque fois que l'application est compilée.
La [démo de Karate](https://github.com/intuit/karate/tree/master/karate-demo) a été très utile pour configurer le tout correctement. (Une version "mockée" du service démarre pour permettre les tests de Karate)

Les scénarios de tests de Karate sont disponibles dans le répertoire: `sondage-service\src\test\resources\features`
