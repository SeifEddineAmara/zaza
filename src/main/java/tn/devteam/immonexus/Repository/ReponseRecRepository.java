package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.ReclamationType;
import tn.devteam.immonexus.Entities.ReponseRec;

import java.util.List;

@Repository

public interface ReponseRecRepository extends CrudRepository<ReponseRec, Long> {
    @Query("SELECT DISTINCT rep.message FROM ReponseRec rep INNER JOIN rep.reclamation rec WHERE rec.type= :reclamationType ")
    List<String> reponseSuggestion(@Param("reclamationType") ReclamationType reclamationType );
}
