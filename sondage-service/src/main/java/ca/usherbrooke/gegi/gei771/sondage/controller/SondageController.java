package ca.usherbrooke.gegi.gei771.sondage.controller;

import ca.usherbrooke.gegi.gei771.messages.ReponseMessage;
import ca.usherbrooke.gegi.gei771.messages.ReponseTextMessage;
import ca.usherbrooke.gegi.gei771.messages.SondageMessage;
import ca.usherbrooke.gegi.gei771.messages.UserIdMessage;
import ca.usherbrooke.gegi.gei771.sondage.exception.QuestionNotFoundException;
import ca.usherbrooke.gegi.gei771.sondage.exception.SondageNotFoundException;
import ca.usherbrooke.gegi.gei771.sondage.exception.UsagerNotFoundException;
import ca.usherbrooke.gegi.gei771.sondage.model.Question;
import ca.usherbrooke.gegi.gei771.sondage.model.Usager;
import ca.usherbrooke.gegi.gei771.sondage.repository.UsagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import ca.usherbrooke.gegi.gei771.sondage.model.Reponse;
import ca.usherbrooke.gegi.gei771.sondage.model.Sondage;
import ca.usherbrooke.gegi.gei771.sondage.repository.ReponseRepository;
import ca.usherbrooke.gegi.gei771.sondage.repository.SondageRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Contrôleur REST qui définit les opérations qui seront disponibles dans le
 * service. Une référence au repository nécessaire est aussi injectée pour que
 * le contrôleur puisse faire des recherches ou des modifications aux sondages
 * dans sa source de données.
 */
@RestController
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
        // TODO
        // On doit ici déterminer comment sera généré l'identifiant unique de
        // l'utilisateur, puis le retourner.
        return new UserIdMessage(usagerRepository.save(new Usager()).getId());
    }

    /**
     * Retourne la liste de tous les sondages disponibles pour l'usager spécifié.
     *
     * @param userId
     *            L'identifiant unique de l'usager
     * @return La liste des sondages
     * @throws UsagerNotFoundException
     *             Comment gère-t-on les cas où un identifiant d'usager invalide a
     *             été fourni?
     */
    @GetMapping("/usagers/{userId}/sondage")
    public Iterable<SondageMessage> list(@PathVariable UUID userId) throws UsagerNotFoundException {
        // TODO
        // On doit ici valider que l'identifiant fourni est valide, puis retourner
        // la liste de tous les sondages qui sont disponibles pour cet usager.
        Usager usager = Optional.ofNullable(usagerRepository.findOne(userId))
            .orElseThrow(() -> new UsagerNotFoundException(String.format("Impossible de trouver l'usager avec l'identifiant '%s'.", userId.toString())));
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
                                                 @PathVariable int questionId, @RequestBody ReponseTextMessage reponseTextMessage)
            throws UsagerNotFoundException, SondageNotFoundException, QuestionNotFoundException {

        // TODO
        // On doit ici créer une nouvelle entité Reponse avec les données fournies
        // par l'utilisateur, puis la retourner.
        // Attention, il pourrait y avoir des cas d'erreur à gérer!
        Assert.notNull(reponseTextMessage, "La réponse ne peut pas être nulle.");
        Usager usager = Optional.ofNullable(usagerRepository.findOne(userId))
                .orElseThrow(() -> new UsagerNotFoundException(String.format("Impossible de trouver l'usager avec l'identifiant '%s'.", userId.toString())));
        Sondage sondage = Optional.ofNullable(sondageRepository.findOne(sondageId))
                .orElseThrow(() -> new SondageNotFoundException(String.format("Impossible de trouver le sondage %d.", sondageId)));
        Question question = sondage.getQuestions().stream()
                .filter(q -> q.getId() == questionId)
                .findFirst()
                .orElseThrow(() -> new QuestionNotFoundException(String.format("Impossible de trouver la question %d pour le sondage %d.", questionId, sondageId)));

        Reponse reponse = reponseRepository.save(new Reponse(usager, question, reponseTextMessage.getText()));
        return new ResponseEntity<>(conversionService.convert(reponse, ReponseMessage.class), HttpStatus.CREATED);
    }

    @GetMapping("/usagers")
    public Iterable<Usager> listUsagers() {
        return usagerRepository.findAll(); // TODO remove! Test...
    }

    @GetMapping("/usagers/{userId}/reponses")
    public Iterable<Reponse> listReponses(@PathVariable UUID userId) throws UsagerNotFoundException {
        Usager usager = Optional.ofNullable(usagerRepository.findOne(userId))
            .orElseThrow(() -> new UsagerNotFoundException(String.format("Impossible de trouver l'usager avec l'identifiant '%s'.", userId.toString())));
        return usager.getReponses(); // TODO remove! Test...
    }

}
