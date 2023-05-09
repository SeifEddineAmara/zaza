package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Advertising implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAd;
    private String title;
    private String description;

    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

    private String image;
    private Long nbrJours;

    private double coutParJour;
    private double nbrVuesCible;
    private double coutParVueCible;
    private double gainPublicitaire; // ***
    private double nbrVuesFinal;
    private String socityName;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user;

}
