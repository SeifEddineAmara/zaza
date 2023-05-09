package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Scraping;

import java.io.IOException;
import java.util.List;

public interface IScrapingService {
    List<Scraping> Scrapping() throws IOException;

    Scraping retrieveScraping(Long idS);

    void removeById(Long idScp);

    void removeAll();
}
