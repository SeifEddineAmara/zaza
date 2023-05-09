package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.PropertyValuation;


@Repository
public interface PropertyValuationRepository extends JpaRepository<PropertyValuation,Long> {
}
