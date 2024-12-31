package tn.esprit.exam.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idEtudiant;

    String nomETud;

    String prenomETud;
    long cin   ;

    String ecole;

    @Temporal(TemporalType.DATE)
    Date dateNaissance;

    @ManyToMany(mappedBy = "etudiants",cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
     Set<Reservation> reservations;

}
