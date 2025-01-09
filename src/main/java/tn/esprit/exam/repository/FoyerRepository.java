package tn.esprit.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.exam.entity.Foyer;

import java.util.List;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {
    @Query("SELECT f FROM Foyer f ORDER BY f.capaciteFoyer DESC")
    Foyer findFoyerWithMaxCapacity();

    @Query("SELECT f.nomFoyer, COUNT(r) FROM Foyer f JOIN f.blocs b JOIN b.chambres c JOIN c.reservations r GROUP BY f")
    List<Object[]> countReservationsByFoyer();



}
