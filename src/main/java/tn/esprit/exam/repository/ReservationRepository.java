package tn.esprit.exam.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.exam.entity.Etudiant;
import tn.esprit.exam.entity.Reservation;

import java.util.Date;
import java.util.Optional;

@Repository

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByEtudiants(Etudiant etudiant);

   @Query("SELECT COUNT(r) FROM Reservation r WHERE r.dateAnneeUniv BETWEEN :debutAnnee AND :finAnnee")
    long countByAnneeUniversitaire(Date debutAnnee, Date finAnnee);

}
