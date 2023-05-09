package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Interfaces.IMessageForumService;
import tn.devteam.immonexus.Repository.MessageForumRepository;

@Service
@Slf4j
public class MessageForumService implements IMessageForumService {
    @Autowired
    MessageForumRepository messageForumRepository;
}
