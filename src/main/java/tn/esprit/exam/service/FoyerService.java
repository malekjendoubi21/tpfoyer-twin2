package tn.esprit.exam.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.exam.entity.Bloc;
import tn.esprit.exam.entity.Foyer;
import tn.esprit.exam.entity.Universite;
import tn.esprit.exam.repository.FoyerRepository;
import tn.esprit.exam.repository.UniversiteRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FoyerService implements IFoyerService {
   FoyerRepository foyerRepository;
UniversiteRepository universiteRepository;
  public  Foyer addFoyer (Foyer f) {
        return foyerRepository.save(f);

    }
    public  Foyer updateFoyer (Foyer f){
        return foyerRepository.save(f);

    }
    public  Foyer retrieveFoyer (long idFoyer){
        return foyerRepository.findById(idFoyer).get();

    }
 public    List<Foyer> retrieveAllFoyers(){
        List<Foyer> listF = foyerRepository.findAll();
        log.info("nombre total des foyers : " + listF.size());
        for (Foyer f: listF) {
            log.info("foyer : " + f);
        }
        return listF;


 }

    public  void removeFoyer (long idFoyer){
        foyerRepository.deleteById(idFoyer);



    }
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        // Récupérer l'université par son ID
        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new IllegalArgumentException("Université avec l'ID " + idUniversite + " n'existe pas."));

        // Assurer l'association entre le foyer et ses blocs
        for (Bloc bloc : foyer.getBlocs()) {
            bloc.setFoyer(foyer);
        }

        // Associer le foyer à l'université
        universite.setFoyer(foyer);

        // Sauvegarder le foyer
        foyerRepository.save(foyer);

        // Sauvegarder l'université avec le foyer associé
        universiteRepository.save(universite);

        return foyer;
    }
}
