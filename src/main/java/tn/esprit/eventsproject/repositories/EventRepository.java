package tn.esprit.eventsproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Tache;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByParticipants_NomAndParticipants_PrenomAndParticipants_Tache(String nom, String prenom, Tache tache);
    Event findByDescription(String description);
    List<Event> findByDateDebutBetween(LocalDate startDate, LocalDate endDate);
}