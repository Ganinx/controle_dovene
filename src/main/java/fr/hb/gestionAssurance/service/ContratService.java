package fr.hb.gestionAssurance.service;

import fr.hb.gestionAssurance.model.Contrat;
import fr.hb.gestionAssurance.model.Voiture;
import fr.hb.gestionAssurance.repository.ContratRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContratService {
    
    private final ContratRepository contratRepository;

    public ContratService(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    public Contrat saveContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }


    public void deleteContrat(Long id) {
        if (contratRepository.existsById(id)) {
            contratRepository.deleteById(id);
        } else {
            throw new RuntimeException("Contrat introuvable avec l'id : " + id);
        }
    }

    public Optional<Contrat> getContratById(Long id) {
        return contratRepository.findById(id);
    }

    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    public List<Contrat> getLatestContrat(){
        List<Contrat> contrats = getAllContrats();
        return contrats.stream()
                .filter(contrat -> isWithinThreeMonths(contrat.getDateFin()))
                .toList();
    }

    public static boolean isWithinThreeMonths(LocalDate date) {
        LocalDate today = LocalDate.now();
        LocalDate threeMonthsLater = today.plusMonths(3);
        return !date.isBefore(today) && date.isBefore(threeMonthsLater);
    }

    public Long getAllContratsEnCours() {
        List<Contrat> contrats = getAllContrats();
        return contrats.stream()
                .filter(Contrat::isEstTermine)
                .count();
    }
}
