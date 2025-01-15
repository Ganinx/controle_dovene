package fr.hb.gestionAssurance.repository;

import fr.hb.gestionAssurance.model.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratRepository extends JpaRepository<Contrat,Long> {
}
