Feature: Réponses de l'utilisateur

  Background:
    * url baseUrl
    * def randomUuid = function(){ return java.util.UUID.randomUUID() + '' }
    * def connexion = callonce read('connexion.feature')
    * def connectedUser = connexion.response

  Scenario: Envoyer une réponse avec un UUID invalide
    Given path '/usagers/1/sondage/1/questions/1'
    And request { text: 'a' }
    When method POST
    Then status 400

  Scenario: Envoyer une réponse avec un usager inexistant
    Given path '/usagers/' + randomUuid() + '/sondage/1/questions/1'
    And request { text: 'a' }
    When method POST
    Then status 404
    And match response.message == "Usager introuvable."

  Scenario: Envoyer une réponse avec un sondage inexistant
    Given path '/usagers/' + connectedUser.id + '/sondage/5/questions/1'
    And request { text: 'a' }
    When method POST
    Then status 404
    And match response.message == "Sondage introuvable."

  Scenario: Envoyer une réponse avec une question inexistante
    Given path '/usagers/' + connectedUser.id + '/sondage/1/questions/22'
    And request { text: 'a' }
    When method POST
    Then status 404
    And match response.message == "Question introuvable."

  Scenario: Envoyer une réponse avec une question inexistante dont l'ID correspond à MAX de int
    Given path '/usagers/' + connectedUser.id + '/sondage/1/questions/2147483647'
    And request { text: 'a' }
    When method POST
    Then status 404
    And match response.message == "Question introuvable."

  Scenario: Envoyer une réponse avec une question inexistante dont l'ID est supérieur à MAX de int
    Given path '/usagers/' + connectedUser.id + '/sondage/1/questions/2147483648'
    And request { text: 'a' }
    When method POST
    Then status 400
    And match response.message == "Opération invalide."
    And match response.debugMessage contains "Vérifiez les paramètres d'entrée"

  Scenario: Envoyer une réponse avec la mauvaise case
    Given path '/usagers/' + connectedUser.id + '/sondage/1/questions/1'
    And request { text: 'A' }
    When method POST
    Then status 400
    And match response.message == "Requête invalide."
    And match response.debugMessage contains "Vérifiez le contenu de la requête"

  Scenario: Envoyer une réponse trop longue
    Given path '/usagers/' + connectedUser.id + '/sondage/1/questions/1'
    And request { text: 'aa' }
    When method POST
    Then status 400
    And match response.message == "Requête invalide."
    And match response.debugMessage contains "Vérifiez le contenu de la requête"

  Scenario: Envoyer une réponse valide
    Given path '/usagers/' + connectedUser.id + '/sondage/1/questions/1'
    And request { text: 'a' }
    When method POST
    Then status 201
    And match $ == { id: 1, question: { id: 1, text: '#string' }, text: 'a' }
