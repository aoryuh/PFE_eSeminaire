package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Message;
import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class PopulationService {

    @Autowired
    SeminarBuilder seminarBuilder;

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @Autowired
    SeminarService seminarService;

    @Autowired
    MessageService messageService;

    @PostConstruct
    private void init() throws IOException {

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

        ArrayList<User> authors = new ArrayList();

        authors.add(user);
        Seminar seminar;


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
        user.setMail("damien.coquard@etu.univ-amu.fr");
        user.setTeam(team);
        userService.save(user);
        members.add(user);
        teamService.update(team);


        authors.add(user);


        /**
         * Katia
         */

        user = new User();
        roles.add("USER");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("katia");
        user.setName("hallai");
        user.setMail("mailk");
        user.setTeam(team);
        userService.save(user);

        members.add(user);
        teamService.update(team);

        user = new User();
        roles = new ArrayList<>();
        roles.add("ADMIN");
        roles.add("USER");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("Kati");
        user.setName("HAL");
        user.setMail("mailad");
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
        user.setFirstName("Katiaaa");
        user.setName("HALLL");
        user.setMail("mailRespo");
        user.setTeam(team);
        userService.save(user);

        members.add(user);
        teamService.update(team);

        authors = new ArrayList<>();
        authors.add(user);
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


        Seminar ss = new Seminar();
        ss.setTitle("L'impact de l'IA sur le monde");
        Date dd = new Date();
        ss.setDate(date);
        ss.setLocation("Campus Luminy, Marseille 9éme");
        ss.setAuthors(authors);
        ss.setTeam(team);
        List<String> ll = new ArrayList<>();
        ll.add(user.getMail());
        ss.setOptionalContentLinks(ll);
        String description = "L'Intelligence Artificielle (IA) est une technologie qui a un impact profond et croissant sur le monde dans de nombre..." ;
        ss.setDescription(description);
        seminarService.save(ss);


        /**
         * Peuplement de forum (avec des messages)
         */
        Message newMessage= new Message();
        newMessage.setSubject("Seminaire sur la femme en informatique");
        newMessage.setUser(user);
        newMessage.setContent("Combien de temps ça va durer ?");
        newMessage.setDate(new Date());
        messageService.save(newMessage);

        newMessage = new Message();
        newMessage.setSubject("Seminaire sur la femme en informatique");
        newMessage.setUser(user);
        newMessage.setContent("2h");
        newMessage.setDate(new Date());
        messageService.save(newMessage);


        /*début vrai pop*/

        team = new Team();
        team.setName("University of Birmingham");
        team.setMembers(new ArrayList<>());
        teamService.save(team);

        team = new Team();
        team.setName("Ecole navale, Brest");
        team.setMembers(new ArrayList<>());
        teamService.save(team);


        user = new User();
        roles = new ArrayList<>();
        roles.add("USER");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("Van-Giang");
        user.setName("Trinh");
        user.setMail("Van-Giang_Trinh");
        user.setTeam(teamService.getByName("LIRICA"));
        userService.save(user);
        members.add(user);
        teamService.update(teamService.getByName("LIRICA"));

        user = new User();
        roles = new ArrayList<>();
        roles.add("USER");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("Nicola");
        user.setName("Olivetti");
        user.setMail("Nicola_Olivetti");
        user.setTeam(teamService.getByName("LIRICA"));
        userService.save(user);
        members.add(user);
        teamService.update(teamService.getByName("LIRICA"));

        user = new User();
        roles = new ArrayList<>();
        roles.add("USER");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("Giulio");
        user.setName("Guerrierri");
        user.setMail("Giulio_Guerrieri");
        user.setTeam(teamService.getByName("LIRICA"));

        userService.save(user);
        members.add(user);
        teamService.update(teamService.getByName("LIRICA"));

        user = new User();
        roles = new ArrayList<>();
        roles.add("USER");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("Marianna");
        user.setName("Girlando");
        user.setMail("Marianna_Girlando");
        user.setTeam(teamService.getByName("University of Birmingham"));
        userService.save(user);
        members.add(user);
        teamService.update(teamService.getByName("University of Birmingham"));

        user = new User();
        roles = new ArrayList<>();
        roles.add("USER");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("José-Luis");
        user.setName("Vilchis-Medina");
        user.setMail("José-Luis_Vilchis-Medina");
        user.setTeam(teamService.getByName("Ecole navale, Brest"));
        userService.save(user);
        members.add(user);
        teamService.update(teamService.getByName("Ecole navale, Brest"));

        user = new User();
        roles = new ArrayList<>();
        roles.add("USER");
        roles.add("ADMIN");
        roles.add("RESPO");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("utilisateur");
        user.setName("eSeminaire");
        user.setMail("utilisateur.eseminaire@gmail.com");
        user.setTeam(teamService.getByName("LIRICA"));
        roles = new ArrayList<>();
        userService.save(user);
        members.add(user);
        teamService.update(teamService.getByName("LIRICA"));


        Iterator<File> files = FileUtils.iterateFiles(new File("src/main/resources/seminarFile"),
                TrueFileFilter.INSTANCE,
                TrueFileFilter.INSTANCE);
        while (files.hasNext()) {
            seminar = seminarBuilder.build(files.next());
            seminarService.save(seminar);
            teamService.update(team);
        }



    }



}