package mybootapp.controller;

import mybootapp.model.Composante;
import mybootapp.repo.ComposanteRepo;
import mybootapp.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
@Controller
public class ComposanteController {

    @Autowired
    ComposanteRepo composanteRepo;
    @Autowired
    UtilisateurService utilisateurService;


    @PreAuthorize("@authenticationService.isCorrepondant(#idComposante)")
    @ModelAttribute("composante")
    @RequestMapping(value = "/correspondant", method = RequestMethod.GET)
    public ModelAndView correspondantPage(@RequestParam(value = "idComposante") Long idComposante) {
        // Collection<Composante> composantes = composanteRepo.findAll();
        Composante composantes = composanteRepo.getById(idComposante);

        return new ModelAndView("correspondant", "composante",composantes);
    }



    @RequestMapping(value = "/admin/createComposante", method = RequestMethod.GET)
    public String addComposante(@ModelAttribute Composante composante) {
        return "composante/composanteForm";}




    @RequestMapping(value = "/admin/createComposante", method = RequestMethod.POST)
    public String saveComposante(@ModelAttribute("composante") @Valid Composante composante  , BindingResult result ) {

        if (result.hasErrors()) {
            return "composante/composanteForm";
        }
        //  Adresse
        composanteRepo.save(composante);

        return "redirect:/";
    }



}
