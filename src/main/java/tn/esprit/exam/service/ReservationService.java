package tn.esprit.exam.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.exam.entity.Etudiant;
import tn.esprit.exam.entity.Reservation;
import tn.esprit.exam.entity.Chambre;

import tn.esprit.exam.repository.ChambreRepository;
import tn.esprit.exam.repository.EtudiantRepository;
import tn.esprit.exam.repository.ReservationRepository;

import java.io.File;
import java.util.*;

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


    @Scheduled(cron = "0 0 9 * * *") // Tous les jours à 9h
    public void notifyReservationRenewal() {
        Date currentDate = new Date();

        // Calculer la date d'expiration (7 jours à partir de la date actuelle)
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date expirationDate = calendar.getTime();

        // Récupérer les réservations expirant bientôt
        List<Reservation> reservations = reservationRepository.findReservationsExpiringSoon(currentDate, expirationDate);

        // Notifier pour chaque réservation
        for (Reservation reservation : reservations) {
            log.info("La réservation {} expire bientôt. Veuillez la renouveler.", reservation.getNumReservation());
        }
    }

    @Scheduled(cron = "0 0 0 * * *") // Tous les jours à minuit
    public void updateExpiredReservations() {
        reservationRepository.updateExpiredReservations(new Date());
    }

    @Scheduled(cron = "0 0 0 1 * *") // Le 1er de chaque mois à minuit
    public void generateMonthlyReport() {
        // Obtenir la date actuelle
        Calendar calendar = Calendar.getInstance();

        // Définir la date au premier jour du mois courant
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        // Reculer d'un mois pour obtenir le dernier mois
        calendar.add(Calendar.MONTH, -1);
        Date startDate = calendar.getTime(); // Premier jour du mois précédent

        // Aller au dernier jour du mois précédent
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date endDate = calendar.getTime(); // Dernier jour du mois précédent

        // Récupérer les réservations pour le mois précédent
        List<Reservation> reservations = reservationRepository.findReservationsForLastMonth(startDate, endDate);

        // Afficher les informations dans les logs
        log.info("Nombre total de réservations le mois dernier : {}", reservations.size());
        for (Reservation reservation : reservations) {
            log.info("Réservation : {}", reservation);
        }
    }

    @Scheduled(cron = "0 0 8 * * *") // Tous les jours à 8h
    public void notifyRenewalInConsole() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date expirationDate = calendar.getTime();

        List<Reservation> reservations = reservationRepository.findReservationsExpiringSoon(currentDate, expirationDate);

        for (Reservation reservation : reservations) {
            for (Etudiant etudiant : reservation.getEtudiants()) {
                log.info("Notification : La réservation {} expire bientôt pour l'étudiant {} {}.",
                        reservation.getNumReservation(), etudiant.getNomETud(), etudiant.getPrenomETud());
                // Utiliser System.out.println si vous préférez.
                System.out.println(String.format(
                        "Notification : La réservation %s expire bientôt pour l'étudiant %s %s.",
                        reservation.getNumReservation(), etudiant.getNomETud(), etudiant.getPrenomETud()
                ));
            }
        }
    }


    @Scheduled(cron = "0 0 0 * * 1") // Chaque lundi à minuit
    public void generateWeeklyReport() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        Date lastWeek = calendar.getTime();

        List<Object[]> report = reservationRepository.findReservationsByTypeAndValidation(lastWeek, currentDate);

        for (Object[] row : report) {
            log.info("Type: {}, Validées: {}, Non validées: {}", row[0], row[1], row[2]);
        }
    }
    @Scheduled(cron = "0 0 0 1 1 *") // Chaque 1er janvier à minuit
    public void cleanupExpiredReservations() {
        Date oneYearAgo = new Date(System.currentTimeMillis() - 365L * 24 * 60 * 60 * 1000);
        int deleted = reservationRepository.deleteExpiredReservations(oneYearAgo);
        log.info("Nombre de réservations expirées supprimées : {}", deleted);
    }

    @Scheduled(fixedDelay = 5000) // 5 secondes (en millisecondes)
    public void runTaskWithFixedDelay() {
        System.out.println("Tâche exécutée à : " + new Date());
    }
    @Scheduled(initialDelay = 10000, fixedDelay = 5000) // Délai initial de 10 secondes, puis toutes les 5 secondes
    public void runTaskWithInitialDelay() {
        System.out.println("Tâche exécutée avec un délai initial : " + new Date());
    }
    @Scheduled(fixedDelay = 60000) // Toutes les 60 secondes
    public void cleanupExpiredReservationsfixeddelay() {
        Date now = new Date();
        int deleted = reservationRepository.deleteExpiredReservations(now);
        System.out.println("Nombre de réservations expirées supprimées : " + deleted + " à " + new Date());
    }
    @Scheduled(fixedDelay = 30000) // Toutes les 30 secondes
    public void processFiles() {
        File folder = new File("/path/to/folder");
        File[] files = folder.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {
                System.out.println("Traitement du fichier : " + file.getName());
                // Traitement du fichier
                file.delete(); // Supprime après traitement
            }
        } else {
            System.out.println("Aucun fichier à traiter à " + new Date());
        }
    }
    @Scheduled(fixedDelay = 20000) // Toutes les 20 secondes
    public void simulateLongProcess() throws InterruptedException {
        System.out.println("Début du processus long à : " + new Date());
        Thread.sleep(10000); // Simule un traitement de 10 secondes
        System.out.println("Fin du processus long à : " + new Date());
    }
    @Scheduled(fixedDelay = 15000) // Toutes les 15 secondes
    public void taskWithErrorHandling() {
        try {
            System.out.println("Tâche exécutée à : " + new Date());
            // Simule une exception
            if (new Random().nextBoolean()) {
                throw new RuntimeException("Erreur simulée");
            }
            System.out.println("Tâche terminée avec succès.");
        } catch (Exception e) {
            System.err.println("Erreur lors de l'exécution de la tâche : " + e.getMessage());
        }
    }

}
