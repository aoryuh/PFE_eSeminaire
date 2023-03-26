package PFE.eSeminaire.Controller;

import PFE.eSeminaire.Service.SeminarService;
import PFE.eSeminaire.Service.TeamService;
import PFE.eSeminaire.Service.UserService;
import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.security.MyUserDetails;
import antlr.collections.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RequestMapping("/")
@Controller
public class AppController {

    @Autowired
    MyUserDetails userDetailsService;

    @Autowired
    SeminarService seminarService;

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home() {
        Collection<Seminar> seminars = seminarService.getList();
        Date date = new Date();
        seminars.removeIf(seminar -> seminar.getDate().before(date));
        return new ModelAndView("home", "seminars", seminars);
    }

    @RequestMapping(value = "/archive", method = RequestMethod.GET)
    public ModelAndView archive() {
        Collection<Seminar> seminars = seminarService.getList();
        Date date = new Date();
        seminars.removeIf(seminar -> seminar.getDate().after(date));
        return new ModelAndView("archive", "seminars", seminars);
    }

    @RequestMapping(value = "/userDetail/{id}", method = RequestMethod.GET)
    public ModelAndView userDetail(@PathVariable Long id) {
        User user = userService.get(id).get();
        ModelAndView modelAndView = new ModelAndView("userDetail", "user", user);
        Collection<Seminar> seminarsOfUser = seminarService.getSeminarsOfUser(user);
        modelAndView.addObject("seminars", seminarsOfUser);
        return modelAndView;
    }

    @RequestMapping(value = "seminarDetails/{idSeminar}", method = RequestMethod.GET)
    public ModelAndView viewSeminarDetails(@PathVariable("idSeminar") Long id) {
        Optional<Seminar> seminar = seminarService.get(id);
        if (seminar.isPresent()) {
            return new ModelAndView("seminarDetail", "seminar", seminar.get());
        } else {
            System.out.println("Error Found"); // error message
        }
        return null;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchSeminar(@RequestParam("query") String query) {
        Collection<Seminar> seminars = seminarService.searchByKeyword(query);
        if (seminars.isEmpty())
            return new ModelAndView("error/researchError");
        return  new ModelAndView("search", "seminars", seminars);
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public ModelAndView homeSorted(@RequestParam("select") String select) {
        Collection<Seminar> seminars = seminarService.getList();
        if (select=="nom") {
            seminars = seminarService.getListOrderByName();
        }
        else if (select == "date"){
            seminars = seminarService.getListOrderedByDate();
        }
        return new ModelAndView("home", "seminars", seminars);

    }
}
