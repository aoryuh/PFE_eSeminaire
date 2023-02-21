package PFE.eSeminaire.Controller;

import PFE.eSeminaire.Service.SeminarService;
import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.repository.SeminarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

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


    @RequestMapping(value = "{idSeminar}", method = RequestMethod.GET)
    public ModelAndView viewSeminarDetails(@PathVariable("idSeminar") Long id) {

        Optional<Seminar> seminar = SS.findById(id);

        if (seminar.isPresent()) {
            return new ModelAndView("detailSeminar", "seminar", seminar.get());

        } else {
            System.out.println("Error Found"); // error message
        }
        return null;

    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchSeminar(@RequestParam("query") String query) {
        Collection<Seminar> seminars = SS.searchByKeyword(query);
        return  new ModelAndView("recherche", "seminars", seminars);

    }


    @RequestMapping(value = "/forum", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView forum() {
        return new ModelAndView("forum");
    }





}
