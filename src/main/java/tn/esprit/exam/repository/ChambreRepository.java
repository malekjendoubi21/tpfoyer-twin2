package tn.esprit.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.exam.entity.Chambre;
import tn.esprit.exam.entity.TypeChambre;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    //List<Chambre> findByBlocIdAndTypeChambre(long idBloc, TypeChambre typeChambre);

    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeChambre = :typeChambre")
    List<Chambre> getChambresParBlocEtType(@Param("idBloc") long idBloc, @Param("typeChambre") TypeChambre typeChambre);

    @Query("SELECT c FROM Chambre c WHERE c.bloc.nomBloc = :nomFoyer " +
            "AND c.typeChambre = :typeChambre " +
            "AND c.idChambre NOT IN (SELECT r.chambre.idChambre FROM Reservation r WHERE r.dateAnneeUniv BETWEEN :debutAnnee AND :finAnnee)")
    List<Chambre> findChambresNonReserveParNomFoyerEtType(
            String nomFoyer, TypeChambre typeChambre, Date debutAnnee, Date finAnnee);


    @Query("SELECT c FROM Chambre c WHERE c.bloc.nomBloc = :nomBloc AND c.reservations IS EMPTY")
    List<Chambre> findAvailableChambresByBlocName(@Param("nomBloc") String nomBloc);

    @Query("SELECT c, COUNT(r.etudiants) FROM Chambre c JOIN c.reservations r GROUP BY c")
    List<Object[]> countEtudiantsByChambre();
    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :blocId AND NOT EXISTS (SELECT r FROM c.reservations r WHERE r.estValide = true)")
    List<Chambre> findAvailableChambresByBloc(@Param("blocId") long blocId);

}

