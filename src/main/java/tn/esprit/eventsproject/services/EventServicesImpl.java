package tn.esprit.eventsproject.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;   // For JUnit 5
import org.junit.jupiter.api.BeforeEach; // For setup in JUnit 5
import tn.esprit.eventsproject.entities.*;
import tn.esprit.eventsproject.repositories.*;
import java.time.LocalDate;
import java.util.*;

class EventServicesImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @Mock
    private LogisticsRepository logisticsRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    private Event event;
    private Participant participant;
    private Logistics logistics;
    private Set<Participant> participants;
    private Set<Logistics> logisticsSet;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Mock Event
        event = new Event();
        event.setDescription("Sample Event");

        // Mock Participant
        participant = new Participant();
        participant.setIdPart(1);
        participant.setNom("Tounsi");
        participant.setPrenom("Ahmed");
        participant.setEvents(new HashSet<>());

        // Mock Logistics
        logistics = new Logistics();
        logistics.setReserve(true);
        logistics.setPrixUnit(100);
        logistics.setQuantite(2);

        participants = new HashSet<>();
        participants.add(participant);

        logisticsSet = new HashSet<>();
        logisticsSet.add(logistics);
    }

    @Test
    void testAddParticipant() {
        // Arrange
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        // Act
        Participant savedParticipant = eventServices.addParticipant(participant);

        // Assert
        assertNotNull(savedParticipant);
        verify(participantRepository, times(1)).save(participant);
    }

    @Test
    void testAddAffectEvenParticipant() {
        // Arrange
        when(participantRepository.findById(1)).thenReturn(Optional.of(participant));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        // Act
        Event updatedEvent = eventServices.addAffectEvenParticipant(event, 1);

        // Assert
        assertTrue(updatedEvent.getParticipants().contains(participant));
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void testAddAffectLog() {
        // Arrange
        when(eventRepository.findByDescription("Sample Event")).thenReturn(event);
        when(logisticsRepository.save(any(Logistics.class))).thenReturn(logistics);

        // Act
        Logistics savedLogistics = eventServices.addAffectLog(logistics, "Sample Event");

        // Assert
        assertNotNull(savedLogistics);
        verify(logisticsRepository, times(1)).save(logistics);
    }

    @Test
    void testGetLogisticsDates() {
        // Arrange
        when(eventRepository.findByDateDebutBetween(any(LocalDate.class), any(LocalDate.class))).thenReturn(Collections.singletonList(event));

        // Act
        List<Logistics> logisticsList = eventServices.getLogisticsDates(LocalDate.now(), LocalDate.now().plusDays(1));

        // Assert
        assertNotNull(logisticsList);
        assertEquals(1, logisticsList.size());
    }

    @Test
    void testCalculCout() {
        // Arrange
        List<Event> events = new ArrayList<>();
        events.add(event);
        when(eventRepository.findByParticipants_NomAndParticipants_PrenomAndParticipants_Tache("Tounsi", "Ahmed", Tache.ORGANISATEUR)).thenReturn(events);
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        // Act
        eventServices.calculCout();  // Calling the actual method

        // Assert
        assertEquals(200, event.getCout());
        verify(eventRepository, times(1)).save(event);
    }
}
