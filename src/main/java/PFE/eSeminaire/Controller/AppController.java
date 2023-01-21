package PFE.eSeminaire.Controller;

import PFE.eSeminaire.Service.SeminarService;
import PFE.eSeminaire.model.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@RequestMapping("/")
@RestController
public class AppController {

    @Autowired
    SeminarService SS;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home() {

        Collection<Seminar> seminars = SS.findAllSeminars();
        return new ModelAndView("listSeminars", "seminars", seminars);
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
