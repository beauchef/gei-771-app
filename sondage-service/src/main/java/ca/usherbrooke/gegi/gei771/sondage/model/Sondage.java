package ca.usherbrooke.gegi.gei771.sondage.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

/**
 * Entité qui définit un sondage.
 */
@Entity
@NoArgsConstructor
public class Sondage {

    /**
     * Identifiant unique du sondage.
     *
     * @return l'identifiant du sondage
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    private int id;

    /**
     * Description du sondage.
     *
     * @param description la description du sondage
     * @return la description du sondage
     */
    @Getter
    @Setter
    private String description;

    /** Les questions contenues dans ce sondage. */
    @OneToMany(mappedBy = "sondage")
    @JsonManagedReference
    private List<Question> questions;

    /**
     * Constructeur qui crée un sondage initialisé avec une description
     * (l'identifiant est toujours généré automatiquement par la couche de
     * persistence).
     *
     * @param description
     *            la description du sondage.
     */
    public Sondage(String description) {
        this.description = description;
    }

    /**
     * Obtenir les questions du sondage.
     *
     * @return une copie en lecture-seule des questions faisant partie de ce sondage.
     */
    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    /**
     * Ajouter une question à un sondage.
     *
     * @param question Question à ajouter.
     */
    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}
