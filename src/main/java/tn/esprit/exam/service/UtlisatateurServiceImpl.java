package tn.esprit.exam.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.exam.entity.Produit;
import tn.esprit.exam.entity.Utlisatateur;
import tn.esprit.exam.repository.ProduitRepository;
import tn.esprit.exam.repository.UtlisatateurRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UtlisatateurServiceImpl implements IUtlisatateurServiceImpl {

    UtlisatateurRepository utlisatateurRepository;
    ProduitRepository produitRepository;
    public Utlisatateur ajouterUtilisateur (Utlisatateur u){
        Utlisatateur utlisatateur = utlisatateurRepository.save(u);
        return utlisatateur;
    }



    @Override
    @Transactional
    public Produit ajouterProduitEtCategories(Produit produit) {
        return produitRepository.save(produit);
    }


    public void affecterProdAUser(List<String> nomPrdouit, String email){



        Produit produit = (Produit) produitRepository.findProduitByNomProduit(nomPrdouit);
        Utlisatateur utlisatateur= (Utlisatateur) utlisatateurRepository.findUtlisatateurByEmail(email);
        // on set le fils dans le parent :
        produit.setUtlisatateur(utlisatateur);
        produitRepository.save(produit);
    }


    public boolean chercherProduit (String nomProduit){

        Produit  produit = (Produit) produitRepository.findProduitByNomProduit(nomProduit);

        if(produit.getEtat().equals("boyocot")) {
            return true;
        }
        else{
            return  false ;


        }


    }



}
