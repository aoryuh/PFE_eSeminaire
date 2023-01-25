package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository ur;

    public User save(User user) {
        if(user.getPassword().length()<20)
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ur.save(user);
    }

    public Optional<User> get(Long id){
        return ur.findById(id);
    }

    public List<User> getList() {
        return ur.findAll();
    }

    public User update(User user) {
        return ur.findById(user.getIdUser())
                .map(u->{
                    u.setName(user.getName());
                    u.setFirstName(user.getFirstName());
                    u.setMail(u.getMail());
                    u.setPassword(user.getPassword());
                    u.setTeam(user.getTeam());
                    u.setRoles(user.getRoles());
                    return ur.save(u);
                }).orElseThrow(() -> new RuntimeException("user not found"));
    }

    public String delete(Long id) {
        ur.deleteById(id);
        return "User deleted";
    }

    public Optional<User> findByMail(String mail){
        return ur.findByMail(mail);
    }

    public List<User> getUsersOfTeam(Team team){
        return ur.findByTeam(team);
    }
}
