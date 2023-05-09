package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Interfaces.ISubjectForumService;
import tn.devteam.immonexus.Repository.SubjectForumRepository;

@Service
@Slf4j
public class SubjectForumService implements ISubjectForumService {
    @Autowired
    SubjectForumRepository subjectForumRepository;
}
