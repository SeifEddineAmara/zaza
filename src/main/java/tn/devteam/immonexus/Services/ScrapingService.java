package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Scraping;
import tn.devteam.immonexus.Interfaces.IScrapingService;
import tn.devteam.immonexus.Repository.AnnouncementRepository;
import tn.devteam.immonexus.Repository.ScrapingRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ScrapingService implements IScrapingService {
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    ScrapingRepository scrapingRepository;

    @Override
    public List<Scraping> Scrapping() throws IOException {

        // Récupérer le contenu HTML de la page à scraper
        Document document = Jsoup.connect("https://www.tayara.tn/articles").get();

        // Sélectionner tous les éléments HTML correspondant à un article
        Elements articles = document.select(".article");
        List<Scraping> annonceScrapper = new ArrayList<>();
        // Parcourir tous les articles et extraire le titre et le lien
        for (Element article : articles) {
            String title = article.select("h2.title").text();
            String description = article.select("p.description").text();
            double price = Double.parseDouble(article.select("p.price").text());
            String link = article.select("a").attr("href");
            for (Scraping annonce : annonceScrapper) {
                annonce.setTitle(title);
                annonce.setPrice(price);
                annonce.setDescription(description);

            }
            System.out.println(title + " : " + link);
        }
         scrapingRepository.saveAll(annonceScrapper);
        return annonceScrapper;

    }

    @Override
    public Scraping retrieveScraping(Long idS) {
        return scrapingRepository.findById(idS).orElse(null);
    }

    @Override
    public void removeById(Long idScp) {
        scrapingRepository.deleteById(idScp);
    }

    @Override
    public void removeAll() {
        scrapingRepository.deleteAll();
    }
}
