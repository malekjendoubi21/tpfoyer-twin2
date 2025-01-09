package tn.esprit.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.exam.entity.Etudiant;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    List<Etudiant> getEtudiantsByCin(long cin);
    Optional<Etudiant> findByCin(long cin);

    @Query("SELECT e FROM Etudiant e WHERE e.reservations IS EMPTY")
    List<Etudiant> findEtudiantsWithoutReservations();
    @Query("SELECT e FROM Etudiant e JOIN e.reservations r JOIN r.chambre c JOIN c.bloc b WHERE b.foyer.nomFoyer = :nomFoyer")
    List<Etudiant> findEtudiantsByFoyer(@Param("nomFoyer") String nomFoyer);
    @Query("SELECT e, COUNT(r) AS totalReservations " +
            "FROM Etudiant e " +
            "JOIN e.reservations r " +
            "WHERE FUNCTION('YEAR', r.dateAnneeUniv) = :annee " +
            "GROUP BY e " +
            "ORDER BY totalReservations DESC")
    List<Object[]> findEtudiantWithMostReservations(@Param("annee") int annee);


}
