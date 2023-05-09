package tn.devteam.immonexus.Entities;

import lombok.*;

import java.io.Serializable;
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adresse implements Serializable {
    private String state;
    private String city;
    private String streetNumber;
}
