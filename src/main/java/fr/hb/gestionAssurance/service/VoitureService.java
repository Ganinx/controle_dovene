package fr.hb.gestionAssurance.service;

import fr.hb.gestionAssurance.model.Voiture;
import fr.hb.gestionAssurance.repository.VoitureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoitureService {
    
    private VoitureRepository voitureRepository;

    public VoitureService(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }

    public Voiture saveVoiture(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    public void deleteVoiture(String id) {
        if (voitureRepository.existsById(id)) {
            voitureRepository.deleteById(id);
        } else {
            throw new RuntimeException("Voiture introuvable avec l'id : " + id);
        }
    }

    public Optional<Voiture> getVoitureById(String id) {
        return voitureRepository.findById(id);
    }

    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
    }

    public Long getAllVoituresDispo() {
        List<Voiture> voitures = getAllVoitures();
        return voitures.stream()
                .filter(Voiture::isStatut)
                .count();
    }
}
