package PFE.eSeminaire.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RequestMapping("/")
@RestController
public class AppController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("homepage/unauthentifiedHome");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("struct/login");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView homeAuthentified() {
        return new ModelAndView("homepage/authentifiedHome");
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView homeAdmin() {
        return new ModelAndView("homepage/adminHome");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPost() {
        return new ModelAndView("homepage/authentifiedHome");
    }

}
