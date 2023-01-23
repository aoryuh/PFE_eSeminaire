package PFE.eSeminaire.Controller;

import PFE.eSeminaire.Service.SeminarService;
import PFE.eSeminaire.Service.TeamService;
import PFE.eSeminaire.Service.UserService;
import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.repository.SeminarRepository;
import PFE.eSeminaire.security.MyUserDetails;
import PFE.eSeminaire.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@RequestMapping("/admin")
@Controller
public class adminController {

    @Autowired
    MyUserDetails userDetailsService;

    @Autowired
    SeminarService seminarService;

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    @Autowired
    SeminarRepository seminarRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView myAdminPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserPrincipal loggedUser = (MyUserPrincipal) authentication.getPrincipal();
        User user;
        user = userService.findByMail(loggedUser.getUsername()).get();
        Collection<User> users = userService.getUsersOfTeam(teamService.getTeamFromUser(user));
        Collection<Seminar> seminarsOfUserTeam = seminarService.getSeminarsOfTeam(user.getTeam());
        ModelAndView model = new ModelAndView("admin");
        model.addObject("seminar", seminarsOfUserTeam);
        model.addObject("users", users);
        return model;
    }

    @RequestMapping(value = "/seminarDelete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String seminarFormation(@PathVariable Long id) {
        System.out.println("nb " + seminarService.getList().size());
        seminarRepository.deleteById(id);
        System.out.println("nb service" + seminarService.getList().size());
        System.out.println("nb repo" + seminarRepository.findAll().size());
        return "redirect:/admin";
    }
}
