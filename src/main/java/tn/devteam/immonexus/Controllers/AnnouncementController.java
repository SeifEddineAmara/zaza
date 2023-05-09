package tn.devteam.immonexus.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.devteam.immonexus.Entities.Announcement;
import tn.devteam.immonexus.Entities.RealEstateType;
import tn.devteam.immonexus.Entities.TypeOffer;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.Interfaces.IAnnouncementService;
import tn.devteam.immonexus.Interfaces.IFileUploadService;
import tn.devteam.immonexus.Interfaces.IImageVerificationService;
import tn.devteam.immonexus.Interfaces.ILikeService;
import tn.devteam.immonexus.Repository.AnnouncementRepository;
import tn.devteam.immonexus.Repository.UserRepository;
import tn.devteam.immonexus.Services.twilio.SmsServiceImpl;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j

@RequestMapping("/Announcement")
public class AnnouncementController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnnouncementRepository announcementRepository;
    @Autowired
    IAnnouncementService iAnnouncementService;
    @Autowired

    IFileUploadService iFileUploadService;
    @Autowired

    IImageVerificationService iImageVerificationService;
    @Autowired
    SmsServiceImpl smsService;
    @Autowired
    ILikeService likeService;

    @PostMapping("/addAnnounce2/")
    public Announcement addAnnouncement2(@RequestParam("file") MultipartFile file,
                                         @RequestParam("announcement") String announcement,
                                         @RequestParam("idUser") Long idUser
                                         )
            throws IOException, InterruptedException {

        Announcement announcement1;
        ObjectMapper objectMapper = new ObjectMapper();
        iFileUploadService.uploadfile(file);
        announcement1 = objectMapper.readValue(announcement, Announcement.class);
        log.info("hhhhhhhhhhhhhhhhh:::::" + announcement1.getRealEstateType());
        String imagePath = file.getOriginalFilename();

        boolean c = iImageVerificationService.isRealEstateImage2(imagePath);
        if (c==false){
            log.info("Model ma khdamech w denya mad3ouka");
            announcement1.setValidity(false);
        }
        else {
            log.info("Modeljawa behi");
            announcement1.setValidity(true);
        }
       User user =userRepository.findById(idUser).orElse(null);
        if(user==null){
            log.info("User not found");
        }
        else{ announcement1.setUser(user);}
        // announcement.setImage(file.getOriginalFilename());
        return iAnnouncementService.addAnnouncement(announcement1);

        //return iAnnouncementService.addAnnouncement(announcement);
    }

    @PostMapping("/addAnnounce/")
    public Announcement addAnnouncement4(@RequestParam("dateEvenement") double price, @RequestParam("surface") double surface,
                                         @RequestParam("titre") String titre, @RequestParam("lieux") TypeOffer offer,
                                         @RequestParam("description") String description, @RequestParam("affiche") MultipartFile affiche,
                                         @RequestParam("coveredArea") double coveredArea,
                                         @RequestParam("realEstateType") RealEstateType realEstateType
    )
            throws ParseException, IOException, InterruptedException {
        LocalDate pubdate = LocalDate.now();
        Announcement announcement = new Announcement();
        announcement.setPublicationDate(pubdate);
        announcement.setTotalSurface(surface);
        announcement.setPrice(price);
        announcement.setTitre(titre);
        announcement.setPublicationDate(pubdate);
        announcement.setDescription(description);
        announcement.setOfferType(offer);
        announcement.setCoveredArea(coveredArea);
        announcement.setRealEstateType(realEstateType);
        log.info("aaaaaaaaaaaaa::::" + announcement.getImage());

        iFileUploadService.uploadfile(affiche);
//boolean v= iImageVerificationService.isRealEstateImage(affiche);
        String imagePath = affiche.getOriginalFilename();
        boolean c = iImageVerificationService.isRealEstateImage2(imagePath);
        log.info("validation AI:  true or false :" + c);
        announcement.setValidity(c);
        announcement.setImage(imagePath);

        return iAnnouncementService.addAnnouncement(announcement);
    }

    @PostMapping("/verifyImage")
    public ResponseEntity<String> verifyImage(@RequestParam("file") MultipartFile file) throws IOException {

        return iImageVerificationService.verifyImage(file);
    }
    @GetMapping("/getUserByAnnonce/{idAnnonce}")

    public User findUserByAnnonce(@PathVariable("idAnnonce") Long idAnnonce){
        return iAnnouncementService.findUserByAnnonce(idAnnonce);
    }
    @PutMapping("/updateAnnounce")
    public Announcement updateAnnouncement(@RequestBody Announcement a) {
        log.info("like number", a.getLikesNumber());
        return iAnnouncementService.updateAnnouncement(a);
    }

    @GetMapping("/get-Announce/{idAnnonce}")
    public Announcement retrieveAnnouncement(@PathVariable("idAnnonce") Long ida) {
        return iAnnouncementService.retrieveAnnouncement(ida);
    }

    @DeleteMapping("/Remove-par-Id/{idAnnonce}")
    public void removeAnnouncement(@PathVariable("idAnnonce") Long idA) {
        iAnnouncementService.removeAnnouncement(idA);
    }

    @DeleteMapping("/Remove-All")
    public void removeAll() {
        iAnnouncementService.removeAll();
    }


    @GetMapping("/get-All-Announcement")
    public List<Announcement> retrieveAllAnnoucement() {
        return iAnnouncementService.retrieveAllAnnoucement();
    }

    @GetMapping("/classer-Annonces/")
    public List<Announcement> classerAnnoncesParNoteMoyenne() {
        return iAnnouncementService.classerAnnoncesParNoteMoyenne();
    }
    @GetMapping("/Top4-Annonces/")

    public List<Announcement> findTop4(){
        return iAnnouncementService.findTop4();
    }
    @PostMapping("/Like-annoncee/")
    public void LikeAnnounce(@RequestBody Announcement announcement)
    //  @RequestParam("idUser") Long idUser) {
    {
        log.info("like number");
        Long idUser= Long.valueOf(1);
        iAnnouncementService.LikeAnnounce(announcement, idUser);
        log.info("like number ",announcement.getLikesNumber());
    }
    @PostMapping("/Dislike-annoncee/")
    public void DislikeAnnounce(@RequestBody Announcement announcement)
    //  @RequestParam("idUser") Long idUser) {
    {
        log.info("like number");
        Long idUser= Long.valueOf(1);
        iAnnouncementService.DisikeAnnounce(announcement, idUser);
        log.info("Dislikes number ",announcement.getDisLikesNumber());
    }

    @GetMapping("/Get-Likes/")
    public Integer getLikes(@RequestParam("idAnnonce") Long idAnnonce) {
        return iAnnouncementService.getLikes(idAnnonce);
    }

    @GetMapping("/Get-Recomended-Announcement/")
    public List<Announcement> recommanderAnnonces(@RequestParam("userId") Long userId) {
        return likeService.recommanderAnnonces(userId);
    }

    @GetMapping("/getAnnByOffer/")
    public List<Announcement> getAnnByOfferTypeBuy() {
        return iAnnouncementService.getAnnByOfferTypeBuy();
    }

    @GetMapping("/getAnnByOfferRent/")

    public List<Announcement> getAnnByOfferTypeRent() {
        return iAnnouncementService.getAnnByOfferTypeRent();

    }
}
