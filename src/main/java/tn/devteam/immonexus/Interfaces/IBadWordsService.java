package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.BadWords;

import java.util.List;
import java.util.Optional;

public interface IBadWordsService {


    // create

    BadWords addBadWord(BadWords badWords);

    // read
    List<BadWords> getAllBadWords();

    Optional<BadWords> getBadWordById(Long id);

   // BadWords getBadWordByWord(String word);


    BadWords updateBadWord(Long id, BadWords badWords);

    // delete
    void deleteBadWord(Long id);
}
