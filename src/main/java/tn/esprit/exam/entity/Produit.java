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
public class Produit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idProduit;


    String nomProduit;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    @ToString.Exclude
    @JoinColumn(name = "id_utlisatateur")
    private Utlisatateur utlisatateur;
    @Enumerated(EnumType.STRING)
    Etat etat;


    @ManyToMany(mappedBy = "produits", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)

    private Set<Categorie> categories;

}
