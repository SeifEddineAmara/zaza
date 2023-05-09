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
public class MessageForum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMsgForum;
    private String content;
    private Integer likes;
    private Integer dislikes;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private SubjectForum subjectForum;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private User user;

}
