package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Rating;

import java.util.List;

public interface IRatingService {
    void AddRating(Rating rat, Long idAnnonce);

    void deleteRate(Long id);

    Rating updateRate(float note, String comment, Long idRate);

    List<Rating> getByAnnonceId(Long annonceId);

    Rating getById(Long id);

    float RateAVG(Long idAnnonce);
}
