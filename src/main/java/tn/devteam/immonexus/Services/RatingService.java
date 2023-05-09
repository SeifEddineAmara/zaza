package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.devteam.immonexus.Entities.Announcement;
import tn.devteam.immonexus.Entities.Rating;
import tn.devteam.immonexus.Interfaces.IRatingService;
import tn.devteam.immonexus.Repository.AnnouncementRepository;
import tn.devteam.immonexus.Repository.RatingRepository;
import tn.devteam.immonexus.Repository.UserRepository;

import java.util.List;

@Service
@Slf4j
public class RatingService implements IRatingService {
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AnnouncementRepository announcementRepository;

    @Override
    public void AddRating(Rating rat, Long idAnnonce)
    {


        Announcement announcement = announcementRepository.findById(idAnnonce).orElse(null);
        Rating r = ratingRepository.save(rat);
        r.setAnnouncement(announcement);
        ratingRepository.save(r);
    }

    @Override
    public void deleteRate(Long id)
    {
        Rating p = ratingRepository.findById(id).orElse(null);
        ratingRepository.delete(p);
    }

    @Override
    public Rating updateRate(float note,String comment, Long idRate) {
        Rating existingRating = ratingRepository.findById(idRate).orElse(null);
        if (existingRating == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rating not found");
        }
        existingRating.setNote(note);
        existingRating.setComment(comment);
        return ratingRepository.save(existingRating);
    }
    @Override
    public List<Rating> getByAnnonceId(Long annonceId) {
        return ratingRepository.findByAnnouncement_IdAnnounce(annonceId);
    }
    @Override
    public Rating getById(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    public float RateAVG(Long idAnnonce) {
        // TODO Auto-generated method stub
        Announcement announcement= announcementRepository.findById(idAnnonce).orElse(null);
        float Rate= ratingRepository.RateAVG(idAnnonce);
        announcement.setRate(Rate);
        announcementRepository.save(announcement);
        return Rate;

    }
}
