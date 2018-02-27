package ca.usherbrooke.gegi.gei771.sondage.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Définit la réponse fournie par un usager à une question d'un sondage
 *
 * TODO: est-ce que les SETTER sont nécessaires?
 */
@Entity
@NoArgsConstructor
public class Reponse {

    /**
     * Identifiant unique de la réponse.
     *
     * @return the id
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;

    /**
     * Identifiant unique de l'utilisateur ayant fourni cette réponse.
     *
     * @param usager the userId to set
     * @return the userId
     **/
    @ManyToOne
    @JsonBackReference
    @Getter
    @Setter
    private Usager usager;

    /**
     * Question qui a été posée à l'utilisateur et qui a motivé cette réponse.
     *
     * @param question the question to set
     * @return the question
     **/
    @ManyToOne
    @Getter
    @Setter
    private Question question;

    /**
     * Texte contenant la réponse fournie par l'usager
     *
     * @param text the text to set
     * @return the text
     **/
    @Getter
    @Setter
    private String text;

    /**
     * Crée une nouvelle réponse en l'associant à la question spécifiée.
     *
     * @param usager    L'utilisateur qui a répondu à la question.
     * @param question  La question à laquelle cette réponse s'applique.
     * @param text      Le texte de la réponse.
     */
    public Reponse(Usager usager, Question question, String text) {
        this.usager = usager;
        this.question = question;
        this.text = text;
    }
}
