package tn.devteam.immonexus.Interfaces;

import tn.devteam.immonexus.Entities.Advertising;

import java.util.Date;
import java.util.List;

public interface IAdvertisingService {
    List<Advertising> getAllAdvertising();

    Advertising addAdvertising(Advertising ad);

    Advertising getAdvertisingById(Long idAd);

    Advertising updateAdvertising(Advertising add);

    void deleteById(Long idAdvertising);

    double calculerGainPublicitaire(Advertising advertising);

    Long calculerNbreDesJours(Advertising advertising);

    List<Advertising> getAdvertisingBetweenTwoDates(Date startDate, Date endDate);
}
