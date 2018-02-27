Feature: Sondages disponibles

  Background:
    * url baseUrl
    * def randomUuid = function(){ return java.util.UUID.randomUUID() + '' }
    * def connexion = callonce read('connexion.feature')
    * def connectedUser = connexion.response

  Scenario: Requête de sondages avec un UUID invalide
    Given path '/usagers/1/sondage'
    When method GET
    Then status 400

  Scenario: Requête de sondages avec un usager inexistant
    Given path '/usagers/' + randomUuid() + '/sondage'
    When method GET
    Then status 404

  Scenario: Requête valide de sondages
    Given path '/usagers/' + connectedUser.id + '/sondage'
    When method GET
    Then status 200
    And match $ == [ { id: 1, description: '#string', questions: '#array' }, { id: 2, description: '#string', questions: '#array' } ]
