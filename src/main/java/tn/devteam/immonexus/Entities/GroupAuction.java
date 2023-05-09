package tn.devteam.immonexus.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupAuction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGroupAuction;
    private String name;
    private String description;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "groupAuction")
    private List<Auction> auctionList;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany
    private List<User> userList;
}
