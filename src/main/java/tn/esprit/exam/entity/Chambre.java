package tn.esprit.exam.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idChambre;

    long numeroChambre;

    @Enumerated(EnumType.STRING)
    TypeChambre typeChambre;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "chambre")
    private Set<Reservation> reservations= new HashSet<Reservation>();

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Bloc bloc;

}
