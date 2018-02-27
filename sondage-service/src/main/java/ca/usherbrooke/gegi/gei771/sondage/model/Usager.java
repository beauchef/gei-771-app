package ca.usherbrooke.gegi.gei771.sondage.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author beauchef on 2018-02-04.
 */
@Entity
@NoArgsConstructor
public class Usager {

    /**
     * ID de l'usager.
     *
     * @return L'ID de l'usager.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Getter
    private UUID id;

    /**
     * Les réponses de cet utilisateur.
     **/
    @OneToMany(mappedBy = "usager")
    @JsonManagedReference
    private List<Reponse> reponses = new ArrayList<>();

    /**
     * Liste de réponses de cet utilisateur.
     *
     * @return une copie en lecture-seule des réponses données par cet utilisateur.
     */
    public List<Reponse> getReponses() {
        return Collections.unmodifiableList(reponses);
    }

    /**
     * Ajouter une réponse de sondage à cet utilisateur.
     *
     * @param reponse Réponse à rajouter.
     */
    public void addReponse(Reponse reponse) {
        this.reponses.add(reponse);
    }
}
