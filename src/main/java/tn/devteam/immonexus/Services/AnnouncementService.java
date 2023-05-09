package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Announcement;
import tn.devteam.immonexus.Entities.TypeOffer;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.Interfaces.*;
import tn.devteam.immonexus.Repository.AnnouncementRepository;
import tn.devteam.immonexus.Repository.LikeRepository;
import tn.devteam.immonexus.Repository.UserRepository;

import java.util.List;

@Service
@Slf4j
public class AnnouncementService implements IAnnouncementService {
    @Autowired
    AnnouncementRepository announcementRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    IImageVerificationService iImageVerificationService;
    @Autowired
    IRatingService ratingService;
    @Autowired
    ILikeService iLikeService;
    @Autowired
    IDislikeService iDislikeService;

    @Override
    public List<Announcement> retrieveAllAnnoucement() {
        List<Announcement> annonce = announcementRepository.findAll();
        return annonce;
    }

    @Override
    public Announcement addAnnouncement(Announcement a) {

        if (a.isValidity()) {
            announcementRepository.save(a);
        } else {
            log.info("Announcement not added ");
        }
        return a;
    }

    @Override
    public Announcement updateAnnouncement(Announcement a) {
        return announcementRepository.save(a);
    }

    @Override
    public Announcement retrieveAnnouncement(Long idA) {
        return announcementRepository.findById(idA).orElse(null);
    }

    @Override
    public void removeAnnouncement(Long idA) {
        announcementRepository.deleteById(idA);
    }

    @Override
    public void removeAll() {
        announcementRepository.deleteAll();
    }

    @Override
    public List<Announcement> getAnnByOfferTypeBuy() {
        return announcementRepository.findAllByOfferTypeLike(TypeOffer.SELLS);
    }

    @Override
    public void LikeAnnounce(Announcement announcement, Long idUser) {
        iLikeService.LikeAnnounce(announcement, idUser);
    }

    @Override
    public void DisikeAnnounce(Announcement announcement, Long idUser) {
        iDislikeService.DislikeAnnounce(announcement, idUser);
    }

    @Override
    public Integer getLikes(Long idAnnonce) {
        Announcement announcement = announcementRepository.findById(idAnnonce).orElse(null);
        return announcement.getLikesNumber();
    }

    @Override
    public List<Announcement> findTop4() {
        return announcementRepository.findTop3ByOrderByLikesNumberDesc();
    }

    @Override
    public void affectAnnouncetoUser(Long idAnnonce, Long idUser) {
        Announcement announcement = announcementRepository.findById(idAnnonce).orElse(null);
        User user = userRepository.findById(idUser).orElse(null);
        announcement.setUser(user);
        announcementRepository.save(announcement);
    }

    @Override
    public List<Announcement> getAnnByOfferTypeRent() {
        return announcementRepository.findAllByOfferTypeLike(TypeOffer.RENT);
    }

    @Scheduled(cron = "0 18 00 * * *")

    @Override
    public List<Announcement> classerAnnoncesParNoteMoyenne() {
        List<Announcement> annonces = announcementRepository.findAllByOrderByRateDesc();
        int rang = 1;
        for (Announcement annonce : annonces) {
            annonce.setRang(rang++);
        }
        announcementRepository.saveAll(annonces);
        return annonces;
    }

    @Override
    public float SetAVGRate(Long idAnnonce) {
        return ratingService.RateAVG(idAnnonce);
    }

    @Override
    public User findUserByAnnonce(Long idAnnonce) {
        Announcement announcement = announcementRepository.findById(idAnnonce).orElse(null);
        return announcement.getUser();

    }

}
