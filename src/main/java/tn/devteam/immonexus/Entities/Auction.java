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
public class Auction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAuction;
    private LocalDate startTime;
    private LocalDate endTime;
    private String bid;
    private String winnerName;
    private double currentBidAmount;
    private double bidIncrement;
    private double minimBid;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private GroupAuction groupAuction;

}
