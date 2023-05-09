package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Announcement;
import tn.devteam.immonexus.Entities.Likes;
import tn.devteam.immonexus.Interfaces.ILikeService;
import tn.devteam.immonexus.Repository.AnnouncementRepository;
import tn.devteam.immonexus.Repository.LikeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LikeService implements ILikeService {
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    AnnouncementRepository announcementRepository;
@Override
    public void LikeAnnounce( Announcement announcement, Long idUser) {
        Likes like = new Likes();
        like.setAnnonceId(announcement.getIdAnnounce());
        like.setUserId(idUser);
        likeRepository.save(like);
        Integer a = announcement.getLikesNumber();
        if(a==null){
            a=1;
        }else{
            a++;

        }
        announcement.setLikesNumber(a);
        announcementRepository.save(announcement);
    }
    @Override
    public Integer getLikes(Long idAnnonce){
        Announcement   announcement=announcementRepository.findById(idAnnonce).orElse(null);
        return announcement.getLikesNumber();
    }
    @Override
    public List<Announcement> recommanderAnnonces(Long userId) {
        // Récupérer les annonces likées par l'utilisateur
        List<Long> annoncesLikes = likeRepository.findByUserId(userId).stream()
                .map(Likes::getAnnonceId)
                .collect(Collectors.toList());
        // Récupérer les annonces similaires aux annonces likées
        List<Announcement> annoncesRecommandees = announcementRepository.findByIdAnnounceIn(annoncesLikes);
        return annoncesRecommandees;
    }
}
