package tn.devteam.immonexus.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.devteam.immonexus.Interfaces.IFileUploadService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileUploadService implements IFileUploadService {
    @Override
    public void uploadfile(MultipartFile file) throws IllegalStateException, IOException {
     //   file.transferTo(new File("C:\\Users\\Mon Pc\\Desktop\\ImmoNexus12\\src\\assets\\PiDevFiles\\"+file.getOriginalFilename()));
           file.transferTo(new File("C:\\Users\\TECHNOPC\\Desktop\\ImmoFront\\PiImmoFront\\src\\assets\\"+file.getOriginalFilename()));
    }
    @Override
    public void saveImage(MultipartFile file) throws IOException {
        Files.write(Paths.get("C:\\Users\\Mon Pc\\Desktop\\Esprit\\4SAE3\\Angular\\testFront\\src\\assets\\" + file.getOriginalFilename()), file.getBytes());
    }
}
