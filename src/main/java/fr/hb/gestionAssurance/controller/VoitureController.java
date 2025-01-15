package fr.hb.gestionAssurance.controller;

import fr.hb.gestionAssurance.enumeration.CarCategory;
import fr.hb.gestionAssurance.model.Voiture;
import fr.hb.gestionAssurance.service.VoitureService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("voitures")
public class VoitureController {

    private VoitureService voitureService;

    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    @GetMapping("")
    public String listVoitures(Model model, Locale locale) {
        model.addAttribute("voitures", voitureService.getAllVoitures());
        return "voiture/list";
    }

    @GetMapping("/add")
    public String addVoitureForm(Model model) {
        model.addAttribute("isEditMode", false);
        model.addAttribute("voiture", new Voiture());
        model.addAttribute("carCategories", CarCategory.values());
        return "voiture/form";
    }

    @GetMapping("/edit/{ref}")
    public String editVoitureForm(@PathVariable("ref") String reference, Model model) {
        Optional<Voiture> existingVoiture = voitureService.getVoitureById(reference);
        if (existingVoiture == null) {
            return "redirect:/voitures";
        }
        model.addAttribute("isEditMode", true);
        model.addAttribute("voiture", existingVoiture);
        model.addAttribute("carCategories", CarCategory.values());
        return "voiture/form";
    }

    @PostMapping("/post")
    public String saveVoiture(@Valid @ModelAttribute("voiture") Voiture voiture, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isEditMode", voiture.getImmatriculation() != null);
            return "voiture/form";
        }
        voitureService.saveVoiture(voiture);
        return "redirect:/voitures";
    }

    @GetMapping("/delete/{ref}")
    public String deleteVoiture(@PathVariable("ref") String reference) {
        voitureService.deleteVoiture(reference);
        return "redirect:/voitures";
    }

}
