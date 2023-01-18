package PFE.eSeminaire.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@RestController
public class AppController {

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
        return new ModelAndView("admin");
    }




}
