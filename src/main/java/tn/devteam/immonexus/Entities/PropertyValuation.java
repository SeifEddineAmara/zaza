package tn.devteam.immonexus.Entities;

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
public class PropertyValuation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPropertyValue;
    @Embedded
    private Adresse adress;
    private String message;
    private LocalDate constructionDate;
    @Enumerated(EnumType.STRING)
    private REstatus status;
    @Enumerated(EnumType.STRING)
    private RealEstateType propertyType;

}
