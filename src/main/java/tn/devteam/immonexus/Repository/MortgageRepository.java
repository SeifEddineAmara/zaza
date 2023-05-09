package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Mortgage;
@Repository
public interface MortgageRepository extends JpaRepository<Mortgage,Long> {
}
