package PFE.eSeminaire.Controller;

import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.repository.SeminarRepository;
import PFE.eSeminaire.repository.UserRepository;
import PFE.eSeminaire.security.MyUserDetails;
import PFE.eSeminaire.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;

@RequestMapping("/")
@RestController
public class AppController {

    @Autowired
    MyUserDetails userDetailsService;

    @Autowired
    SeminarRepository seminarRepository;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    @RequestMapping(value = "/myTeam", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView myTeam() {
        return new ModelAndView("myTeam");
    }

    @RequestMapping(value = "/forum", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView forum() {
        return new ModelAndView("forum");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView myAdminPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserPrincipal loggedUser = (MyUserPrincipal) authentication.getPrincipal();
        User user;
        user = userRepository.findByMail(loggedUser.getUsername()).get();
        Collection<Seminar> seminarsOfUserTeam = seminarRepository.findByTeam(user.getTeam());
        return new ModelAndView("admin", "seminar", seminarsOfUserTeam);
    }




}
