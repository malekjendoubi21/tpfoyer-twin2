package tn.esprit.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.exam.entity.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
