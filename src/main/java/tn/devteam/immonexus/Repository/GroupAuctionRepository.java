package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.GroupAuction;
@Repository
public interface GroupAuctionRepository extends JpaRepository<GroupAuction,Long> {
}
