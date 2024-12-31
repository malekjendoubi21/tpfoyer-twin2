package tn.esprit.exam.control;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.exam.entity.Chambre;
import tn.esprit.exam.entity.TypeChambre;
import tn.esprit.exam.service.IChambreService;
import java.util.List;

@Tag(name = "Ce Web Service gère le CRUD pour une Chambre")
@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChambreRestController {

    IChambreService chambreService;

    // http://localhost:8089/exam/chambre/retrieve-all-chambres
    @Operation(description = "Ce web service permet de récupérer toutes les chambres de la base de données")
    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres() {
        List<Chambre> listChambres = chambreService.retrieveAllChambres();
        return listChambres;
    }
    // http://localhost:8089/exam/chambre/retrieve-chambre/8
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chId) {
        Chambre chambre = chambreService.retrieveChambre(chId);
        return chambre;
    }

    // http://localhost:8089/exam/chambre/add-chambre
    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.addChambre(c);
        return chambre;
    }

    // http://localhost:8089/exam/chambre/remove-chambre/{chambre-id}
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chId) {
        chambreService.removeChambre(chId);
    }

    // http://localhost:8089/exam/chambre/modify-chambre
    @PutMapping("/modify-chambre")
    public Chambre modifyChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.modifyChambre(c);
        return chambre;
    }

    @GetMapping("/bloc/{idBloc}/type/{typeChambre}")
    public ResponseEntity<List<Chambre>> getChambresParBlocEtType(
            @PathVariable long idBloc,
            @PathVariable TypeChambre typeChambre) {
        List<Chambre> chambres = chambreService.getChambresParBlocEtType(idBloc, typeChambre);
        if (chambres.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retourne 204 No Content si aucune chambre n'est trouvée
        }
        return ResponseEntity.ok(chambres); // Retourne la liste des chambres avec un code 200
    }
    // Endpoint pour obtenir les chambres non réservées d'un foyer et d'un type spécifique pendant l'année universitaire actuelle
    @GetMapping("/non-reserve")
    public ResponseEntity<List<Chambre>> getChambresNonReserveParNomFoyerEtTypeChambre(
            @RequestParam String nomFoyer,
            @RequestParam TypeChambre typeChambre) {

        List<Chambre> chambres = chambreService.getChambresNonReserveParNomFoyerEtTypeChambre(nomFoyer, typeChambre);

        if (chambres.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retourne 204 No Content si aucune chambre n'est trouvée
        }

        return ResponseEntity.ok(chambres); // Retourne les chambres avec un code 200 OK
    }
}
