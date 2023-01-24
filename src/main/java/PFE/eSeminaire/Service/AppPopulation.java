package PFE.eSeminaire.Service;

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
/*
        User user = new User();
        ArrayList<String> roles = new ArrayList<>();
        roles.add("USER");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("damien");
        user.setName("coquard");
        user.setMail("mail");
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
        user.setFirstName("damienAdmin");
        user.setName("coquard");
        user.setMail("mailadmin");
        user.setTeam(team);
        userService.save(user);

 */

        User user = new User();
        ArrayList<String> roles = new ArrayList<>();
        roles.add("USER");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("katia");
        user.setName("hallai");
        user.setMail("mail");
        user.setTeam(team);
        US.save(user);

        members.add(user);
        TS.update(team);

        user = new User();
        roles = new ArrayList<>();
        roles.add("ADMIN");
        roles.add("USER");
        user.setRoles(roles);
        user.setPassword("aaa");
        user.setFirstName("kat");
        user.setName("hal");
        user.setMail("mailad");
        user.setTeam(team);
        US.save(user);

        members.add(user);
        TS.update(team);

        for(int i=1; i<=10; i++){
            Seminar seminar = new Seminar();
            seminar.setIdSeminar((long) i);
            seminar.setTitle("La femme et l'informatique");
            Date date = new Date(2023,01,01);
            seminar.setDate(date);
            seminar.setLocation("Campus Luminy, Marseille 9éme");
            seminar.setAuthor(user);
            seminar.setTeam(team);
            List<String> links = new ArrayList<>();
            String s = "etu.univ_amu.fr";
            links.add(s);
            seminar.setOptionalContentLinks(links);
            SS.save(seminar);
        }






    }
}
