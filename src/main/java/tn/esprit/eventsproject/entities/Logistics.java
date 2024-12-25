package tn.esprit.eventsproject.entities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tn.esprit.eventsproject.entities.Event;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Logistics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idLog;  // Identifiant unique pour la classe Logistics

    private String description;
    private boolean reserve;  // Etat de la réservation
    private float prixUnit;  // Prix unitaire de la logistique
    private int quantite;  // Quantité

    @ManyToOne
    @JoinColumn(name = "event_id")  // Nom de la colonne pour la clé étrangère
    private Event event;  // Relation ManyToOne avec Event

    // Constructeurs
    public Logistics() {
    }

    public Logistics(int idLog, String description, boolean reserve, float prixUnit, int quantite, Event event) {
        this.idLog = idLog;
        this.description = description;
        this.reserve = reserve;
        this.prixUnit = prixUnit;
        this.quantite = quantite;
        this.event = event;
    }

    // Getters et Setters
    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isReserve() {
        return reserve;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }

    public float getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(float prixUnit) {
        this.prixUnit = prixUnit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
