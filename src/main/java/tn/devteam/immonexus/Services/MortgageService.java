package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Interfaces.IMortgageService;
import tn.devteam.immonexus.Repository.MortgageRepository;

@Service
@Slf4j
public class MortgageService implements IMortgageService {
    @Autowired
    MortgageRepository mortgageRepository;
}
