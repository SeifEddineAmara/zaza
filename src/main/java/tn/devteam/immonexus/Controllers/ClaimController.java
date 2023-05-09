package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Entities.ClaimStatsDTO;
import tn.devteam.immonexus.Interfaces.IClaimService;

import java.util.List;

@RestController
@RequestMapping("/Reclamation")
public class ClaimController {


    private IClaimService rService;

    @Autowired
    public ClaimController(IClaimService rService) {
        this.rService = rService;
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Claim> addReclamation(@RequestBody Claim r, @PathVariable("userId") Long id) {
        Claim addedClaim = rService.addReclamation(r, id);
        return ResponseEntity.ok(addedClaim);
    }

    @GetMapping("/getAll")
    public List<Claim> getAllReclamations(){
        return rService.getAllReclamations();
    }
    @GetMapping("/getReclamationsTraitees")
    public List<Claim> getReclamationsTraitees() {
        return rService.getReclamationsTraitees();

    }

    @GetMapping("/getReclamationsNonTraitees")
    @ResponseBody
    public List<Claim> getReclamationsNonTraitees() {
        return rService.getReclamationsNonTraitees();

    }
    @GetMapping("/getReclamationsByClient/{idUser}")
    @ResponseBody
    public List<Claim> getReclamationsByClient(@PathVariable("idUser") Long idUser) {
        return rService.getReclamationsByUser(idUser);

    }
    @PutMapping("/marquerTraitee/{idReclamation}")
    @ResponseBody
    public void marquerTraitee(@PathVariable("idReclamation") Long idReclamation)
    {
        rService.marqueTraitee(idReclamation);
    }
    @DeleteMapping("/delete/{idReclamation}")
    public void deleteReclamation(@PathVariable("idReclamation") Long id) {
        rService.deleteReclamation(id);
    }

    @GetMapping("/by-type")
    public ResponseEntity<List<ClaimStatsDTO>> getReclamationStatsByType() {
        List<ClaimStatsDTO> stats = rService.getReclamationStatsByType();
        return ResponseEntity.ok(stats);
    }
}
