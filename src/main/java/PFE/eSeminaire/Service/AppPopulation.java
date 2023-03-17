package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Message;
import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Deux utilisateurs sont maintenant disponibles
 * Plus tard on va peupler l'application
 */

@Service
public class AppPopulation {

    @Autowired
    UserService US;

    @Autowired
    TeamService TS;

    @Autowired
    SeminarService SS;

    @Autowired
    MessageService MS;

    @PostConstruct
    private void init(){

        Team team = new Team();
        team.setName("LIRICA");
        ArrayList<User> members = new ArrayList<>();
        team.setMembers(members);
        TS.save(team);

        /**
        * Damien
        */
        User user1 = new User();
        ArrayList<String> roles_user1 = new ArrayList<>();
        roles_user1.add("USER");
        user1.setRoles(roles_user1);
        user1.setPassword("aaa");
        user1.setFirstName("Damien");
        user1.setName("Coquard");
        user1.setMail("maild");
        user1.setTeam(team);
        US.save(user1);

        members.add(user1);
        TS.update(team);

        user1 = new User();
        roles_user1 = new ArrayList<>();
        roles_user1.add("RESPO");
        roles_user1.add("ADMIN");
        roles_user1.add("USER");
        user1.setRoles(roles_user1);
        user1.setPassword("aaa");
        user1.setFirstName("damienAdmin");
        user1.setName("coquard");
        user1.setMail("mailadmin");
        user1.setTeam(team);
        US.save(user1);
        members.add(user1);
        TS.update(team);

        user1 = new User();
        roles_user1 = new ArrayList<>();
        roles_user1.add("USER");
        roles_user1.add("RESPO");
        user1.setRoles(roles_user1);
        user1.setPassword("aaa");
        user1.setFirstName("damienRespoSeminaire");
        user1.setName("coquard");
        user1.setMail("mailrespo");
        user1.setTeam(team);
        US.save(user1);
        members.add(user1);
        TS.update(team);

        /**
         * Katia
         */

        User user2 = new User();
        ArrayList<String> roles_user2 = new ArrayList<>();
        roles_user2.add("USER");
        user2.setRoles(roles_user2);
        user2.setPassword("aaa");
        user2.setFirstName("katia");
        user2.setName("hallai");
        user2.setMail("mailk");
        user2.setTeam(team);
        US.save(user2);

        members.add(user2);
        TS.update(team);

        user2 = new User();
        roles_user2 = new ArrayList<>();
        roles_user2.add("ADMIN");
        roles_user2.add("USER");
        user2.setRoles(roles_user2);
        user2.setPassword("aaa");
        user2.setFirstName("Kati");
        user2.setName("HAL");
        user2.setMail("mailad");
        user2.setTeam(team);
        US.save(user2);

        members.add(user2);
        TS.update(team);

        /**
         * Line JAMET JAKUBIEC
         */

        User user3 = new User();
        ArrayList<String> roles_user3 = new ArrayList<>();
        roles_user3 = new ArrayList<>();
        roles_user3.add("ADMIN");
        roles_user3.add("USER");
        user3.setRoles(roles_user2);
        user3.setPassword("aaa");
        user3.setFirstName("line");
        user3.setName("JAMET JAKUBIEC");
        user3.setMail("line.jamet@admin.univ_amu.fr");
        user3.setTeam(team);
        US.save(user3);

        members.add(user3);
        TS.update(team);

        /**
         * Peuplement de seminaires
         */
        for(int i=1; i<=10; i++){
            Seminar seminar = new Seminar();
            List<User> users = new ArrayList<>();
            users.add(user3);
            seminar.setTitle("La femme et l'informatique");
            Date date = new Date();
            seminar.setDate(date);
            seminar.setLocation("Campus Luminy, Marseille 9éme");
            seminar.setAuthors(users);
            seminar.setTeam(team);
            List<String> links = new ArrayList<>();
            links.add(user3.getMail());
            seminar.setOptionalContentLinks(links);
            String description = "Alors qu’en 1978, 50 % des étudiant·es en informatique étaient des femmes..." ;

            seminar.setDescription(description);
            SS.save(seminar);


        }

        for(int i=1; i<=5; i++){
            Seminar seminar = new Seminar();
            List<User> users = new ArrayList<>();
            users.add(user3);
            seminar.setTitle("L'impact de l'IA sur le monde");
            Date date = new Date();
            seminar.setDate(date);
            seminar.setLocation("Campus Luminy, Marseille 9éme");
            seminar.setAuthors(users);
            seminar.setTeam(team);
            List<String> links = new ArrayList<>();
            links.add(user3.getMail());
            seminar.setOptionalContentLinks(links);
            String description = "L'Intelligence Artificielle (IA) est une technologie qui a un impact profond et croissant sur le monde dans de nombreux domaines..." ;
            seminar.setDescription(description);
            SS.save(seminar);
        }

        /**
         * Peuplement de forum (avec des messages)
         */

        Message m1 = new Message();
        m1.setSubject("Seminaire sur la femme en informatique");
        m1.setUser(user2);
        m1.setContent("Combien de temps ça va durer ?");
        m1.setDate(new Date());
        MS.save(m1);

        Message m2 = new Message();
        m2.setSubject("Seminaire sur la femme en informatique");
        m2.setUser(user3);
        m2.setContent("Oui, 2h");
        m2.setDate(new Date());
        MS.save(m2);

    }
}
