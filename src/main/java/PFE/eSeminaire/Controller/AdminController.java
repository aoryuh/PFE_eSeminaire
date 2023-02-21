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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

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

    @RequestMapping(value = "/userDetail/{id}",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView viewUserDetails(@PathVariable Long id) {
        Optional<User> user = userService.get(id);

            if (user.isPresent()) {
                return new ModelAndView("userDetail", "user", user.get());

            } else {
                System.out.println("Error Found"); // error message
            }
            return null;
        }

    @RequestMapping(value = "/seminarUpdate/{id}", method = RequestMethod.GET)
    public ModelAndView seminarUpdate(@PathVariable Long id) {
        Seminar seminar = seminarService.findById(id).get();
        return new ModelAndView("addSeminar", "seminar", seminar);
    }
    @RequestMapping(value = "/seminarUpdate/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String seminarUpdate(@ModelAttribute @Valid Seminar seminar, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "/addSeminar";
        }
        seminarService.update(id,seminar);
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
        user.setTeam(currentUser.getTeam());
        userService.save(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/addSeminar", method = RequestMethod.GET)
    public String addSeminar(@ModelAttribute Seminar seminar) {
        return "addSeminar";
    }

    @RequestMapping(value = "/addSeminar", method = RequestMethod.POST)
    public String addUser(@ModelAttribute @Valid Seminar seminar, BindingResult result) {
        if (result.hasErrors()) {
            return "/addSeminar";
        }
        seminar.setTeam(seminar.getAuthor().getTeam());
        seminarService.save(seminar);
        return "redirect:/admin";
    }




    @ModelAttribute("ListUsersOfTeam")
    public Map<User, String> ListUsersOfTeam() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserPrincipal loggedUser = (MyUserPrincipal) authentication.getPrincipal();
        User currentUser;
        currentUser = userService.findByMail(loggedUser.getUsername()).get();
        Map<User, String> map = new HashMap<>();
        String nameFirstName;
        for (User user : userService.getUsersOfTeam(currentUser.getTeam())){
            nameFirstName = user.getFirstName() + " " + user.getName();
            map.put(user, nameFirstName);
        }
        return map;
    }
}


