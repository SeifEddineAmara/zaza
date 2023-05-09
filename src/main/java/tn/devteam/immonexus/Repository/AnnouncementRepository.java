package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Announcement;
import tn.devteam.immonexus.Entities.TypeOffer;

import java.util.List;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findAllByOrderByRateDesc();
    List<Announcement> findByIdAnnounceIn(List<Long> annonceIds);
    List<Announcement> findAllByOfferTypeLike(TypeOffer offer);
    List<Announcement> findTop3ByOrderByLikesNumberDesc();

}
