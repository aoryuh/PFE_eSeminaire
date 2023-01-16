package PFE.eSeminaire.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RequestMapping("/")
@Controller
public class AppController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("homepage/unauthentifiedHome");
    }

    @RequestMapping(value = "/authentified", method = RequestMethod.GET)
    public ModelAndView homeAuthentified() {
        return new ModelAndView("homepage/authentifiedHome");
    }

}
