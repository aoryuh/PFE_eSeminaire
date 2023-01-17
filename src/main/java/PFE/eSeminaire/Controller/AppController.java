package PFE.eSeminaire.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RequestMapping("/")
@Controller
public class AppController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("homepage/unauthentifiedHome");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public String helloAdmin() {
        return "Hello";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("struct/login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginPost() {
        return new ModelAndView("homepage/authentifiedHome");
    }

}
