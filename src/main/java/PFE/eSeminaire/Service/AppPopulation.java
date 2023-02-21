package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
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
        user1.setFirstName("damien");
        user1.setName("coquard");
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
        user2.setFirstName("kat");
        user2.setName("hal");
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
            seminar.setTitle("La femme et l'informatique");
            Date date = new Date();
            seminar.setDate(date);
            seminar.setLocation("Campus Luminy, Marseille 9éme");
            seminar.setAuthor(user3);
            seminar.setTeam(team);
            List<String> links = new ArrayList<>();
            links.add(user3.getMail());
            seminar.setOptionalContentLinks(links);
            String description = "Alors qu’en 1978, 50 % des étudiant·es en informatique étaient des femmes..." ;

            seminar.setDescription(description);
            SS.save(seminar);


        }



    }
}
