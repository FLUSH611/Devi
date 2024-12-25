package tn.esprit.eventsproject.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;
import tn.esprit.eventsproject.repositories.ParticipantRepository;

@Entity
public class Participant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;  // Identifiant unique du participant (clé primaire)

    private int idPart;  // Identifiant secondaire du participant
    private String nom;  // Nom du participant
    private String prenom;  // Prénom du participant

    @Enumerated(EnumType.STRING)
    private Tache tache;  // Enums pour les tâches du participant

    // Relation Many-to-Many avec Event
    @ManyToMany
    @JoinTable(
            name = "participant_event",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events;  // Liste des événements auxquels le participant participe

    // Relation Many-to-Many avec Participant (référence réciproque dans Event)
    @ManyToMany(mappedBy = "participants")
    private Set<Participant> participants; // Liste des participants à un événement

    // Constructeur par défaut
    public Participant() {
    }

    // Constructeur avec paramètres
    public Participant(int idPart, String nom, String prenom, Set<Event> events) {
        this.idPart = idPart;
        this.nom = nom;
        this.prenom = prenom;
        this.events = events;
    }

    // Getter et Setter pour 'id'
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter et Setter pour 'idPart'
    public int getIdPart() {
        return idPart;
    }

    public void setIdPart(int idPart) {
        this.idPart = idPart;
    }

    // Getter et Setter pour 'nom'
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter et Setter pour 'prenom'
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // Getter et Setter pour 'tache'
    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    // Getter et Setter pour 'events'
    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    // Getter et Setter pour 'participants'
    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    // Méthode pour récupérer un participant par son ID
    public static Participant getParticipantById(ParticipantRepository participantRepository, int participantId) {
        return participantRepository.findById(participantId)
                .orElseThrow(() -> new IllegalArgumentException("Participant not found"));
    }
}
