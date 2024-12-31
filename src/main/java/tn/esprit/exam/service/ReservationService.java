package tn.esprit.exam.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.exam.entity.Etudiant;
import tn.esprit.exam.entity.Reservation;
import tn.esprit.exam.entity.Chambre;

import tn.esprit.exam.repository.ChambreRepository;
import tn.esprit.exam.repository.EtudiantRepository;
import tn.esprit.exam.repository.ReservationRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationService implements IReservationService {
ReservationRepository reservationRepository;
ChambreRepository   chambreRepository;
EtudiantRepository etudiantRepository;
    @Override
    public List<Reservation> retrieveAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        return reservationRepository.save(res);
    }

    @Override
    public Reservation retrieveReservation(long idReservation) {
        return reservationRepository.findById(idReservation).get();
    }
    @Override
    public void affecterReservationAuCHambre(Long reservationID, Long chambreID) {
        Reservation reservation = reservationRepository.findById(reservationID).get();
        Chambre chambre = chambreRepository.findById(chambreID).get();
        reservation.setChambre(chambre);
        reservationRepository.save(reservation);
    }

    @Override
    public void desaffecterReservationDuCHambre(Long reservationID) {
        Reservation reservation = reservationRepository.findById(reservationID).get();
        reservation.setChambre(null);
        reservationRepository.save(reservation);
    }
    @Override
    public Reservation ajouterReservation(long idChambre, long cin) {
      /*  // Récupérer la chambre
        Chambre chambre = chambreRepository.findById(idChambre)
                .orElseThrow(() -> new IllegalArgumentException("Chambre introuvable"));

        // Récupérer l'étudiant via le champ 'cin'
        Etudiant etudiant = etudiantRepository.findByCin(cin)
                .orElseThrow(() -> new IllegalArgumentException("Etudiant introuvable"));

        // Get the first Etudiant from the list

        // Créer la réservation
        Reservation reservation = new Reservation();
        reservation.setChambre(chambre);
        reservation.setEtudiants(etudiant);  // Set the single Etudiant object here
        reservation.setEstValide(true);

        // Définir les dates universitaires
        reservation.setDateAnneeUniv(Date.valueOf("2024-09-01"));
        reservation.setFinAnneeUnive(Date.valueOf("2025-06-01"));

        // Générer le numéro de réservation
        String numReservation = idChambre + "-" + chambre.getBloc().getNomBloc() + "-" + cin;
        reservation.setNumReservation(numReservation);

        // Sauvegarder la réservation
        return reservationRepository.save(reservation);*/
        return null;
    }
    @Override
    public Reservation annulerReservation(long cinEtudiant) {
        // Récupérer l'étudiant via le champ 'cin'
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant)
                .orElseThrow(() -> new IllegalArgumentException("Etudiant introuvable"));

        // Récupérer la réservation associée à l'étudiant
        Reservation reservation = reservationRepository.findByEtudiants(etudiant)
                .orElseThrow(() -> new IllegalArgumentException("Aucune réservation trouvée pour cet étudiant"));

        // Mettre à jour l'état de la réservation
        reservation.setEstValide(false);

        // Désaffecter l'étudiant de la réservation
        reservation.setEtudiants(null);

        // Désaffecter la chambre de la réservation et mettre à jour sa capacité
        Chambre chambre = reservation.getChambre();
       // chambre.setCapacite(chambre.getCapacite() + 1); // Augmenter la capacité de la chambre
        reservation.setChambre(null);

        // Sauvegarder les modifications
        reservationRepository.save(reservation);
        chambreRepository.save(chambre);

        return reservation;
    }
    public long getReservationParAnneeUniversitaire(Date debutAnnee, Date finAnnee) {
        return reservationRepository.countByAnneeUniversitaire(debutAnnee, finAnnee);
    }
}
