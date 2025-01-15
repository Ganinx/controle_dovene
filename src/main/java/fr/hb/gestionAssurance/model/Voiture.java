package fr.hb.gestionAssurance.model;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Voiture {

    @Id
    @NotBlank(message = "L'immatriculation ne peut pas être vide")
    private String immatriculation;

    @NotBlank(message = "Le modèle ne peut pas être vide")
    private String modele;

    @NotBlank(message = "La marque ne peut pas être vide")
    private String marque;

    @NotBlank(message = "La catégorie ne peut pas être vide")
    private String categorie;

    private boolean statut;

    @OneToMany(mappedBy = "voiture")
    private List<Contrat> contrats;

    public Voiture() {
    }

    public Voiture(String immatriculation, String modele, String marque, String categorie, boolean statut, List<Contrat> contrats) {
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.marque = marque;
        this.categorie = categorie;
        this.statut = statut;
        this.contrats = contrats;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public boolean isStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }
}
