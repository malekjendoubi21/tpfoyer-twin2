package tn.esprit.exam.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bloc {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    long idBloc;

String nomBloc;

long capaciteBloc;

    @OneToMany(mappedBy = "bloc")
    @JsonIgnore
     Set<Chambre> chambres;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnore
     Foyer foyer;
}

