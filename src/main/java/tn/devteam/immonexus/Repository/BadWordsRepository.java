package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.BadWords;

@Repository
public interface BadWordsRepository extends JpaRepository<BadWords, Long> {
   // BadWords findByWord(String word);
}
