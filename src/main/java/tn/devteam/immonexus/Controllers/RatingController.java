package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.devteam.immonexus.Interfaces.IRatingService;

@RestController
@AllArgsConstructor
@RequestMapping("/Rating")
public class RatingController {
    IRatingService iRatingService;
}
