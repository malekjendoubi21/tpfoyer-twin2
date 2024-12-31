package tn.esprit.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.exam.entity.Produit;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit,Long> {


       @Query("SELECT c FROM Produit c " +

            "WHERE c.nomProduit = :nomProduit" )
       List<Produit> findProduitByNomProduit(
               List<String> nomProduit)
               ;


    @Query("SELECT c FROM Produit c " +

            "WHERE c.nomProduit = :nomProduit" )
    List<Produit> findProduitByNomProduit(String nomProduit);
}
