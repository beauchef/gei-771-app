package ca.usherbrooke.gegi.gei771.sondage.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Définit une question faisant partie d'un sondage.
 */
@Entity
@NoArgsConstructor
public class Question {

    /**
     * Identifiant unique de la question.
     *
     * @return l'identifiant unique de cette question.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    private int id;

    /**
     * Sondage auquel appartient cette question.
     *
     * @return le sondage auquel appartient cette question.
     */
    @ManyToOne
    @JsonBackReference
    @Getter
    private Sondage sondage;

    /**
     * Contient le texte de la question (le libellé).
     *
     * @return le libellé de la question.
     */
    @Getter
    private String text;

    /**
     * Constructeur qui crée une nouvelle question en l'associant au sondage spécifié.
     *
     * @param sondage le sondage auquel appartient cette nouvelle question.
     */
    public Question(Sondage sondage, String text) {
        this.sondage = sondage;
        this.text = text;

        // il s'agit d'une relation bi-latérale
        this.sondage.addQuestion(this);
    }
}
