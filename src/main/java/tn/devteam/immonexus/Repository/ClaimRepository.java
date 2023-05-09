package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Claim;
import tn.devteam.immonexus.Entities.ReclamationType;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    @Query("select r from Claim r where r.traitee= true")
    List<Claim> findAllTraitees();


    @Query("select r from Claim r where r.traitee= false")
    List<Claim>findAllNonTraitees();

    // Compter le nombre de réclamations non traitées qui contiennent un mauvais mot
    @Query("SELECT COUNT(c) FROM Claim c WHERE c.traitee = false AND c.description LIKE CONCAT('%', :badWord, '%')")
    Long countUnprocessedClaimsWithBadWord(@Param("badWord") String badWord);

    // Compter le nombre de réclamations non traitées qui ne contiennent pas de mauvais mot
    @Query("SELECT COUNT(c) FROM Claim c WHERE c.traitee = false AND c.description NOT LIKE CONCAT('%', :badWord, '%')")
    Long countUnprocessedClaimsNoBadWord(@Param("badWord") String badWord);

    long countByType(ReclamationType type);


}
