package mybootapp.model;

import mybootapp.repo.ComposanteRepo;
import mybootapp.repo.FormationRepo;
import mybootapp.repo.UtilisateurRepo;
import mybootapp.service.UtilisateurService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class DataTest {



    @Autowired
    FormationRepo formationRepo;

    @Autowired
    ComposanteRepo composanteRepo;

    @Autowired
    UtilisateurRepo utilisateurRepo;

    @Autowired
    UtilisateurService utilisateurService;



    @Test
    public void formationGenerator(){
        assertEquals(4, formationRepo.findAll().size());
    }

    @Test
    public void composanteGenerator(){
        assertEquals(2, composanteRepo.findAll().size());
    }


    @Test
    public void adresseComposante(){
        Adresse adresse = new Adresse();
        String ligne = "163 Av. de Luminy, 13009 Marseille";
        adresse.setLigne(ligne);
       Collection<Adresse> compoAdresse = composanteRepo.findAll().get(0).getAdresses();
        assertTrue(compoAdresse.contains(adresse));
    }

    @Test
    public void UtilisateurComposante(){
        Utilisateur u = new Utilisateur();
        u.setPrenom("test");
        Composante c = composanteRepo.findAll().get(0);
        c.setCorrespondant(u);
        System.out.println("dvdddddddddddddddddddddddddddddddddddd"+c.getCorrespondant().getPrenom());
        assertTrue(c.getCorrespondant().getPrenom().equals("test"));

    }

    @Test
    public void utilisateurComposante() {
        Composante c = new Composante();
        c.setIntitule("test");
        Utilisateur u = new Utilisateur();
        u.setNom("utilisateur test");
        c.setCorrespondant(u);
        u.setIdComposante(c);
        assertTrue(u.getIdComposante().getIntitule().equals("test"));
    }


    @Test
    public void getByidCAS(){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdCAS("o18025131");
        Utilisateur utilisateur1 = utilisateurService.getByidCAS("o18025131");
        assertTrue(utilisateur1.getIdCAS().equals(utilisateur.getIdCAS()));

    }
}
