package tn.esprit.exam.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.exam.entity.Foyer;
import tn.esprit.exam.entity.Universite;
import tn.esprit.exam.entity.Universite;
import tn.esprit.exam.repository.FoyerRepository;
import tn.esprit.exam.repository.UniversiteRepository;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class UniversiteService implements IUniversiteService {
    UniversiteRepository universiteRepository;
FoyerRepository foyerRepository;
    public Universite addUniversite (Universite f) {
        return universiteRepository.save(f);

    }
    public  Universite updateUniversite (Universite f){
        return universiteRepository.save(f);

    }
    public  Universite retrieveUniversite (long idUniversite){
        return universiteRepository.findById(idUniversite).get();

    }
    public    List<Universite> retrieveAllUniversities(){
        List<Universite> listF = universiteRepository.findAll();
        log.info("nombre total des universites : " + listF.size());
        for (Universite f: listF) {
            log.info("universite : " + f);
        }
        return listF;


    }

    public  void removeUniversite (long idUniversite){
        universiteRepository.deleteById(idUniversite);

    }
 /*   public Universite affecterFoyerAUniversite (long idFoyer, String nomUniversite) {
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);
        universite.setFoyer.setId(idFoyer);
        return universiteRepository.save(universite);
    }
*/
    @Override
    public Universite affecterFoyerAUniversite (long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);
        universite.setFoyer(foyer);
        return universiteRepository.save(universite);
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new IllegalArgumentException("Université avec l'ID " + idUniversite + " n'existe pas."));

        if (universite.getFoyer() == null) {
            throw new IllegalArgumentException("Cette université n'a pas de foyer affecté.");
        }

        universite.setFoyer(null);

        // Sauvegarder les modifications
        return universiteRepository.save(universite);
    }
}
