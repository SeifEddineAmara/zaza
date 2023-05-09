package tn.devteam.immonexus.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Entities.ReponseRec;
import tn.devteam.immonexus.Interfaces.IClaimService;
import tn.devteam.immonexus.Interfaces.IReponseRecService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ReponseRec")
public class ReponsRecController {

    @Autowired
    IReponseRecService rs;

    @Autowired
    IClaimService iClaimService;


    @PostMapping("/add/{recId}")
    @ResponseBody
    public Map<String, Object> addReponse(@RequestBody ReponseRec reponse, @PathVariable("recId") Long id) {
        Claim claim = iClaimService.getReclamationById(id);
        ReponseRec addedReponse = rs.addReponse(reponse, id).getReponseReclamation();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("claim", claim);
        responseMap.put("reponse", addedReponse);

        return responseMap;
    }

    @GetMapping("/getTempsAttenteReclamation/{idReclamation}")
    @ResponseBody
    public long getTempsAttenteReclamation(@PathVariable("idReclamation") Long idRec)
    {
        return rs.getTempsAttenteReclamation(idRec);
    }

    @GetMapping("/getTempsAttenteMoyenReclamation")
    @ResponseBody
    public long getTempsAttenteMoyenReclamation()
    {
        return rs.getTempsAttenteMoyenReclamation();
    }
    @GetMapping("/suggestions/{idRec}")
    public List<String> suggest(@PathVariable("idRec") Long id){
        return rs.suggestion(id);
    }
}

