package fr.hb.gestionAssurance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    private String nom;

    @NotBlank(message = "Le prénom ne peut pas être vide")
    private String prenom;

    @Email(message = "L'email doit être valide")
    @NotBlank(message = "L'email ne peut pas être vide")
    private String email;

    @NotBlank(message = "Le permis ne peut pas être vide")
    private String permis;

    @Past(message = "La date de naissance doit être dans le passé")
    private LocalDate dateNaissance;

    @NotBlank(message = "Le genre ne peut pas être vide")
    private String genre;

    private boolean isBloque;

    @OneToMany(mappedBy = "client")
    private List<Contrat> contrats;

    public Client() {
    }

    public Client(Long id, String nom, String prenom, String email, String permis, LocalDate dateNaissance, String genre, boolean isBloque, List<Contrat> contrats) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.permis = permis;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.isBloque = isBloque;
        this.contrats = contrats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean getIsBloque() {
        return isBloque;
    }

    public void setBloque(boolean bloque) {
        isBloque = bloque;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }
}
