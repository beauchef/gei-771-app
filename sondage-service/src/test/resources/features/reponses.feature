Feature: Réponses de l'utilisateur

  Background:
    * url baseUrl
    * def randomUuid = function(){ return java.util.UUID.randomUUID() + '' }
    * def connexion = callonce read('connexion.feature')
    * def connectedUser = connexion.response

  Scenario: Envoyer une réponse avec un UUID invalide
    Given path '/usagers/1/sondage/1/questions/1'
    And request { reponse: 'A' }
    When method POST
    Then status 400

  Scenario: Envoyer une réponse avec un usager inexistant
    Given path '/usagers/' + randomUuid() + '/sondage/1/questions/1'
    And request { reponse: 'A' }
    When method POST
    Then status 404
    And match response.message == "Usager introuvable."

  Scenario: Envoyer une réponse avec un sondage inexistant
    Given path '/usagers/' + connectedUser.id + '/sondage/5/questions/1'
    And request { reponse: 'A' }
    When method POST
    Then status 404
    And match response.message == "Sondage introuvable."

  Scenario: Envoyer une réponse avec une question inexistante
    Given path '/usagers/' + connectedUser.id + '/sondage/1/questions/22'
    And request { reponse: 'A' }
    When method POST
    Then status 404
    And match response.message == "Question introuvable."

  Scenario: Envoyer une réponse valide
    Given path '/usagers/' + connectedUser.id + '/sondage/1/questions/1'
    And request { text: 'A' }
    When method POST
    Then status 201
    And match $ == { id: 1, question: { id: 1, text: '#string' }, text: 'A' }
