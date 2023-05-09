package tn.devteam.immonexus.Interfaces;

import org.springframework.scheduling.annotation.Scheduled;
import tn.devteam.immonexus.Entities.Announcement;
import tn.devteam.immonexus.Entities.User;

import java.util.List;

public interface IAnnouncementService {
    List<Announcement> retrieveAllAnnoucement();


    Announcement addAnnouncement(Announcement a);

    Announcement updateAnnouncement(Announcement a);

    Announcement retrieveAnnouncement(Long idA);

    void removeAnnouncement(Long idA);

    void removeAll();

    List<Announcement> getAnnByOfferTypeBuy();

    void LikeAnnounce(Announcement announcement, Long idUser);

    void DisikeAnnounce(Announcement announcement, Long idUser);

    Integer getLikes(Long idAnnonce);

    List<Announcement> findTop4();

    void affectAnnouncetoUser(Long idAnnonce, Long idUser);

    List<Announcement> getAnnByOfferTypeRent();

    @Scheduled(cron = "0 18 00 * * *")
    List<Announcement> classerAnnoncesParNoteMoyenne();

    float SetAVGRate(Long idAnnonce);

    User findUserByAnnonce(Long idAnnonce);
}
