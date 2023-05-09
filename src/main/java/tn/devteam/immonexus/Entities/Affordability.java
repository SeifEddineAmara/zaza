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
public class Affordability implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAffordability;
    private String annualInvome;
    private double downPayment;
    private double creditScore;
    private double mensualExpenses;
    private double interestRate;
    private long loanTerme;
    private String insurance;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user ;



}
