package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Announcement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnnounce;
    @NotNull(message = "Le titre ne peut pas être vide")
    private String titre;

    private Integer likesNumber;
    private Integer disLikesNumber;

    private String description;

    private String image;
    private double price;
    @Enumerated(EnumType.STRING)
    private TypeOffer offerType;
    private float rate;
    private String city;
    private String state;
    @Enumerated(EnumType.STRING)

    private REstatus rEstatus;
    private boolean validity;
    @Enumerated(EnumType.STRING)
    private RealEstateType realEstateType;
    private LocalDate publicationDate;
    private double totalSurface;
    private double coveredArea;
    private Integer rang;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user;


}
