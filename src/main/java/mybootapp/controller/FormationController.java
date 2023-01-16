package mybootapp.controller;


import java.security.Principal;

import java.text.Normalizer;
import java.util.*;


import mybootapp.model.*;
import mybootapp.repo.*;
import mybootapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequestMapping("/")
@Controller
public class FormationController {

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
    ComposanteService composanteService;

    @Autowired
    PopulationService populationService;

    @Autowired
    DateValidator validator;

    @Autowired
    FormationCreationValidator formationCreationValidator;

    @Value("${application.message:Hello World}")
    private String message;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home2(Principal p) {
        return new ModelAndView("home", "message", p);
    }


    @RequestMapping(value = "/formationList", method = RequestMethod.GET)
    public ModelAndView listFormations() {
        Collection<Composante> composantes = composanteRepo.findAll();
        return new ModelAndView("formation/formationList", "composante", composantes);
    }


    @ModelAttribute("formation")
    @RequestMapping(value = "/formationDetails/sessions", method = RequestMethod.GET)
    public ModelAndView printSessions(@RequestParam(value = "id") Long id){
        Formation formation = formationRepo.getById(id);
        return new ModelAndView("session/sessionList", "formation", formation);
    }

    @RequestMapping(value = "formationDetails/sessions/add/{id}", method = RequestMethod.GET)
    public String addSession(@ModelAttribute Session session) {
        return "session/sessionCreate";
    }

    @RequestMapping(value = "formationDetails/sessions/add/{idFormation}", method = RequestMethod.POST)
    public String addSession(@ModelAttribute @Valid Session session, BindingResult result, @PathVariable Long idFormation ) {
        validator.validate(session,result);
        if (result.hasErrors()) {
            return "session/sessionCreate";
        }
        Formation f = formationRepo.getById(idFormation);
        f.getAction().getSessions().add(session);
        formationRepo.save(f);
    //(userasright()formationid));
        return "session/sessionList";
    }

    @RequestMapping(value = "/formationDetails", method = RequestMethod.GET)
    public ModelAndView printFormation(@RequestParam(value = "id") Long id){
        Formation formation = formationRepo.getById(id);
        return new ModelAndView("formation/formationDetails", "formation", formation);
    }

    @ModelAttribute("formation")
    @RequestMapping(value = "formationDetails/edit", method = RequestMethod.GET)
    public ModelAndView editFormation( @RequestParam(value = "id") Long id){
        Formation formation = formationRepo.getById(id);
        ModelAndView modelAndView = new ModelAndView("formation/formationForm", "formation", formation);
        Composante c = composanteRepo.getById(composanteService.getIdComposanteWithFormation(formation));
        Map<Adresse, String> adresses = listBuilder.ListAdressesOfComposante(c);
        modelAndView.addObject("listAdresses", adresses);
        return modelAndView;
    }
    @ModelAttribute("typeDeParcours")
    public Map<String, String> typeDeParcoursList() {
        return listBuilder.typeDeParcoursList();
    }
    @ModelAttribute("objectifGeneral")
    public Map<String, String> objectifGeneralList() {
        return listBuilder.objectifGeneralList();
    }
    @ModelAttribute("modaliteEnseignement")
    public Map<String, String> modaliteEnseignementList() {
        return listBuilder.modaliteEnseignementList();
    }
    @ModelAttribute("rythmeFormation")
    public Map<String, String> rythmeFormationList() {
        return listBuilder.rythmeFormationList();
    }
    @ModelAttribute("modaliteAdmission")
    public Map<String, String> modaliteAdmissionList() {
        return listBuilder.modaliteAdmissionList();
    }
    @ModelAttribute("langueFormation")
    public Map<String, String> langueFormationList() {
        return listBuilder.langueFormationList();
    }
    @ModelAttribute("tauxTVA")
    public Map<String, String> tauxTVAList() {
        return listBuilder.tauxTVAList();
    }
    @ModelAttribute("fraisInclusANPEC")
    public Map<String, String> fraisInclusANPECList() {
        return listBuilder.fraisInclusANPECList();
    }
    @ModelAttribute("niveauObligatoire")
    public Map<String, String> niveauObligatoireList() {
        return listBuilder.niveauObligatoireList();
    }
    @ModelAttribute("modaliteEntreeSortie")
    public Map<String, String> modaliteEntreeSortieList() {
        return listBuilder.modaliteEntreeSortieList();
    }
    @ModelAttribute("preRequis")
    public Map<String, String> preRequisList() {
        return listBuilder.preRequisList();
    }

    @RequestMapping(value = "formationDetails/edit", method = RequestMethod.POST)
    public String saveFormation(@ModelAttribute("formation") @Valid Formation formation, BindingResult result) {
        if (result.hasErrors()) {
            return "formation/formationForm";
        }
        formation.setEtatEdition("stable");
        formationRepo.save(formation);
        return "redirect:/formationDetails?id="+formation.getId();
    }


    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        Collection<Composante> composantes = composanteRepo.findAll();
        return new ModelAndView("admin", "composante",composantes);
    }

    @RequestMapping(value = "/admin/formationCreate", method = RequestMethod.GET)
    public ModelAndView addFormationForm(@ModelAttribute Formation formation)

    {return new ModelAndView("formation/formationCreate","formation", formation);}

    @ModelAttribute("ListComposantes")
    public Map<Composante, String> ListComposantes() {
        return listBuilder.ListComposantes();
    }


    @RequestMapping(value = "/admin/formationCreate", method = RequestMethod.POST)
    public String saveFormationCreation(@ModelAttribute  Formation formation, BindingResult result) {
        formationCreationValidator.validate(formation, result);
        formation.finishCreation();
        if (result.hasErrors()) {
            return "formation/formationCreate";
        }
        formation.setEtatEdition("brouillon");
        formationRepo.save(formation);
        Composante c = formation.getComposante();
        c.addFormation(formation);
        composanteRepo.save(c);
        return "redirect:/formationList";
    }

    @PreAuthorize("@authenticationService.isAdmin()")
    @RequestMapping(value = "/formationList/deleteFormation/{id}")
    public String deleteFormation(@PathVariable Long id) {
        formationRepo.deleteById(id);
        return "redirect:/formationList";
    }


    /*
    @RequestMapping(value = "/correspondant/addAdress", method = RequestMethod.GET)
    public String addAdresse(@ModelAttribute Adresse adresse) {return "adresseForm";}

    @RequestMapping(value = "/correspondant/addAdress", method = RequestMethod.POST)
    public String addAdresse(@ModelAttribute("adresse") @Valid Adresse adresse, BindingResult result) {
        if (result.hasErrors()) {
            return "adresseForm";
        }
        adresseRepo.save(adresse);
        Composante c = composanteRepo.findAll().get(0);
        c.addAdresse(adresse);
        composanteRepo.save(c);
        System.out.println("adresses:  "+c.getAdresses());

        return "redirect:";
    }
*/


}
