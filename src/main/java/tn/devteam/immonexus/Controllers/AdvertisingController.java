package tn.devteam.immonexus.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.devteam.immonexus.Entities.Advertising;
import tn.devteam.immonexus.Entities.User;
import tn.devteam.immonexus.Interfaces.IAdvertisingService;
import tn.devteam.immonexus.Interfaces.IFileUploadService;
import tn.devteam.immonexus.Repository.AdevertisingRepository;
import tn.devteam.immonexus.Repository.UserRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/Advertising")
public class AdvertisingController {
    IAdvertisingService iAdvertisingService;
    IFileUploadService iFileUploadService;
    @Autowired
    AdevertisingRepository adevertisingRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("/get-AllAdvertising")
    public List<Advertising> getAllAdvertising() {


     /*   Date currentDate = new Date();
        List<Advertising> advertisings = iAdvertisingService.getAllAdvertising();

        List<Advertising> validAdvertisings = advertisings.stream()
                .filter(advertising -> advertising.getEndDate().after(currentDate))
                .sorted(Comparator.comparingDouble(Advertising::getGainPublicitaire).reversed())
                .collect(Collectors.toList());

        return validAdvertisings;*/
        return  iAdvertisingService.getAllAdvertising();
    }



    @DeleteMapping("/delete-Advertising-ById/{idA}")
    public void deleteById(@PathVariable("idA") Long idAdvertising) {
        iAdvertisingService.deleteById(idAdvertising);
    }

    @PostMapping("/add-Advertising")
    public Advertising addAdvertising(@RequestBody Advertising  advertising) {

        return iAdvertisingService.addAdvertising(advertising);
    }
    @PutMapping("/updateAdvertising")
    public Advertising updateAdvertising(@RequestBody Advertising advertising1) {


        Long nbrJours = iAdvertisingService.calculerNbreDesJours(advertising1);
        advertising1.setNbrJours(nbrJours);

        double gainPublicitaire = iAdvertisingService.calculerGainPublicitaire(advertising1);
        advertising1.setGainPublicitaire(gainPublicitaire);
        return iAdvertisingService.updateAdvertising(advertising1);
    }




    @PostMapping("/add-Advertisingg")
    public Advertising addAdvertising(@RequestParam("file") MultipartFile file,
                                      @RequestParam("advertising") String advertising,
                                      @RequestParam("idUser") Long idUser) throws IOException {
        Advertising advertising1;
        ObjectMapper objectMapper = new ObjectMapper();
        iFileUploadService.uploadfile(file);
        advertising1= objectMapper.readValue(advertising,Advertising.class);
        // log.info("hhhhhhhhhhhhhhhhh:::::"+advertising1.getTitre());
        Long nbrJours = iAdvertisingService.calculerNbreDesJours(advertising1);
        advertising1.setNbrJours(nbrJours);
        User user=userRepository.findById(idUser).orElse(null);
        double gainPublicitaire = iAdvertisingService.calculerGainPublicitaire(advertising1);
        advertising1.setGainPublicitaire(gainPublicitaire);
        advertising1.setUser(user);
        return  iAdvertisingService.addAdvertising(advertising1);
    }


    @GetMapping("/export-pdf/{id}")
    public ResponseEntity<ByteArrayResource> exportAdvertisingToPdf(@PathVariable Long id) throws IOException, DocumentException {
        // Récupérer la publicité à exporter
        Advertising advertising = adevertisingRepository.findById(id).orElse(null);

        // Créer un nouveau document PDF
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        Font font = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.RED);
        // Ouvrir le document
        document.open();

        // Ajouter le titre de la publicité
        Paragraph title = new Paragraph(advertising.getTitle(),font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph space = new Paragraph("\n");
        document.add(space);

        // Ajouter les détails de la publicité
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        PdfPCell cell;


        cell = new PdfPCell(new Phrase("Description"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(advertising.getDescription()));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Start Date"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(advertising.getStartDate().toString()));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("End Date"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(advertising.getEndDate().toString()));
        table.addCell(cell);



        cell = new PdfPCell(new Phrase("nbrJours"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(Long.toString(advertising.getNbrJours())));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("coutParJour"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(Double.toString(advertising.getCoutParJour())));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("NbrVuesCible"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(Double.toString(advertising.getNbrVuesCible())));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("coutParVueCible"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(Double.toString(advertising.getCoutParVueCible())));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("gainPublicitaire"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(Double.toString(advertising.getGainPublicitaire())));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("nbrVuesFinal"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(Double.toString(advertising.getNbrVuesFinal())));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("socityName"));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(advertising.getSocityName()));
        table.addCell(cell);



        document.add(table);

        // Fermer le document
        document.close();

        // Créer une ressource ByteArray pour le contenu du PDF
        byte[] bytes = outputStream.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(bytes);

        // Retourner une réponse avec le contenu du PDF
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=advertising.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(bytes.length)
                .body(resource);
    }



    @GetMapping("/between-dates/{start}/{end}")
    public ResponseEntity<List<Advertising>> getAdvertisingBetweenDates(
            @PathVariable("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDateStr,
            @PathVariable("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDateStr) {

        List<Advertising> advertisingList = iAdvertisingService.getAdvertisingBetweenTwoDates(startDateStr, endDateStr);

        if(advertisingList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(advertisingList);
    }
}
