package tn.esprit.exam.service;

import tn.esprit.exam.entity.Produit;
import tn.esprit.exam.entity.Utlisatateur;

import java.util.List;

public interface IUtlisatateurServiceImpl {



    public Utlisatateur ajouterUtilisateur (Utlisatateur u);
    public Produit ajouterProduitEtCategories (Produit p);
    public void affecterProdAUser(List<String> nomProuit, String email);
    public boolean chercherProduit (String nomProduit)
;
}
