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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {

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
        users.remove(user);
        Collection<Seminar> seminarsOfUserTeam = seminarService.getSeminarsOfTeam(user.getTeam());
        ModelAndView model = new ModelAndView("admin");
        model.addObject("seminar", seminarsOfUserTeam);
        model.addObject("users", users);
        return model;
    }

    @RequestMapping(value = "/seminarDelete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String seminarDelete(@PathVariable Long id) {
        seminarService.delete(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/userDelete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userDelete(@PathVariable Long id) {
        List<Seminar> seminarsOfUser = seminarService.getSeminarsOfUser(userService.get(id).get());
        if(!seminarsOfUser.isEmpty()){
            for (Seminar seminar: seminarsOfUser) {
                seminarService.delete(seminar.getIdSeminar());
            }
        }
        userService.delete(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/updateDelete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String seminarUpdate(@PathVariable Long id) {
        seminarService.delete(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(@ModelAttribute User user) {
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/addUser";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserPrincipal loggedUser = (MyUserPrincipal) authentication.getPrincipal();
        User currentUser;
        currentUser = userService.findByMail(loggedUser.getUsername()).get();
        System.out.println("nb" + userService.getList().size());
        user.setTeam(currentUser.getTeam());
        userService.save(user);
        System.out.println("nb" + userService.getList().size());

        return "redirect:/admin";
    }
}


