package tn.esprit.exam.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.exam.entity.Bloc;
import tn.esprit.exam.entity.Chambre;
import tn.esprit.exam.entity.Foyer;
import tn.esprit.exam.repository.BlocRepository;
import tn.esprit.exam.repository.ChambreRepository;
import tn.esprit.exam.repository.FoyerRepository;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class BlocService implements IBlocService {
    BlocRepository  blocRepository;
    ChambreRepository chambreRepository;
    FoyerRepository foyerRepository;
    @Override
    public List<Bloc> retrieveBlocs() {
        return blocRepository.findAll();
    }

    @Override
 public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }
    @Override
    public Bloc updateBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc retrieveBloc(long idBloc) {
        return blocRepository.findById(idBloc).get();
    }

    @Override
    public void removeBloc(long idBloc) {
        blocRepository.deleteById(idBloc);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new IllegalArgumentException("Bloc avec l'ID " + idBloc + " n'existe pas."));

        // Récupérer les chambres par leurs IDs
        List<Chambre> chambres = chambreRepository.findAllById(numChambre);

        if (chambres.isEmpty()) {
            throw new IllegalArgumentException("Aucune des chambres spécifiées n'a été trouvée.");
        }

        // Associer le bloc à chaque chambre
        for (Chambre chambre : chambres) {
            chambre.setBloc(bloc);
        }

        // Sauvegarder les chambres avec leur bloc associé
        chambreRepository.saveAll(chambres);

        // Optionnel : Ajouter les chambres au bloc (relation bidirectionnelle)
        bloc.getChambres().addAll(chambres);

        // Retourner le bloc avec les chambres associées
        return blocRepository.save(bloc);
    }
    public Bloc affecterBlocAFoyer(long idBloc, long idFoyer) {
        // Récupérer le Bloc par son ID
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new IllegalArgumentException("Bloc introuvable avec l'id : " + idBloc));

        // Récupérer le Foyer par son ID
        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new IllegalArgumentException("Foyer introuvable avec l'id : " + idFoyer));

        // Affecter le foyer au bloc
        bloc.setFoyer(foyer);

        // Sauvegarder le bloc avec l'affectation
        return blocRepository.save(bloc);
    }
}
