package fr.hb.gestionAssurance.repository;

import fr.hb.gestionAssurance.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture,String> {
}
