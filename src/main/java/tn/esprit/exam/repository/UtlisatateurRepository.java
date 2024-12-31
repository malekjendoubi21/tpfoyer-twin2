package tn.esprit.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.exam.entity.Produit;
import tn.esprit.exam.entity.Utlisatateur;

import java.util.List;

public interface UtlisatateurRepository extends JpaRepository<Utlisatateur,Long> {

    @Query("SELECT u FROM Utlisatateur u " +

            "WHERE u.email = :email" )
    List<Utlisatateur> findUtlisatateurByEmail(
           String email)
            ;








}
