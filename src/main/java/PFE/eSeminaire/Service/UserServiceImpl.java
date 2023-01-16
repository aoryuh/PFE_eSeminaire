package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.User;
import PFE.eSeminaire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepository ur;

    public User create(User user) {
        return ur.save(user);
    }

    public Optional<User> get(Long id){
        return ur.findById(id);
    }

    public List<User> getList() {
        return ur.findAll();
    }

    public User update(Long id, User user) {
        return ur.findById(id)
                .map(u->{
                    u.setName(user.getName());
                    u.setFirstName(user.getFirstName());
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
}
