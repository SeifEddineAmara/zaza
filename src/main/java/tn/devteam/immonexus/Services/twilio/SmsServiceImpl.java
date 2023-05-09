package tn.devteam.immonexus.Services.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.devteam.immonexus.Configurations.sms.TwilioConfig;
import tn.devteam.immonexus.Repository.UserRepository;


@Service
public class SmsServiceImpl {
    @Autowired
    private TwilioConfig twilioConfig;
    @Autowired
    private UserRepository userRepository;

    //SMS
    public void SendSMS() {
        // User use1r =userRepository.findById(id).orElse(null);

        String twilioNumber = "whatsapp:+14155238886";
        String toNumber = "whatsapp:+21653834156";

        String ACCOUNT_SID = "AC93f5e5239e6b07be0589867f23a5e20a";
        String AUTH_TOKEN = "201f0f1e6f5cdbffdca858a19afb42da";

        String messageBody = "Votre image est incompatible avec notre concept  : ";
        // URI mediaUrl = URI.create("https://www.example.com/image.jpg");
        //   URI linkUrl = URI.create("https://www.example.com");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        MessageCreator creator = null;
      /*  creator.setMediaUrl(mediaUrl);
        creator.setStatusCallback(URI.create("https://www.example.com/status-callback"));
        creator.setPersistentAction(URI.create(linkUrl.toString()));

       */
        creator = Message.creator(
                new PhoneNumber(toNumber),
                new PhoneNumber(twilioNumber),
                messageBody
        );
        creator.create();


    }
}
