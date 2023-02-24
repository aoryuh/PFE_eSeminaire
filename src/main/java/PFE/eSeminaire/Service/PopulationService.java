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

@Service
public class PopulationService {

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @Autowired
    SeminarService seminarService;

    @Autowired
    MessageService messageService;

    @PostConstruct
    private void init(){

        /**
         * Peuplement en team
         */
        Team team = new Team();
        team.setName("Archive");
        ArrayList<Seminar> seminars = new ArrayList<>();
        ArrayList<User> members = new ArrayList<>();
        team.setMembers(members);
        teamService.save(team);

        team = new Team();
        team.setName("LIRICA");
        team.setMembers(members);
        teamService.save(team);

        /**
         * Peuplement en user
         */
        User user = new User();
        ArrayList<String> roles = new ArrayList<>();
        roles.add("USER");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("damien");
        user.setName("coquard");
        user.setMail("maildam");
        user.setTeam(team);
        userService.save(user);

        user = new User();
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("katia");
        user.setName("hallai");
        user.setMail("mailkat");
        user.setTeam(team);
        userService.save(user);

        members.add(user);
        teamService.update(team);

        ArrayList<String> optionalContent = new ArrayList();
        ArrayList<User> authors = new ArrayList();

        authors.add(user);
        Seminar seminar = new Seminar();
        seminar.setTitle("seminaire");
        seminar.setDescription("description de \"seminaire\"");
        seminar.setDate(new Date());
        seminar.setLocation("location");
        seminar.setAuthors(authors);
        seminar.setTeam(team);
        seminar.setOptionalContentLinks(optionalContent);
        seminars.add(seminar);
        team.setSeminars(seminars);

        seminarService.save(seminar);
        teamService.update(team);

        members.add(user);
        teamService.update(team);

        /**
         * Damien user
         */
        user = new User();
        roles = new ArrayList<>();
        roles.add("ADMIN");
        roles.add("RESPO");
        roles.add("USER");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("damienAdmin");
        user.setName("coquard");
        user.setMail("mailadmin");
        user.setTeam(team);
        userService.save(user);

        members.add(user);
        teamService.update(team);

        user = new User();
        roles = new ArrayList<>();
        roles.add("USER");
        roles.add("RESPO");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("damienRespoSeminaire");
        user.setName("coquard");
        user.setMail("mailrespo");
        user.setTeam(team);
        userService.save(user);
        members.add(user);
        teamService.update(team);


        authors.add(user);
        seminar = new Seminar();
        seminar.setTitle("seminaire 2");
        seminar.setDescription("description de \"seminaire 2\"");
        seminar.setDate(new Date());
        seminar.setLocation("location");
        seminar.setAuthors(authors);
        seminar.setTeam(team);
        seminar.setOptionalContentLinks(optionalContent);
        seminars.add(seminar);
        team.setSeminars(seminars);

        seminarService.save(seminar);
        teamService.update(team);


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
        userService.save(user2);

        members.add(user2);
        teamService.update(team);

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
        userService.save(user2);

        members.add(user2);
        teamService.update(team);

        user2 = new User();
        roles_user2 = new ArrayList<>();
        roles_user2.add("USER");
        roles_user2.add("RESPO");
        user2.setRoles(roles_user2);
        user2.setPassword("aaa");
        user2.setFirstName("Katiaaa");
        user2.setName("HALLL");
        user2.setMail("mailRespo");
        user2.setTeam(team);
        userService.save(user2);

        members.add(user2);
        teamService.update(team);

        authors = new ArrayList<>();
        authors.add(user2);
        seminar = new Seminar();
        seminar.setTitle("La femme et l'informatique");
        seminar.setDescription("Alors qu’en 1978, 50 % des étudiant·es en informatique étaient des femmes...");
        Date date = new Date(2023,01,01, 0,0);
        seminar.setDate(date);
        seminar.setLocation("Campus Luminy, Marseille 9éme");
        seminar.setAuthors(authors);
        seminar.setTeam(team);
        List<String> links = new ArrayList<>();
        String m = "etu.univ_amu.fr";
        links.add(m);
        seminar.setOptionalContentLinks(links);
        seminars.add(seminar);
        team.setSeminars(seminars);

        seminarService.save(seminar);
        teamService.update(team);


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
        userService.save(user3);

        members.add(user3);
        teamService.update(team);

        /**
         * Peuplement de seminaires
         */
        for(int i=1; i<=10; i++){
            Seminar s = new Seminar();
            s.setTitle("La femme et l'informatique");
            Date d = new Date();
            s.setDate(d);
            s.setLocation("Campus Luminy, Marseille 9éme");
            s.setAuthors(authors);
            s.setTeam(team);
            List<String> l = new ArrayList<>();
            l.add(user3.getMail());
            seminar.setOptionalContentLinks(l);
            String description = "Alors qu’en 1978, 50 % des étudiant·es en informatique étaient des femmes..." ;
            s.setDescription(description);
            seminarService.save(s);


        }

        for(int i=1; i<=5; i++){
            Seminar ss = new Seminar();
            ss.setTitle("L'impact de l'IA sur le monde");
            Date dd = new Date();
            ss.setDate(date);
            ss.setLocation("Campus Luminy, Marseille 9éme");
            ss.setAuthors(authors);
            ss.setTeam(team);
            List<String> ll = new ArrayList<>();
            ll.add(user3.getMail());
            ss.setOptionalContentLinks(ll);
            String description = "L'Intelligence Artificielle (IA) est une technologie qui a un impact profond et croissant sur le monde dans de nombreux domaines..." ;
            ss.setDescription(description);
            seminarService.save(ss);
        }

        /**
         * Peuplement de forum (avec des messages)
         */
        Message newMessage= new Message();
        newMessage.setSubject("Seminaire sur la femme en informatique");
        newMessage.setUser(user2);
        newMessage.setContent("Combien de temps ça va durer ?");
        newMessage.setDate(new Date());
        messageService.save(newMessage);

        newMessage = new Message();
        newMessage.setSubject("Seminaire sur la femme en informatique");
        newMessage.setUser(user);
        newMessage.setContent("2h");
        newMessage.setDate(new Date());
        messageService.save(newMessage);
    }

}
