package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TeamServiceTest {

    @Autowired
    TeamService teamService;


    @Test
    public void testSave() {

        Team team = new Team();
        team.setName("team1");

        Team savedTeam = teamService.save(team);

        assertNotNull(savedTeam);

        assertNotNull(savedTeam.getIdTeam());

        assertEquals("team1", savedTeam.getName());
    }


    @Test
    public void testGet() {

        Team team = new Team();
        team.setName("team1");

        Team savedTeam = teamService.save(team);

        Optional<Team> optionalTeam = teamService.tr.findById(savedTeam.getIdTeam());

        assertTrue(optionalTeam.isPresent());

        assertNotNull(optionalTeam.get());

        assertEquals(savedTeam.getIdTeam(), optionalTeam.get().getIdTeam());

        assertEquals(savedTeam.getName(), optionalTeam.get().getName());
    }


    @Test
    public void testGetTeamFromUser() {

        Team team = new Team();
        team.setName("team1");
        teamService.save(team);

        User user = new User();

        user.setTeam(team);

        Team userTeam = teamService.getTeamFromUser(user);

        assertNotNull(userTeam);

        assertEquals(team, userTeam);
    }


    @Test
    public void testGetList() {

        Team team1 = new Team();
        team1.setName("team1");
        Team team2 = new Team();
        team2.setName("team2");

        teamService.save(team1);
        teamService.save(team2);

        List<Team> teams = teamService.getList();

        assertNotNull(teams);

        assertTrue(teams.size() >= 2);

        assertTrue(teams.contains(team1));
        assertTrue(teams.contains(team2));
    }


    @Test
    public void testUpdate() {

        Team team = new Team();
        team.setName("team1");
        teamService.save(team);

        String newName = "Updated Team";

        team.setName(newName);
        team.setMembers(new ArrayList<>());

        Team updatedTeam = teamService.update(team);

        assertEquals(newName, updatedTeam.getName());
        assertEquals(team.getMembers(), updatedTeam.getMembers());
    }

    @Test
    public void testDelete() {

        Team team = new Team();
        team.setName("team1");
        teamService.save(team);
        Long teamId = team.getIdTeam();

        String message = teamService.delete(teamId);

        assertEquals("User deleted", message);

        Optional<Team> deletedTeam = teamService.get(teamId);
        assertFalse(deletedTeam.isPresent());
    }


    @Test
    public void testTeamIsPresentByName() {

        Team team = new Team();
        team.setName("team1");
        teamService.save(team);
        String teamName = team.getName();

        boolean isPresent = teamService.teamIsPresentByName(teamName);

        assertTrue(isPresent);
    }



    @Test
    public void testGetByName() {

        Team team = new Team();
        team.setName("team1");
        teamService.save(team);
        String teamName = team.getName();

        Team foundTeam = teamService.getByName(teamName);
        assertNotNull(foundTeam);

        assertEquals(teamName, foundTeam.getName());
    }

}