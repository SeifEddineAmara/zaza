package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Entities.ReponseRec;
import tn.devteam.immonexus.Interfaces.IClaimService;
import tn.devteam.immonexus.Interfaces.IReponseRecService;
import tn.devteam.immonexus.Repository.ClaimRepository;
import tn.devteam.immonexus.Repository.ReponseRecRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class ReponsRecService implements IReponseRecService {


        private ReponseRecRepository reponseRepo;


        private ClaimRepository recRepo;


        private IClaimService recServ;

        @Autowired
    public ReponsRecService(ReponseRecRepository reponseRepo, ClaimRepository recRepo, IClaimService recServ) {
        this.reponseRepo = reponseRepo;
        this.recRepo = recRepo;
        this.recServ = recServ;
    }

    @Override
    @Transactional
    public Claim addReponse(ReponseRec reponse, Long id) {
        Claim rec = recRepo.findById(id).orElse(null);
        if (rec == null) {
            // Gérer le cas où la réclamation n'existe pas
            return null;
        }
        reponse.setReclamation(rec);
        reponseRepo.save(reponse);
        rec.setReponseReclamation(reponse);
        recServ.marqueTraitee(id);
        recRepo.save(rec);
        return recRepo.save(rec);
    }

    @Override
    public long getTempsAttenteReclamation(Long idReclamation) {
        Claim rec = (Claim) recRepo.findById(idReclamation).orElse(null);

        long duree = Math.abs(rec.getReponseReclamation().getDateReponse().getTime()-rec.getDateReclamation().getTime());
        return duree;
    }
    @Override
    public long getTempsAttenteMoyenReclamation() {
        long total=0;
        List<Claim> myList = recRepo.findAllTraitees();
        for(Claim rec : myList)
        {
            total+= Math.abs(rec.getReponseReclamation().getDateReponse().getTime()-rec.getDateReclamation().getTime());
        }
        return total/myList.size();

    }
    @Override
    public  List<String> suggestion(Long idRec) {
        Claim rec = recRepo.findById(idRec).orElse(null);
        return reponseRepo.reponseSuggestion(rec.getType());
    }

}
