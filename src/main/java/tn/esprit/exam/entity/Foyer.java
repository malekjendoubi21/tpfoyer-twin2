package tn.esprit.exam.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idFoyer;

    String nomFoyer;

    long capaciteFoyer;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "foyer")
    @JsonIgnore
     Universite universite;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "foyer")
     Set<Bloc> blocs= new HashSet<Bloc>();
}
