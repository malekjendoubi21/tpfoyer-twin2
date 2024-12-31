package tn.esprit.exam.entity;

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
public class Utlisatateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idUtlisatateur;
    String email;


    @Enumerated(EnumType.STRING)
    TypeUtlisatateur typeUtlisatateur;

     Date dateInscri;

    @OneToMany( cascade = CascadeType.ALL)
    private Set<Produit> produits;
}
