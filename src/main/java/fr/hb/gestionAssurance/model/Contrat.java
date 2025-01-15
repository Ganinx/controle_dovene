package fr.hb.gestionAssurance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Voiture voiture;

    @ManyToOne
    private Client client;

    @NotNull(message = "La date de début ne peut pas être nulle")
    @PastOrPresent(message = "La date de début doit être dans le passé ou aujourd'hui")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin ne peut pas être nulle")
    @Future(message = "La date de fin doit être dans le futur")
    private LocalDate dateFin;

    @Positive(message = "Le prix total doit être positif")
    private int prixTotal;

    private boolean estTermine;

    public Contrat() {
    }

    public Contrat(Long id, Voiture voiture, Client client, LocalDate dateDebut, LocalDate dateFin, int prixTotal, boolean estTermine) {
        this.id = id;
        this.voiture = voiture;
        this.client = client;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
        this.estTermine = estTermine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public int getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(int prixTotal) {
        this.prixTotal = prixTotal;
    }

    public boolean isEstTermine() {
        return estTermine;
    }

    public void setEstTermine(boolean estTermine) {
        this.estTermine = estTermine;
    }
}
