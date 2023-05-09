package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Announcement;
import tn.devteam.immonexus.Entities.Dislike;
import tn.devteam.immonexus.Interfaces.IDislikeService;
import tn.devteam.immonexus.Repository.AnnouncementRepository;
import tn.devteam.immonexus.Repository.DislikeRepository;

@Service
@Slf4j
public class DislikeService implements IDislikeService {
    @Autowired
    DislikeRepository dislikeRepository;
    @Autowired
    AnnouncementRepository announcementRepository;
    @Override
    public void DislikeAnnounce(Announcement announcement, Long idUser) {
        Dislike dislike = new Dislike();
        dislike.setAnnonceId(announcement.getIdAnnounce());
        dislike.setUserId(idUser);
        dislikeRepository.save(dislike);
        Integer a = announcement.getDisLikesNumber();
        if(a==null){
            a=1;
        }else{
            a++;

        }
        announcement.setDisLikesNumber(a);
        announcementRepository.save(announcement);
    }
    @Override
    public Integer getDisLikes(Long idAnnonce){
        Announcement   announcement=announcementRepository.findById(idAnnonce).orElse(null);
        return announcement.getDisLikesNumber();
    }
}
