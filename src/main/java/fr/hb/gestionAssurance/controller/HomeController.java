package fr.hb.gestionAssurance.controller;

import fr.hb.gestionAssurance.model.Client;
import fr.hb.gestionAssurance.model.Contrat;
import fr.hb.gestionAssurance.service.ClientService;
import fr.hb.gestionAssurance.service.ContratService;
import fr.hb.gestionAssurance.service.VoitureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private ContratService contratService;

    private VoitureService voitureService;

    private ClientService clientService;

    public HomeController(ContratService contratService, VoitureService voitureService, ClientService clientService) {
        this.contratService = contratService;
        this.voitureService = voitureService;
        this.clientService = clientService;
    }

    @GetMapping("/home")
    public String getAccueil(Model model){
        List<Contrat> contrats = contratService.getLatestContrat();
        Long nbrVoitureDispo = voitureService.getAllVoituresDispo();
        Long nbrContratEnCours = contratService.getAllContratsEnCours();
        Long nbrClient = clientService.countAllClients();
        model.addAttribute("contrats", contrats);
        model.addAttribute("nbrVoiture", nbrVoitureDispo);
        model.addAttribute("nbrContrat", nbrContratEnCours);
        model.addAttribute("nbrClient", nbrClient);
        return "home";
    }
}
