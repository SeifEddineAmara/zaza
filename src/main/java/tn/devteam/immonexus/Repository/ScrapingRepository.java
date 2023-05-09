package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Scraping;
@Repository
public interface ScrapingRepository extends JpaRepository< Scraping,Long> {
}
