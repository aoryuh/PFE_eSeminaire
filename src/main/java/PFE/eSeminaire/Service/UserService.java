package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository UR;

    public UserService(UserRepository fakeRepo) {
        this.UR= fakeRepo;

    }



    public User save(User user) {
        if(user.getPassword().length()<20)
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UR.save(user);
    }

    public Optional<User> get(Long id){
        return UR.findById(id);
    }

    public List<User> getList() {
        return UR.findAll();
    }

    public User update(User user) {
        return UR.findById(user.getIdUser())
                .map(u->{
                    u.setName(user.getName());
                    u.setFirstName(user.getFirstName());
                    u.setMail(u.getMail());
                    u.setPassword(user.getPassword());
                    u.setTeam(user.getTeam());
                    u.setRoles(user.getRoles());
                    return UR.save(u);
                }).orElseThrow(() -> new RuntimeException("user not found"));
    }

    public String delete(Long id) {
        UR.deleteById(id);
        return "User deleted";
    }

    public Optional<User> findByMail(String mail){
        return UR.findByMail(mail);
    }

    public List<User> getUsersOfTeam(Team team){
        return UR.findByTeam(team);
    }

    public List<User> getUsersRelatedToSeminar(Seminar seminar) {
        List<User> users = new ArrayList<>();
        for (User author : seminar.getAuthors()) {
            if (!users.contains(author.getTeam().getMembers().get(0))) {
                users.addAll(author.getTeam().getMembers());
            }
        }
        return users;
    }

    public boolean userIsPresentByMail(String mail) {
        return UR.findByMail(mail).isPresent();

    }
}