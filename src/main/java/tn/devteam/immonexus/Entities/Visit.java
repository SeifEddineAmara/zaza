package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVisit;
    private LocalDate visitDate;
    @Embedded
    private Adresse adresse;
    private double duration;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private Announcement announcement;
}
