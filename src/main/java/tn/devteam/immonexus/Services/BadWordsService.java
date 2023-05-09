package tn.devteam.immonexus.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.BadWords;
import tn.devteam.immonexus.Interfaces.IBadWordsService;
import tn.devteam.immonexus.Repository.BadWordsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BadWordsService implements IBadWordsService {
    @Autowired
    private BadWordsRepository badWordsRepository;

    @Override
    public BadWords addBadWord(BadWords badWords) {
        return badWordsRepository.save(badWords);
    }

    @Override
    // read
    public List<BadWords> getAllBadWords() {
        return badWordsRepository.findAll();
    }

    @Override
    public Optional<BadWords> getBadWordById(Long id) {
        return badWordsRepository.findById(id);
    }

   // @Override
   // public BadWords getBadWordByWord(String word) {
    //    return badWordsRepository.findByWord(word);
   // }

    @Override
    public BadWords updateBadWord(Long id, BadWords badWords) {
        Optional<BadWords> existingBadWord = badWordsRepository.findById(id);
        if (existingBadWord.isPresent()) {
            return badWordsRepository.save(badWords);
        } else {
            throw new RuntimeException("Bad word not found with id " + id);
        }
    }

    @Override
    // delete
    public void deleteBadWord(Long id) {
        badWordsRepository.deleteById(id);
    }

}
