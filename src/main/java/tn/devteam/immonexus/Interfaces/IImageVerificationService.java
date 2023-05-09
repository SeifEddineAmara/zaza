package tn.devteam.immonexus.Interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageVerificationService {


    boolean isRealEstateImage(MultipartFile file) throws Exception;


    /*
    @Override
    public boolean isRealEstateImage2(MultipartFile file) throws IOException {
        File imageFile = new File(String.valueOf(file));
        BufferedImage image = ImageIO.read(imageFile);

        if (image == null) {
            return false;
        }

        int width = image.getWidth();
        int height = image.getHeight();

        if (width < 400 || height < 300) {
            return false;
        }

        double aspectRatio = (double) width / (double) height;

        if (aspectRatio < 1.2 || aspectRatio > 2.5) {
            return false;
        }

        return true;
    }

     */
    ResponseEntity<String> verifyImage(MultipartFile file) throws IOException;

    /*
            @Override
            public boolean isRealEstateImage2(MultipartFile file) throws IOException {
                File imageFile = new File(String.valueOf(file));
                BufferedImage image = ImageIO.read(imageFile);

                if (image == null) {
                    return false;
                }

                int width = image.getWidth();
                int height = image.getHeight();

                if (width < 400 || height < 300) {
                    return false;
                }

                double aspectRatio = (double) width / (double) height;

                if (aspectRatio < 1.2 || aspectRatio > 2.5) {
                    return false;
                }

                return true;
            }

             */
    boolean isRealEstateImage2(String imagePath) throws IOException, InterruptedException;
}
