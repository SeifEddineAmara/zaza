package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Affordability;
@Repository
public interface AffordabilityRepository extends JpaRepository<Affordability, Long> {
}
