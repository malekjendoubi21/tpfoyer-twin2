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
public class Categorie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idCategorie;

    String nomCategorie;


    @ManyToMany( cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<Produit> produits;
}
