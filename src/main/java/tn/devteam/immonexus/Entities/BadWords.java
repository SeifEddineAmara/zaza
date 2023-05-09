package tn.devteam.immonexus.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BadWords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Set<String> words;

    public BadWords() {
        this.words = new HashSet<>();
    }

    public void addWord(String word) {
        this.words.add(word);
    }

    public void removeWord(String word) {
        this.words.remove(word);
    }

    public Set<String> getWords() {
        return words;
    }

    public void setWords(Set<String> words) {
        this.words = words;
    }
}
