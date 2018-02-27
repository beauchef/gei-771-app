Feature: Connexion utilisateur

  Background:
    * url baseUrl

  Scenario: Connexion d'un utilisateur
    Given path '/connexion'
    When method GET
    Then status 200
    And match $ == { id: '#uuid' }
