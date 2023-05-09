package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Advertising;

import java.util.Date;
import java.util.List;

@Repository
public interface AdevertisingRepository extends JpaRepository<Advertising, Long> {

    List<Advertising> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(Date startDate, Date endDate);


}
