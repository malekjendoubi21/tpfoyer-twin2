package tn.esprit.exam.service;

import tn.esprit.exam.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservation();
    Reservation updateReservation (Reservation res);
    Reservation retrieveReservation (long idReservation);
    public void affecterReservationAuCHambre(Long idReservation, Long idChambre);
    public void desaffecterReservationDuCHambre(Long idReservation);
    public Reservation ajouterReservation(long idChambre, long cinEtudiant) ;
    public Reservation annulerReservation(long cinEtudiant);
    public long getReservationParAnneeUniversitaire(Date debutAnnee, Date finAnnee) ;

    }
