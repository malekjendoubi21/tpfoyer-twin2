package tn.esprit.exam.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.exam.entity.Reservation;
import tn.esprit.exam.service.IReservationService;

import java.util.Date;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    private IReservationService ireservationService;



    @PutMapping("/updateReservation")
    public Reservation updateReservation(@RequestBody Reservation r) {
        return ireservationService.updateReservation(r);
    }



    @PutMapping("/affecterReservationAuChambre/{ReservationID}/{chambreID}")
    public void affecterReservationAuChambre(@PathVariable Long ReservationID,@PathVariable Long chambreID){
        ireservationService.affecterReservationAuCHambre(ReservationID,chambreID);
    };

    @PutMapping("/desaffecterReservationDuChambre/{ReservationID}")
    public void desaffecterReservationDuChambre(@PathVariable Long ReservationID){
        ireservationService.desaffecterReservationDuCHambre(ReservationID);
    }

    @PostMapping("/ajouter/{idChambre}/{cinEtudiant}")
    public Reservation ajouterReservation(
            @PathVariable long idChambre,
            @PathVariable long cinEtudiant) {
        return ireservationService.ajouterReservation(idChambre, cinEtudiant);
    }
    @DeleteMapping("/annuler/{cinEtudiant}")
    public ResponseEntity<Reservation> annulerReservation(@PathVariable long cinEtudiant) {
        Reservation reservation = ireservationService.annulerReservation(cinEtudiant);
        return ResponseEntity.ok(reservation);
    }
    @GetMapping("/annee")
    public ResponseEntity<Long> getReservationParAnneeUniversitaire(
            @RequestParam("debutAnnee") Date debutAnnee,
            @RequestParam("finAnnee") Date finAnnee) {

        long reservationsCount = ireservationService.getReservationParAnneeUniversitaire(debutAnnee, finAnnee);

        return ResponseEntity.ok(reservationsCount); // Retourne le nombre de réservations
    }
}
