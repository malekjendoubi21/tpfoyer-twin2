package tn.esprit.exam.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.exam.entity.Chambre;
import tn.esprit.exam.entity.TypeChambre;
import tn.esprit.exam.repository.ChambreRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ChambreServiceImpl implements IChambreService {

    ChambreRepository chambreRepository;

    //@Scheduled(cron = "0/15 * * * * *")
    // @Scheduled(fixedDelay = 10000) // 10000 millisecondes
    public List<Chambre> retrieveAllChambres() {
        List<Chambre> listC = chambreRepository.findAll();
        log.info("nombre total des chambres : " + listC.size());
       for (Chambre c: listC) {
           log.info("chambre : " + c);
       }
        return listC;
    }

    public Chambre retrieveChambre(Long chambreId) {
        Chambre c = chambreRepository.findById(chambreId).get();
        return c;
    }

    public Chambre addChambre(Chambre c) {
        Chambre chambre = chambreRepository.save(c);
        return chambre;
    }

    public Chambre modifyChambre(Chambre chambre) {
        Chambre c = chambreRepository.save(chambre);
        return c;
    }

    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeChambre) {
        return chambreRepository.getChambresParBlocEtType(idBloc, typeChambre);
    }
    public List<Chambre> getChambresNonReserveParNomFoyerEtTypeChambre(String nomFoyer, TypeChambre typeChambre) {
        // Définir les dates de l'année universitaire actuelle en utilisant java.util.Date et Calendar
        Calendar calendar = Calendar.getInstance();

        // Date de début de l'année universitaire : 1er septembre 2024
        calendar.set(2024, Calendar.SEPTEMBER, 1, 0, 0, 0);
        Date debutAnnee = calendar.getTime();

        // Date de fin de l'année universitaire : 1er juin 2025
        calendar.set(2025, Calendar.JUNE, 1, 0, 0, 0);
        Date finAnnee = calendar.getTime();

        return chambreRepository.findChambresNonReserveParNomFoyerEtType(nomFoyer, typeChambre, debutAnnee, finAnnee);
    }
}
