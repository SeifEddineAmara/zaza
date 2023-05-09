package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Rating;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    @Query(value = "SELECT AVG(note) FROM Rating R where R.announcement_id_announce=:idA", nativeQuery = true)
    float RateAVG(long idA);

    List<Rating> findByAnnouncement_IdAnnounce(Long annonceId);

}
