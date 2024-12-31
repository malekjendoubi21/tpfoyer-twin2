package tn.esprit.exam.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.exam.entity.Etudiant;
import tn.esprit.exam.entity.Foyer;
import tn.esprit.exam.repository.EtudiantRepository;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class EtudiantService implements IEtudiantService {

    EtudiantRepository etudiantRepository;
    @Override

    //@Scheduled(cron = "0/15 * * * * *")
    // @Scheduled(fixedDelay = 10000) // 10000 millisecondes
    public List<Etudiant> retrieveAllEtudiants() {
        List<Etudiant> listC = etudiantRepository.findAll();
        log.info("nombre total des etudiants : " + listC.size());
        for (Etudiant c : listC) {
            log.info("etudiant : " + c);
        }
        return listC;
    }


    @Override

    public List<Etudiant> addEtudiants (List<Etudiant> etudiants){
        List<Etudiant> listC = etudiantRepository.saveAll(etudiants);
        return listC;
    }
    @Override


    public Etudiant updateEtudiant(Etudiant e) {
        Etudiant c = etudiantRepository.save(e);
        return c;
    }

    @Override
    public Etudiant retrieveEtudiant(long idEtudiant) {
        Etudiant c = etudiantRepository.findById(idEtudiant).get();
        return c;
    }

    @Override
    public void removeEtudiant(long idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);

    }


}