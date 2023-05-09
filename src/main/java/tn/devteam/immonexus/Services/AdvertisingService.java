package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Entities.Advertising;
import tn.devteam.immonexus.Interfaces.IAdvertisingService;
import tn.devteam.immonexus.Repository.AdevertisingRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AdvertisingService implements IAdvertisingService {
    @Autowired
    AdevertisingRepository adevertisingRepository;
    @Override
    public List<Advertising> getAllAdvertising() {
        List<Advertising> advertisings = adevertisingRepository.findAll();
        return advertisings;
    }

    @Override
    public Advertising addAdvertising(Advertising ad){
        return adevertisingRepository.save(ad) ;
    }

    @Override
    public Advertising getAdvertisingById(Long idAd)
    {
        return adevertisingRepository.findById(idAd).orElse(null);
    }

    @Override
    public Advertising updateAdvertising(Advertising add){
        return adevertisingRepository.save(add);
    }

    @Override
    public void deleteById(Long idAdvertising){
        adevertisingRepository.deleteById(idAdvertising);
    }

    @Override
    public double calculerGainPublicitaire(Advertising advertising) {
        // Calculer le coût total des vues
        double coutTotalVues = advertising.getNbrVuesCible() * advertising.getCoutParVueCible();

        // Calculer le coût total des jours
        double coutTotalJours = advertising.getNbrJours() * advertising.getCoutParJour();

        double gainPublicitaire = coutTotalVues + coutTotalJours;

        return gainPublicitaire;
    }


    @Override
    public Long calculerNbreDesJours(Advertising advertising) {
        Date startDate= advertising.getStartDate();
        Date endDate= advertising.getEndDate();
        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long nbrJours = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);

        return nbrJours;
    }





    @Override
    public List<Advertising> getAdvertisingBetweenTwoDates(Date startDate, Date endDate) {


        List<Advertising> advertisingList = adevertisingRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);

        return advertisingList;
    }

}
