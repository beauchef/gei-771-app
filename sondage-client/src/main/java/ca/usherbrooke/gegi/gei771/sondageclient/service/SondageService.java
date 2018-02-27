package ca.usherbrooke.gegi.gei771.sondageclient.service;

import ca.usherbrooke.gegi.gei771.messages.ReponseMessage;
import ca.usherbrooke.gegi.gei771.messages.ReponseTextMessage;
import ca.usherbrooke.gegi.gei771.messages.SondageMessage;
import ca.usherbrooke.gegi.gei771.messages.UserIdMessage;
import ca.usherbrooke.gegi.gei771.sondageclient.exception.SondageServerException;
import ca.usherbrooke.gegi.gei771.sondageclient.util.SondageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author beauchef on 2018-02-25.
 */
@Service
public class SondageService {

    private final RestTemplate restTemplate;
    private final SondageUrl sondageUrl;

    @Autowired
    public SondageService(final RestTemplate restTemplate, final SondageUrl sondageUrl) {
        this.restTemplate = restTemplate;
        this.sondageUrl = sondageUrl;
    }

    public UserIdMessage connexion() throws SondageServerException {
        ResponseEntity<UserIdMessage> response = restTemplate.getForEntity(sondageUrl.connexion(), UserIdMessage.class);
        checkResponse(response);
        return response.getBody();
    }

    public List<SondageMessage> getSondages(String userId) throws SondageServerException {
        ResponseEntity<List<SondageMessage>> response = restTemplate.exchange(sondageUrl.sondages(userId),
            HttpMethod.GET, null, new ParameterizedTypeReference<List<SondageMessage>>() {});
        checkResponse(response);
        return response.getBody();
    }

    public ReponseMessage setReponse(String userId, int sondageId, int questionId, String responseText) throws SondageServerException {
        HttpEntity<ReponseTextMessage> request = new HttpEntity<>(new ReponseTextMessage(responseText));
        ResponseEntity<ReponseMessage> response = restTemplate.postForEntity(sondageUrl.reponse(userId, sondageId, questionId), request, ReponseMessage.class);
        checkResponse(response);
        return response.getBody();
    }

    private <T> ResponseEntity<T> checkResponse(ResponseEntity<T> response) throws SondageServerException {
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            throw new SondageServerException("Le service de sondage a rencontré un problème: "
                + response.getStatusCode().getReasonPhrase());
        }
        return response;
    }
}
