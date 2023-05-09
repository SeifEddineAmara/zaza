package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Auction;
@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
