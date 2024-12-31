package tn.esprit.exam.control;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.exam.entity.Etudiant;
import tn.esprit.exam.service.IEtudiantService;

import java.util.List;

@Tag(name = "Ce Web Service gère le CRUD pour une Chambre")
@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class EtudiantController {
    IEtudiantService etudiantService;
    @PostMapping("/add-etudiants")
    public List<Etudiant> addEtudiants(@RequestBody List<Etudiant> etudiants) {
        return etudiantService.addEtudiants(etudiants);
    }

    @GetMapping("/retrieve-all-etudiants")
    public List<Etudiant> getEtudiants() {
        List<Etudiant> listEtudiants = etudiantService.retrieveAllEtudiants();
        return listEtudiants;
    }
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Long fId) {
        Etudiant etudiant = etudiantService.retrieveEtudiant(fId);
        return etudiant;
    }
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public void removeEtudiant(@PathVariable("etudiant-id") Long fId) {
        etudiantService.removeEtudiant(fId);
    }
    @PutMapping("/modify-etudiant")
    public Etudiant modifyEtudiant(@RequestBody Etudiant f) {
        Etudiant etudiant = etudiantService.updateEtudiant(f);
        return etudiant;
    }
}
