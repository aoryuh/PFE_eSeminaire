package PFE.eSeminaire.Controller.api;

import PFE.eSeminaire.Service.UserService;
import PFE.eSeminaire.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService us;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public User create(@RequestBody User user) {
        return us.create(user);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Optional<User> get(@PathVariable Long id) {
        return us.get(id);
    }

    @RequestMapping(value = "")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    private List<User> getList() {
        return us.getList();
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return us.update(id, user);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String delete(@PathVariable Long id) {
        return us.delete(id);
    }
}
