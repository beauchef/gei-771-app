package ca.usherbrooke.gegi.gei771.sondage.controller;

import ca.usherbrooke.gegi.gei771.messages.ReponseMessage;
import ca.usherbrooke.gegi.gei771.messages.ReponseTextMessage;
import ca.usherbrooke.gegi.gei771.messages.SondageMessage;
import ca.usherbrooke.gegi.gei771.messages.UserIdMessage;
import ca.usherbrooke.gegi.gei771.sondage.exception.QuestionNotFoundException;
import ca.usherbrooke.gegi.gei771.sondage.exception.SondageNotFoundException;
import ca.usherbrooke.gegi.gei771.sondage.exception.UsagerNotFoundException;
import ca.usherbrooke.gegi.gei771.sondage.model.Question;
import ca.usherbrooke.gegi.gei771.sondage.model.Reponse;
import ca.usherbrooke.gegi.gei771.sondage.model.Sondage;
import ca.usherbrooke.gegi.gei771.sondage.model.Usager;
import ca.usherbrooke.gegi.gei771.sondage.repository.ReponseRepository;
import ca.usherbrooke.gegi.gei771.sondage.repository.SondageRepository;
import ca.usherbrooke.gegi.gei771.sondage.repository.UsagerRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Contrôleur REST qui définit les opérations qui seront disponibles dans le
 * service. Une référence au repository nécessaire est aussi injectée pour que
 * le contrôleur puisse faire des recherches ou des modifications aux sondages
 * dans sa source de données.
 */
@RestController
@Log4j
public class SondageController {

    private final ConversionService conversionService;

    private final UsagerRepository usagerRepository;

    private final SondageRepository sondageRepository;

    private final ReponseRepository reponseRepository;

    @Autowired
    public SondageController(final ConversionService conversionService, final UsagerRepository usagerRepository,
                             final SondageRepository sondageRepository, final ReponseRepository reponseRepository) {
        Assert.notNull(conversionService, "Le service de conversion ne peut pas être nul.");
        Assert.notNull(usagerRepository, "Le repository d'usagers ne peut pas être nul.");
        Assert.notNull(sondageRepository, "Le repository de sondages ne peut pas être nul.");
        Assert.notNull(reponseRepository, "Le repository de réponses ne peut pas être nul.");
        this.conversionService = conversionService;
        this.usagerRepository = usagerRepository;
        this.sondageRepository = sondageRepository;
        this.reponseRepository = reponseRepository;
    }

    /**
     * Établit la connexion. Il est nécessaire de faire cet appel en premier, car il
     * permet à l'usager d'obtenir son identifiant unique qu'il devra fournir avec
     * chacune de ces réponses.
     *
     * @return l'identifiant unique de l'utilisateur.
     */
    @GetMapping("/connexion")
    public UserIdMessage connect() {
        Usager usager = usagerRepository.save(new Usager());
        log.info(String.format("Connexion d'un usager: %s", usager.getId()));
        return new UserIdMessage(usager.getId());
    }

    /**
     * Retourne la liste de tous les sondages disponibles pour l'usager spécifié.
     *
     * @param userId
     *            L'identifiant unique de l'usager
     * @return La liste des sondages
     * @throws UsagerNotFoundException
     */
    @GetMapping("/usagers/{userId}/sondage")
    public Iterable<SondageMessage> list(@PathVariable UUID userId) throws UsagerNotFoundException {
        Usager usager = Optional.ofNullable(usagerRepository.findOne(userId))
            .orElseThrow(() -> new UsagerNotFoundException(userId));
        log.info(String.format("Demande de liste de sondage de l'usager: %s", usager.getId()));
        return StreamSupport.stream(sondageRepository.findAll().spliterator(), false)
            .map(s -> conversionService.convert(s, SondageMessage.class))
            .collect(Collectors.toList());
    }

    /**
     * Inscrit la réponse de l'usager dans la base de données.
     *
     * @param userId
     *            l'identifiant unique de l'usager.
     * @param sondageId
     *            l'identifiant unique du sondage.
     * @param questionId
     *            l'identifiant unique de la question.
     * @param reponseTextMessage
     *            la réponse fournie par l'utilisageur
     * @return la réponse telle qu'elle a été inscrite dans la base de données
     * @throws ???
     *             devrait-on gérer les cas où des valeurs erronées ont été
     *             fournies? Qu'en est-il d'une erreur de connexion à la BD?
     */
    @PostMapping("/usagers/{userId}/sondage/{sondageId}/questions/{questionId}")
    public ResponseEntity<ReponseMessage> answer(@PathVariable UUID userId, @PathVariable int sondageId,
                                                 @PathVariable int questionId,
                                                 @Valid @RequestBody ReponseTextMessage reponseTextMessage)
            throws UsagerNotFoundException, SondageNotFoundException, QuestionNotFoundException {

        Assert.notNull(reponseTextMessage, "La réponse ne peut pas être nulle.");
        Usager usager = Optional.ofNullable(usagerRepository.findOne(userId))
                .orElseThrow(() -> new UsagerNotFoundException(userId));
        Sondage sondage = Optional.ofNullable(sondageRepository.findOne(sondageId))
                .orElseThrow(() -> new SondageNotFoundException(sondageId));
        Question question = sondage.getQuestions().stream()
                .filter(q -> q.getId() == questionId)
                .findFirst()
                .orElseThrow(() -> new QuestionNotFoundException(sondageId, questionId));

        Reponse reponse = reponseRepository.save(new Reponse(usager, question, reponseTextMessage.getText()));
        return new ResponseEntity<>(conversionService.convert(reponse, ReponseMessage.class), HttpStatus.CREATED);
    }
}
