package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.devteam.immonexus.Interfaces.ISubjectForumService;

@RestController
@AllArgsConstructor
@RequestMapping("/SubjectForum")
public class SubjectForumController {
    ISubjectForumService iSubjectForumService;
}
