package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EMailServiceTest {

    @Autowired
    EMailService eMailService;

    @Test
    public void testSendNewSeminar() throws java.text.ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2023-04-01 14:00:00");
        Team team = new Team();
        team.setName("Team");
        Seminar seminar = new Seminar();
        seminar.setTeam(team);
        seminar.setTitle("Title");
        seminar.setDate(date);

        ArrayList<String> mails = new ArrayList<>();
        mails.add("test1@example.com");
        mails.add("test2@example.com");

        try {
            eMailService.sendNewSeminar(seminar, mails);
        } catch (Exception e) {
            fail("Une exception a été lancée lors de l'envoi de l'email : " + e.getMessage());
        }
    }
    @Test
    public void testSendDeletedSeminar() throws  java.text.ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2023-04-01 14:00:00");
        Team team = new Team();
        team.setName("Team");
        Seminar seminar = new Seminar();
        seminar.setTeam(team);
        seminar.setTitle("Title");
        seminar.setDate(date);

        ArrayList<String> mails = new ArrayList<>();
        mails.add("test1@example.com");
        mails.add("test2@example.com");

        try {
            eMailService.sendDeletedSeminar(seminar, mails);
        } catch (Exception e) {
            fail("Une exception a été lancée lors de l'envoi de l'email : " + e.getMessage());
        }
    }

    @Test
    public void testSendupdatedSeminar() throws  java.text.ParseException {

        Team team = new Team();
        team.setName("Team");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = dateFormat.parse("2023-04-01 14:00:00");
        Seminar seminar = new Seminar();
        seminar.setTeam(team);
        seminar.setTitle("Title");
        seminar.setDate(date);

        ArrayList<String> mails = new ArrayList<>();
        mails.add("adresse1@example.com");
        mails.add("adresse2@example.com");

        try {
            eMailService.sendupdatedSeminar(seminar, mails);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        assertTrue(true);
    }

}