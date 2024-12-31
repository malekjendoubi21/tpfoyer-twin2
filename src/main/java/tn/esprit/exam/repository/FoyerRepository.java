package tn.esprit.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.exam.entity.Foyer;
@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {

}
