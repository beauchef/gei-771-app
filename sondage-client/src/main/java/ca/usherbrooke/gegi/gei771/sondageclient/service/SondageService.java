package ca.usherbrooke.gegi.gei771.sondageclient.service;

import ca.usherbrooke.gegi.gei771.messages.UserIdMessage;
import ca.usherbrooke.gegi.gei771.sondageclient.exception.SondageServerException;
import ca.usherbrooke.gegi.gei771.sondageclient.util.SondageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * @author beauchef on 2018-02-25.
 */
@Service
public class SondageService {

    final RestTemplate restTemplate;
    final SondageUrl sondageUrl;

    @Autowired
    public SondageService(final RestTemplate restTemplate, final SondageUrl sondageUrl) {
        this.restTemplate = restTemplate;
        this.sondageUrl = sondageUrl;
    }

    public UserIdMessage connextion() throws SondageServerException {
        ResponseEntity<UserIdMessage> response =
                checkResponse(restTemplate.getForEntity(sondageUrl.connexion(), UserIdMessage.class));
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
