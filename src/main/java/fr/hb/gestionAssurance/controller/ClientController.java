package fr.hb.gestionAssurance.controller;

import fr.hb.gestionAssurance.model.Client;
import fr.hb.gestionAssurance.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("clients")
public class ClientController {
    
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("")
    public String listClients(Model model, Locale locale) {
        model.addAttribute("clients", clientService.getAllClients());
        return "client/list";
    }

    @GetMapping("/add")
    public String addClientForm(Model model) {
        model.addAttribute("isEditMode", false);
        model.addAttribute("client", new Client());
        return "client/form";
    }

    @GetMapping("/edit/{ref}")
    public String editClientForm(@PathVariable("ref") Long reference, Model model) {
        Optional<Client> existingClient = clientService.getClientById(reference);
        if (existingClient.isEmpty()) {
            return "redirect:/clients";
        }
        model.addAttribute("isEditMode", true);
        model.addAttribute("client", existingClient);
        return "client/form";
    }

    @PostMapping("/post")
    public String saveClient(@Valid @ModelAttribute("client") Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEditMode", client.getId() != null);
            model.addAttribute("client", client);
            return "client/form";
        }
        clientService.saveClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/delete/{ref}")
    public String deleteClient(@PathVariable("ref") Long reference) {
        clientService.deleteClient(reference);
        return "redirect:/clients";
    }
}
