package tn.esprit.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.exam.entity.Bloc;
@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {
}
