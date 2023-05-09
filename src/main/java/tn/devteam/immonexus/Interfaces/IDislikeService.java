package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Announcement;

public interface IDislikeService {
    void DislikeAnnounce(Announcement announcement, Long idUser);

    Integer getDisLikes(Long idAnnonce);
}
