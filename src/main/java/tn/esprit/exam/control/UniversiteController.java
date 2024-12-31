package tn.esprit.exam.control;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.exam.entity.Universite;
import tn.esprit.exam.service.IUniversiteService;

import java.util.List;
@Tag(name = "Ce Web Service gère le CRUD pour une universite")
@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteController {

    IUniversiteService universiteService;
    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite f) {
        return universiteService.addUniversite(f);
    }
    @GetMapping("/retrieve-all-universites")
    public List<Universite> getUniversites() {
        List<Universite> listUniversites = universiteService.retrieveAllUniversities();
        return listUniversites;
    }
    @GetMapping("/retrieve-universite/{universite-id}")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long fId) {
        Universite universite = universiteService.retrieveUniversite(fId);
        return universite;
    }
    @DeleteMapping("/remove-universite/{idUniversite}")
    public void removeUniversite(@PathVariable("idUniversite") Long idUniversite) {
        universiteService.removeUniversite(idUniversite);
    }
    @PutMapping("/modify-universite")
    public Universite modifyUniversite(@RequestBody Universite f) {
        Universite universite = universiteService.updateUniversite(f);
        return universite;
    }
    @PostMapping("/affecterFoyer/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite(@PathVariable long idFoyer,@PathVariable String nomUniversite){
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }
@PostMapping("/desaffecterFoyer/{idUniversite}")
    public Universite desaffecterFoyerAUniversite(@PathVariable long idUniversite){
        return universiteService.desaffecterFoyerAUniversite(idUniversite);
    }

}
