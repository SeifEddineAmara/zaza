package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Interfaces.IPropertyValuationService;
import tn.devteam.immonexus.Repository.PropertyValuationRepository;

@Service
@Slf4j
public class PropertyValuationService implements IPropertyValuationService {
    @Autowired
    PropertyValuationRepository valuationRepository;
}
