package fr.hb.gestionAssurance.controller;

import fr.hb.gestionAssurance.enumeration.CarCategory;
import fr.hb.gestionAssurance.model.Client;
import fr.hb.gestionAssurance.model.Contrat;
import fr.hb.gestionAssurance.model.Voiture;
import fr.hb.gestionAssurance.service.ClientService;
import fr.hb.gestionAssurance.service.ContratService;
import fr.hb.gestionAssurance.service.VoitureService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("contrats")
public class ContratController {
    
    private ContratService contratService;

    private VoitureService voitureService;

    private ClientService clientService;

    public ContratController(ContratService contratService, VoitureService voitureService, ClientService clientService) {
        this.contratService = contratService;
        this.voitureService = voitureService;
        this.clientService = clientService;
    }

    @GetMapping("")
    public String listContrats(Model model, Locale locale) {
        model.addAttribute("contrats", contratService.getAllContrats());
        return "contrat/list";
    }

    @GetMapping("/add")
    public String addContratForm(Model model) {
        List<Voiture> voitures = voitureService.getAllVoitures();
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("isEditMode", false);
        model.addAttribute("contrat", new Contrat());
        model.addAttribute("voitures", voitures);
        model.addAttribute("clients", clients);
        return "contrat/form";
    }

    @GetMapping("/edit/{ref}")
    public String editContratForm(@PathVariable("ref") Long reference, Model model) {
        Optional<Contrat> existingContrat = contratService.getContratById(reference);
        if (existingContrat.isEmpty()) {
            return "redirect:/contrats";
        }
        model.addAttribute("isEditMode", true);
        model.addAttribute("contrat", existingContrat);
        return "contrat/form";
    }

    @PostMapping("/post")
    public String saveContrat(@Valid @ModelAttribute("contrat") Contrat contrat, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEditMode", contrat.getId() != null);
            return "contrat/form";
        }
        contratService.saveContrat(contrat);
        return "redirect:/contrats";
    }

    @GetMapping("/delete/{ref}")
    public String deleteContrat(@PathVariable("ref") Long reference) {
        contratService.deleteContrat(reference);
        return "redirect:/contrats";
    }
}
