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
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idReservation;
    String numReservation;
    @Temporal(TemporalType.DATE)

    Date dateAnneeUniv;
    @Temporal(TemporalType.DATE)

    Date finAnneeUnive;

    boolean estValide;

    @ManyToMany(cascade = CascadeType.ALL)
     Set<Etudiant> etudiants;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
     Chambre chambre;



}
