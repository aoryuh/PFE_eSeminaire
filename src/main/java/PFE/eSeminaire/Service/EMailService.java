package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EMailService{
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(Seminar seminar, ArrayList<String> mails)  {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pfe.eseminaire@gmail.com");
        message.setSubject("Nouveau séminaire");
        message.setText("nouveau séminaire :" + seminar.getTitle());
        String[] to = new String[mails.size()];
        System.out.println("wddddddddddddddddddddddddddddddd" + mails.size());
        for (int i = 0; i < mails.size(); i++) {

            try{
                message.setTo(mails.get(i));
                emailSender.send(message);
            }catch (MailSendException e){

            }

        }


    }
}
