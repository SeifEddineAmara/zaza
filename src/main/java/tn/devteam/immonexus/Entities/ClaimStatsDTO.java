package tn.devteam.immonexus.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


public class ClaimStatsDTO {
    private String type;
    private Long count;

    public ClaimStatsDTO() {
    }

    public ClaimStatsDTO(String type, Long count) {
        this.type = type;
        this.count = count;
    }

    // Getters and setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

}

    // constructeurs, getters et setters
