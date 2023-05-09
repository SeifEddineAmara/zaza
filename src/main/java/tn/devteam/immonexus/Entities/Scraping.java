package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Scraping implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idScraping;
    private String title;
    private String description;
    private double price;
    @Embedded
    private Adresse adress;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user;
}
