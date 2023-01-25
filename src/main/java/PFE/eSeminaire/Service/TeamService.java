package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    TeamRepository tr;

    public Team save(Team team) {
        return tr.save(team);
    }

    public Optional<Team> get(Long id){
        return tr.findById(id);
    }

    public Team getTeamFromUser(User user){
        return user.getTeam();
    }

    public List<Team> getList() {
        return tr.findAll();
    }

    public Team update(Team team) {
        return tr.findById(team.getIdTeam())
                .map(t->{
                    t.setName(team.getName());
                    t.setMembers(team.getMembers());
                    t.setSeminars(team.getSeminars());
                    return tr.save(t);
                }).orElseThrow(() -> new RuntimeException("user not found"));
    }

    public String delete(Long id) {
        tr.deleteById(id);
        return "User deleted";
    }
}
