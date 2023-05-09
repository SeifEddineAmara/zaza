package tn.devteam.immonexus.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.BadWords;
import tn.devteam.immonexus.Interfaces.IBadWordsService;

import java.util.List;

@RestController
@RequestMapping("/badwords")
public class BadWordsController {


    IBadWordsService badWordsService;

    public BadWordsController(IBadWordsService badWordsService) {
        this.badWordsService = badWordsService;
    }

    @GetMapping
    public List<BadWords> getAllBadWords() {
        return badWordsService.getAllBadWords();
    }

    @PostMapping("/badWordsAdd")
    public ResponseEntity<BadWords> addBadWord(@RequestBody BadWords badWords) {
        BadWords addedBadWord = badWordsService.addBadWord(badWords);
        return new ResponseEntity<>(addedBadWord, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBadWord(@PathVariable Long id) {
        badWordsService.deleteBadWord(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BadWords> updateBadWord(@PathVariable Long id, @RequestBody BadWords badWords) {
        BadWords updatedBadWord = badWordsService.updateBadWord(id, badWords);
        return ResponseEntity.ok(updatedBadWord);
    }
}

