package PFE.eSeminaire.Controller;

import PFE.eSeminaire.Service.SeminarService;
import PFE.eSeminaire.Service.TeamService;
import PFE.eSeminaire.Service.UserService;
import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.User;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequestMapping("/myTeam")
@Controller
public class MyTeamController {

    @Autowired
    MyUserDetails userDetailsService;

    @Autowired
    SeminarService seminarService;

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView myTeamPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserPrincipal loggedUser = (MyUserPrincipal) authentication.getPrincipal();
        User user;
        user = userService.findByMail(loggedUser.getUsername()).get();
        Collection<User> users = userService.getUsersOfTeam(teamService.getTeamFromUser(user));
        Collection<Seminar> seminarsOfUserTeam = seminarService.getSeminarsOfTeam(user.getTeam());
        ModelAndView model = new ModelAndView("myTeam");
        model.addObject("seminar", seminarsOfUserTeam);
        model.addObject("users", users);
        return model;
    }

    @RequestMapping(value = "../seminarDetails/{idSeminar}", method = RequestMethod.GET)
    public ModelAndView viewSeminarDetails(@PathVariable("idSeminar") Long id) {
        Optional<Seminar> seminar = seminarService.get(id);

        return new ModelAndView("seminarDetail", "seminar", seminar.get());
    }

    @RequestMapping(value = "/userDetails/{idUser}", method = RequestMethod.GET)
    public ModelAndView viewUserDetails(@PathVariable("idUser") Long id) {
        Optional<User> user = userService.get(id);
        if (user.isPresent()) {
            List<Seminar> seminars = seminarService.getSeminarsOfUser(user.get());
            ModelAndView modelAndView = new ModelAndView("userDetails", "user", user.get());
            modelAndView.addObject("seminars", seminars);
            return modelAndView;
        } else {
            System.out.println("Error Found"); // error message
        }
        return null;
    }
}
