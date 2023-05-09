package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.Scraping;
import tn.devteam.immonexus.Interfaces.IScrapingService;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Scraping")
public class ScrappingController {
    IScrapingService iScrapingService;
    @PostMapping("/Scraping")
    public List<Scraping> Scrapping() throws IOException {
        return iScrapingService.Scrapping();
    }
    @GetMapping("/get-Scraping/{idS}")
    public Scraping retrieveScraping(@PathVariable("idS") Long idS){
        return iScrapingService.retrieveScraping(idS);
    }
}
