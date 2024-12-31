package tn.esprit.exam.control;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.exam.entity.Foyer;
import tn.esprit.exam.service.IFoyerService;

import java.util.List;

@Tag(name = "Ce Web Service gère le CRUD pour une foyer")
@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerController {

    IFoyerService foyerService;
    @PostMapping("/add-foyer")
    public Foyer addFoyer(@RequestBody Foyer f) {
        return foyerService.addFoyer(f);
    }
    @GetMapping("/retrieve-all-foyers")
    public List<Foyer> getFoyers() {
        List<Foyer> listFoyers = foyerService.retrieveAllFoyers();
        return listFoyers;
    }
    @GetMapping("/retrieve-foyer/{foyer-id}")
    public Foyer retrieveFoyer(@PathVariable("foyer-id") Long fId) {
        Foyer foyer = foyerService.retrieveFoyer(fId);
        return foyer;
    }
    @DeleteMapping("/remove-foyer/{foyer-id}")
    public void removeFoyer(@PathVariable("foyer-id") Long fId) {
        foyerService.removeFoyer(fId);
    }
    @PutMapping("/modify-foyer")
    public Foyer modifyFoyer(@RequestBody Foyer f) {
        Foyer foyer = foyerService.updateFoyer(f);
        return foyer;
    }

    @PostMapping("/ajouter-et-affecter/{idUniversite}")
    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer, @RequestParam long idUniversite) {
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyer, idUniversite);
    }
}
