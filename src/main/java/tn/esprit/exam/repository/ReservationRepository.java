package tn.esprit.exam.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.exam.entity.Etudiant;
import tn.esprit.exam.entity.Reservation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByEtudiants(Etudiant etudiant);

   @Query("SELECT COUNT(r) FROM Reservation r WHERE r.dateAnneeUniv BETWEEN :debutAnnee AND :finAnnee")
    long countByAnneeUniversitaire(Date debutAnnee, Date finAnnee);
    @Query("SELECT r FROM Reservation r WHERE r.estValide = true AND r.dateAnneeUniv BETWEEN :startDate AND :endDate")
    List<Reservation> findValidReservationsForYear(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    @Query("SELECT r FROM Reservation r WHERE r.finAnneeUnive BETWEEN :currentDate AND :expirationDate")
    List<Reservation> findReservationsExpiringSoon(@Param("currentDate") Date currentDate, @Param("expirationDate") Date expirationDate);

    @Modifying
    @Query("UPDATE Reservation r SET r.estValide = false WHERE r.finAnneeUnive < :currentDate")
    void updateExpiredReservations(@Param("currentDate") Date currentDate);
    @Query("SELECT r FROM Reservation r WHERE r.dateAnneeUniv BETWEEN :startDate AND :endDate")
    List<Reservation> findReservationsForLastMonth(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
 @Query("SELECT c.typeChambre, SUM(CASE WHEN r.estValide = true THEN 1 ELSE 0 END), SUM(CASE WHEN r.estValide = false THEN 1 ELSE 0 END) " +
         "FROM Reservation r JOIN r.chambre c WHERE r.dateAnneeUniv BETWEEN :startDate AND :endDate GROUP BY c.typeChambre")
 List<Object[]> findReservationsByTypeAndValidation(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
 @Modifying
 @Query("DELETE FROM Reservation r WHERE r.finAnneeUnive < :oneYearAgo")
 int deleteExpiredReservations(@Param("oneYearAgo") Date oneYearAgo);

}
