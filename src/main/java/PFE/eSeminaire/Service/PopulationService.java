package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
public class PopulationService {

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @PostConstruct
    private void init(){
        Team team = new Team();
        team.setName("team 1");
        ArrayList<User> members = new ArrayList<>();
        team.setMembers(members);
        teamService.save(team);

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

        members.add(user);
        teamService.update(team);
    }
}
