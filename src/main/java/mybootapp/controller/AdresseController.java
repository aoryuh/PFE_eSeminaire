package mybootapp.controller;

import mybootapp.model.Adresse;
import mybootapp.model.Composante;
import mybootapp.repo.AdresseRepo;
import mybootapp.repo.ComposanteRepo;
import mybootapp.repo.FormationRepo;
import mybootapp.repo.UtilisateurRepo;
import mybootapp.service.ComposanteService;
import mybootapp.service.ListBuilder;
import mybootapp.service.PopulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class AdresseController {

    @Autowired
    FormationRepo formationRepo;

    @Autowired
    ComposanteRepo composanteRepo;

    @Autowired
    AdresseRepo adresseRepo;

    @Autowired
    ListBuilder listBuilder;

    @Autowired
    UtilisateurRepo utilisateurRepo;

    @Autowired
    ComposanteService composanteServcie;

    @Autowired
    PopulationService populationService;




     @RequestMapping(value = "/correspondant/addAdress/{id}", method = RequestMethod.GET)
    public String addAdresse(@ModelAttribute Adresse adresse) {
        return "adresseForm";}


    @RequestMapping(value = "/correspondant/addAdress/{idComposante}", method = RequestMethod.POST)
    public String saveAdresse(@ModelAttribute("adresse") @Valid Adresse adresse, BindingResult result, @PathVariable Long idComposante ) {

        if (result.hasErrors()) {
            return "adresseForm";
        }
        //  Adresse

        adresseRepo.save(adresse);
       Composante c = composanteRepo.getById(idComposante);
        c.getAdresses().add(adresse);
        // c.addAdresse(adresse);
        composanteRepo.save(c);
        System.out.println("adresses:  "+c.getAdresses());
        Long idComposanteOfAdress = composanteServcie.getIdComposanteWithAdress(adresse);
        return "redirect:/correspondant?idComposante="+idComposanteOfAdress;
}

    /*
    @RequestMapping(value = "/editAdress/{id}")
    public String showNewAdress(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("command", adresseRepo.findById(id).orElse(null));
        return "editAdress";
    }

    @RequestMapping(value = "/editAdress/{id}", method = RequestMethod.POST)
    public String editAdress(@PathVariable Long id, @ModelAttribute("contact") Adresse adresse) {
        Adresse newAdress = adresseRepo.findById(id).orElse(null);
        newAdress.setLigne(adresse.getLigne());
        adresseRepo.save(newAdress);
        return "redirect:/correspondant";
    }
*/

    @ModelAttribute("adresse")
    @RequestMapping(value = "/correspondant/editAdress", method = RequestMethod.GET)
    public ModelAndView editAdress( @RequestParam(value = "id") Long id) {
        // Formation formation = formationRepo.getById(id);
        Adresse adresse = adresseRepo.getById(id);
        return new ModelAndView("editAdress", "adresse", adresse);
    }

    @RequestMapping(value = "/correspondant/editAdress", method = RequestMethod.POST)
    public String editAdress( @ModelAttribute Adresse adresse, BindingResult result) {
        //  Adresse newAdress = adresseRepo.findById(id).orElse(null);
        //newAdress.setLigne(adresse.getLigne());
        if (result.hasErrors()) {
            return "editAdress";
        }
        adresseRepo.save(adresse);
        Long idComposanteOfAdress = composanteServcie.getIdComposanteWithAdress(adresse);
        return "redirect:/correspondant?idComposante="+idComposanteOfAdress;

    }


    @RequestMapping(value = "/deleteAdress/{id}")
    public String deleteAdress(@PathVariable Long id) {
        Adresse adresse = adresseRepo.getById(id);
        Long idComposanteOfAdress = composanteServcie.getIdComposanteWithAdress(adresse);

        adresseRepo.deleteById(id);
        return "redirect:/correspondant?idComposante="+idComposanteOfAdress;
    }

    @ModelAttribute("indiceRepetition")
    public Map<String, String> indiceRepetitionList() {
        return listBuilder.indiceRepetitionList();
    }
    @ModelAttribute("natureVoie")
    public Map<String, String> natureVoieList() {
        return listBuilder.natureVoieList();
    }
    @ModelAttribute("codePays")
    public Map<String, String> codePaysList() {
        return listBuilder.codePaysList();
    }
}
