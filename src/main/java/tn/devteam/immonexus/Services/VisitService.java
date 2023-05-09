package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Interfaces.IVisitService;
import tn.devteam.immonexus.Repository.VisitRepository;

@Service
@Slf4j
public class VisitService implements IVisitService {
    @Autowired
    VisitRepository visitRepository;
}
