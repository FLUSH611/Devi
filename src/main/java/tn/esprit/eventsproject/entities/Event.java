package tn.esprit.eventsproject.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;  // Clé primaire pour l'entité Event

    private String eventName;
    private String description;

    private static final Logger log = LoggerFactory.getLogger(Event.class); // Logger pour la classe Event

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private float cout;

    @ManyToMany(mappedBy = "events")
    private Set<Participant> participants;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Logistics> logistics;

    // Getter et Setter pour 'id'
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter et Setter pour 'eventName'
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    // Getter et Setter pour 'description'
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter et Setter pour 'dateDebut'
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    // Getter et Setter pour 'dateFin'
    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    // Getter et Setter pour 'cout'
    public float getCout() {
        return cout;
    }

    public void setCout(float cout) {
        this.cout = cout;
    }

    // Getter et Setter pour 'participants'
    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    // Getter et Setter pour 'logistics'
    public Set<Logistics> getLogistics() {
        return logistics;
    }

    public void setLogistics(Set<Logistics> logistics) {
        this.logistics = logistics;
    }

    // Méthode pour calculer le coût de l'événement
    public float calculateEventCost() {
        float somme = 0f;

        if (logistics == null || logistics.isEmpty()) {
            log.warn("Aucun élément logistique trouvé pour l'événement {}", this.description); // Log warning if no logistics found
        }

        for (Logistics logisticsItem : logistics) {
            if (logisticsItem.isReserve()) {
                somme += logisticsItem.getPrixUnit() * logisticsItem.getQuantite();
            }
        }

        return somme; // Return the calculated sum
    }

    // Méthode pour logger le coût de l'événement
    public void logEventCost() {
        float somme = calculateEventCost(); // Calcul du coût via la méthode

        // Logger du coût de l'événement avec SLF4J
        log.info("Coût de l'Event '{}' est {}", this.description, somme); // Using {} for parameterized logging
    }

    // Méthode pour logger les détails de l'événement
    public void logEventDetails() {
        // Log de la description de l'événement
        log.info("Détails de l'événement: {}", this.description); // Parameterized logging for cleaner format
    }

    // Additional helper method for logging all logistics information if needed
    public void logLogisticsDetails() {
        if (logistics != null && !logistics.isEmpty()) {
            logistics.forEach(logistic -> log.info("Logistique pour l'événement {}: {}", this.description, logistic.toString()));
        } else {
            log.info("Aucune logistique associée à l'événement {}", this.description);
        }
    }
}
