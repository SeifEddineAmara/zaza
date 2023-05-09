package tn.devteam.immonexus.Interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileUploadService {
    void uploadfile(MultipartFile file) throws IllegalStateException, IOException;

    void saveImage(MultipartFile file) throws IOException;
}
