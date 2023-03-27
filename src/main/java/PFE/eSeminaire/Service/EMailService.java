package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class EMailService  {

    @Autowired
    JavaMailSender javaMailSender;

    @Qualifier("taskExecutor")
    @Autowired
    private TaskExecutor taskExecutor;

    SimpleDateFormat day = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat hour = new SimpleDateFormat("hh:mm");


    public void sendNewSeminar(Seminar seminar, ArrayList<String> mails) throws ParseException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pfe.eseminaire@gmail.com");
        message.setSubject("Nouveau séminaire");
        message.setText("Bonjour,\n" +
                " Un nouveau séminaire a été annoncé par l'équipe de recherche "+ seminar.getTeam().getName() +" et est intitulé \"" + seminar.getTitle() + "\", il se déroulera le " + day.format(seminar.getDate()) +" à "+ hour.format(seminar.getDate())+ ".\n" +
                " Pour plus d'informations, rendez-vous sur l'application PFE.eSeminaire.");
        message.setTo(mails.toArray(new String[0]));
        taskExecutor.execute(new SimpleMailMessageRunnable(message));
    }

    public void sendDeletedSeminar(Seminar seminar, ArrayList<String> mails) throws ParseException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pfe.eseminaire@gmail.com");
        message.setSubject("séminaire annulée");
        message.setText("Bonjour,\n" +
                " Malheureusement,le séminaire \"" + seminar.getTitle() + "\", de l'équipe de recherche " + seminar.getTeam().getName() +" a été annulé\n" +
                " Vous pouvez consulter les autres séminaires prévus sur l'application PFE.eSeminaire.");
        message.setTo(mails.toArray(new String[0]));
        taskExecutor.execute(new SimpleMailMessageRunnable(message));
    }

    public void sendupdatedSeminar(Seminar seminar, ArrayList<String> mails) throws ParseException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("pfe.eseminaire@gmail.com");
        message.setSubject("séminaire modifié");
        message.setText("Bonjour,\n" +
                " Le séminaire \"" + seminar.getTitle() + "\", de l'équipe de recherche " + seminar.getTeam().getName() +" a été modifié, il se déroulera finalement le " + day.format(seminar.getDate()) +", "+ hour.format(seminar.getDate())+ "à " + seminar.getLocation() + ".\n" +
                " Vous pouvez consulter les autres séminaires prévus sur l'application PFE.eSeminaire.");
        message.setTo(mails.toArray(new String[0]));
        taskExecutor.execute(new SimpleMailMessageRunnable(message));
    }


    private class SimpleMailMessageRunnable implements Runnable {
        private SimpleMailMessage simpleMailMessage;
        private SimpleMailMessageRunnable(SimpleMailMessage simpleMailMessage) {
            this.simpleMailMessage = simpleMailMessage;
        }

        public void run() {
            String[] to = simpleMailMessage.getTo();
            for (String user : to){
                try {
                    simpleMailMessage.setTo(user);
                    javaMailSender.send(simpleMailMessage);
                    System.out.println("sent");
                } catch (MailSendException e) {
                    System.out.println("exception");
                }
            }


        }
    }



}
