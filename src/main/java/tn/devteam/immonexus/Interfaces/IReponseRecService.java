package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Entities.ReponseRec;

import java.util.List;

public interface IReponseRecService {
    Claim addReponse(ReponseRec reponse, Long id);

    long getTempsAttenteReclamation(Long idReclamation);

    long getTempsAttenteMoyenReclamation();

    List<String> suggestion(Long idRec);

}
