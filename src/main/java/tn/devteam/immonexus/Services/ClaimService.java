package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Entities.ClaimStatsDTO;
import tn.devteam.immonexus.Entities.ReclamationType;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.Interfaces.IClaimService;
import tn.devteam.immonexus.Repository.ClaimRepository;
import tn.devteam.immonexus.Repository.ReponseRecRepository;
import tn.devteam.immonexus.Repository.UserRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
public class ClaimService implements IClaimService {
    @Autowired
    ClaimRepository claimRepository;

    @Autowired
    UserRepository userRepository;


    @Autowired
    ReponseRecRepository reponseRecRepository;

    private final String[] badWords = {"fuck", "bitch", "shit"};
    private String filterComplaint(String complaint) {
        for (String badWord : badWords) {
            int wordStart = complaint.indexOf(badWord);
            while (wordStart >= 0) {
                int wordEnd = wordStart + badWord.length() - 1;
                String filteredWord = badWord.charAt(0) + repeat("*", badWord.length() - 2) + badWord.charAt(badWord.length() - 1);
                complaint = complaint.substring(0, wordStart) + filteredWord + complaint.substring(wordEnd + 1);
                wordStart = complaint.indexOf(badWord, wordEnd + 1);
            }
        }
        return complaint;
    }

    private String repeat(String s, int n) {
        return String.join("", Collections.nCopies(n, s));
    }
    @Override
    @Transactional
    public Claim addReclamation(Claim r, Long id) {
        User user = userRepository.findById(id).orElse(null);
        r.setUser(user);
        String filteredComplaint = filterComplaint(r.getDescription());
        r.setDescription(filteredComplaint);
        Claim addedClaim = claimRepository.save(r);
        return addedClaim;
    }

    @Override
    public List<Claim> getAllReclamations() {

        return (List<Claim>) claimRepository.findAll();
    }
    @Override
    public void deleteReclamation(Long id) {
        Claim r= claimRepository.findById(id).get();
        claimRepository.delete(r);
    }
    @Override
    public void marqueTraitee(Long id) {
        Claim rec = (Claim) claimRepository.findById(id).orElse(null)	;
        rec.setTraitee(true);
        claimRepository.save(rec);
    }
    @Override
    public List<Claim> getReclamationsByUser(Long idUser) {
        List<Claim> first = (List<Claim>) claimRepository.findAll();
        List<Claim> second = new ArrayList<Claim>();
        for(Claim rec : first)
        {
            if(rec.getUser().getId()==idUser)
            {
                second.add(rec);

            }
        }

        return second;
    }
    @Override
    public List<Claim> getReclamationsNonTraitees() {
        List<Claim> myList = claimRepository.findAllNonTraitees();
        return myList;
    }
    @Override
    public List<Claim> getReclamationsTraitees() {
        List<Claim> myList = claimRepository.findAllTraitees();
        return myList;
    }

    @Override
    public Claim getReclamationById(Long id) {
        return claimRepository.findById(id).orElse(null);
    }

    //statistique test

     @Override
    public List<ClaimStatsDTO> getReclamationStatsByType() {
        List<ClaimStatsDTO> stats = new ArrayList<>();
        for (ReclamationType type : ReclamationType.values()) {
            Long count = claimRepository.countByType(type);
            stats.add(new ClaimStatsDTO(type.toString(), count));
        }
        return stats;
    }




}
