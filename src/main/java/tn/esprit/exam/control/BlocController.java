package tn.esprit.exam.control;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.exam.entity.Bloc;
import tn.esprit.exam.entity.Bloc;
import tn.esprit.exam.service.IBlocService;

import java.util.List;

@Tag(name = "Ce Web Service gère le CRUD pour une Bloc")
@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
public class BlocController {
    IBlocService blocService;


    @PostMapping("/add-blocs")
    public Bloc addBlocs(@RequestBody Bloc blocs) {
        return blocService.addBloc(blocs);
    }

    @GetMapping("/retrieve-all-blocs")
    public List<Bloc> getBlocs() {
        List<Bloc> listBlocs = blocService.retrieveBlocs();
        return listBlocs;
    }
    @GetMapping("/retrieve-bloc/{bloc-id}")
    public Bloc retrieveBloc(@PathVariable("bloc-id") Long fId) {
        Bloc bloc = blocService.retrieveBloc(fId);
        return bloc;
    }
    @DeleteMapping("/remove-bloc/{bloc-id}")
    public void removeBloc(@PathVariable("bloc-id") Long fId) {
        blocService.removeBloc(fId);
    }
    @PutMapping("/modify-bloc")
    public Bloc modifyBloc(@RequestBody Bloc f) {
        Bloc bloc = blocService.updateBloc(f);
        return bloc;
    }
    @PostMapping("/ajouterEtAffecterChambresABloc/{idBloc}")
    public Bloc ajouterEtAffecterChambresABloc( @RequestBody List<Long> numChambre, @PathVariable long idBloc) {
        return blocService.affecterChambresABloc(numChambre, idBloc);
    }
    @PostMapping("/affecterBlocAFoyer/{idBloc}/{idFoyer}")
    public Bloc affecterBlocAFoyer(@PathVariable long idBloc, @PathVariable long idFoyer) {
        return blocService.affecterBlocAFoyer(idBloc, idFoyer);
    }


}
