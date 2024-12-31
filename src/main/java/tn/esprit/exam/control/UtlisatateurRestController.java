package tn.esprit.exam.control;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.exam.entity.Produit;
import tn.esprit.exam.entity.Utlisatateur;
import tn.esprit.exam.service.IUtlisatateurServiceImpl;

@Tag(name = "Ce Web Service gère le CRUD pour une user")
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UtlisatateurRestController {
    IUtlisatateurServiceImpl iUtlisatateurService;


    @PostMapping("/add-user")
    public Utlisatateur adduser(@RequestBody Utlisatateur u) {
        Utlisatateur user = iUtlisatateurService.ajouterUtilisateur(u);
        return user;
    }
    @PostMapping("/ajouter-projet-et-projet-detail")
    public Produit addProjetAndProjetDetail(@RequestBody Produit produit) {
        return iUtlisatateurService.ajouterProduitEtCategories(produit);
    }
}
