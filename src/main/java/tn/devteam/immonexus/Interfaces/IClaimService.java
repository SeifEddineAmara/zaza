package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Entities.ClaimStatsDTO;

import javax.transaction.Transactional;
import java.util.List;

public interface IClaimService {
    @Transactional
    Claim addReclamation(Claim r, Long id);

    List<Claim> getAllReclamations();

    void deleteReclamation(Long id);

    void marqueTraitee(Long id);

    List<Claim> getReclamationsByUser(Long idUser);

    List<Claim> getReclamationsNonTraitees();

    List<Claim> getReclamationsTraitees();

    Claim getReclamationById(Long id);

    List<ClaimStatsDTO> getReclamationStatsByType();
}
