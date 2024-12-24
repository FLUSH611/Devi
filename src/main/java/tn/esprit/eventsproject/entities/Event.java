package tn.esprit.eventsproject.entities;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Event implements Serializable {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idEvent;
    String description;
    LocalDate dateDebut;
    LocalDate dateFin;
    float cout;
    @ManyToMany(mappedBy = "events")
    private Set<Participant> participants;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Logistics> logistics;

}
