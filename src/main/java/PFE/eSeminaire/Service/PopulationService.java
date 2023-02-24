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
    MessageService MS;

    @PostConstruct
    private void init(){
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


        User user2 = new User();
        roles = new ArrayList<>();
        user2.setRoles(roles);
        user2.setPassword("aaa");
        user2.setFirstName("kat");
        user2.setName("hal");
        user2.setMail("mailad");
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
        String s = "etu.univ_amu.fr";
        links.add(s);
        seminar.setOptionalContentLinks(links);
        seminars.add(seminar);
        team.setSeminars(seminars);

        seminarService.save(seminar);
        teamService.update(team);

        Message m = new Message();
        m.setSubject("Seminaire sur la femme en informatique");
        m.setUser(user2);
        m.setContent("Combien de temps ça va durer ?");
        m.setDate(new Date());
        MS.save(m);

         m = new Message();
        m.setSubject("Seminaire sur la femme en informatique");
        m.setUser(user);
        m.setContent("2h");
        m.setDate(new Date());
        MS.save(m);
    }

}
