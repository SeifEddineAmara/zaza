package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Claim implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Le type de réclamation ne peut pas être nul")
    private ReclamationType type;
    @NotBlank(message = "La description de la réclamation ne peut pas être vide")
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReclamation = new Date(System.currentTimeMillis());
    private boolean traitee=false;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user;

    @OneToOne
    @ToString.Exclude
    @JsonIgnore
    private ReponseRec reponseReclamation;

}
