package mybootapp.service;

import mybootapp.model.*;
import mybootapp.repo.AdresseRepo;
import mybootapp.repo.ComposanteRepo;
import mybootapp.repo.FormationRepo;
import mybootapp.repo.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
public class PopulationService {

    @Autowired
    FormationRepo formationRepo;

    @Autowired
    ComposanteRepo composanteRepo;

    @Autowired
    AdresseRepo adresseRepo;

    @Autowired
    UtilisateurRepo utilisateurRepo;


    @PostConstruct
    public void init(){
        ArrayList<String> formationTypes = new ArrayList<>();
        formationTypes.add("DUT");
        formationTypes.add("Licence");
        formationTypes.add("Master");
        Formation f;
        Session s = new Session();


        Composante composante = new Composante();
        Utilisateur u = new Utilisateur();
        u.setNom("Coquard");
        u.setPrenom("Damien");
        u.setIdCAS("c18024224");
        u.setAdmin(false);
        u.setIdComposante(composante);
        utilisateurRepo.save(u);
        composante.setIntitule("Faculté des Sciences");
        composanteRepo.save(composante);
        for (String formation: formationTypes) {
            f = new Formation();
            f.init(composante.getIntitule(), formation);
            s.setSessionOuverte("1");
            s.setDebut(new Date());
            s.setFin(new Date());
            f.getAction().getSessions().add(s);
            formationRepo.save(f);
            composante.addFormation(f);
            composanteRepo.save(composante);
        }
        Adresse a = new Adresse();
        a.setLigne("163 Av. de Luminy, 13009 Marseille");
        adresseRepo.save(a);
        composante.addAdresse(a);
        composanteRepo.save(composante);
        System.out.println(composante.getId());

        composante = new Composante();
        u = new Utilisateur();
        u.setNom("Ould-Chibani");
        u.setPrenom("Abdessatar");
        u.setAdmin(true);
        u.setIdComposante(composante);
        u.setIdCAS("o18025131");
        utilisateurRepo.save(u);
        composante.setIntitule("Faculté des Arts");
        composanteRepo.save(composante);
        for (String formation: formationTypes) {
            f = new Formation();
            f.init(composante.getIntitule(), formation);
            s = new Session();
            s.setSessionOuverte("1");
            s.setDebut(new Date());
            s.setFin(new Date());
            f.getAction().getSessions().add(s);
            formationRepo.save(f);
            composante.addFormation(f);
            composanteRepo.save(composante);
        }
        a = new Adresse();
        a.setLigne("163 Av. de Luminy, 13009 Marseille");
        adresseRepo.save(a);
        composante.addAdresse(a);
        composanteRepo.save(composante);
        System.out.println(composante.getId());

        composante = new Composante();
        u = new Utilisateur();
        u.setNom("Hallai");
        u.setPrenom("Katia");
        u.setAdmin(true);
        u.setIdComposante(composante);
        u.setIdCAS("h17030347");
        utilisateurRepo.save(u);
        composante.setIntitule("Faculté de Droits");
        composanteRepo.save(composante);
        for (String formation: formationTypes) {
            f = new Formation();
            f.init(composante.getIntitule(), formation);
            s = new Session();
            s.setSessionOuverte("1");
            s.setDebut(new Date());
            s.setFin(new Date());
            f.getAction().getSessions().add(s);
            formationRepo.save(f);
            composante.addFormation(f);
            composanteRepo.save(composante);
        }
        a = new Adresse();
        a.setLigne("163 Av. de Luminy, 13009 Marseille");
        adresseRepo.save(a);
        composante.addAdresse(a);
        composanteRepo.save(composante);
        System.out.println(composante.getId());

        composante = new Composante();
        u = new Utilisateur();
        u.setNom("Ngo");
        u.setPrenom("François");
        u.setAdmin(true);
        u.setIdComposante(composante);
        u.setIdCAS("n17009276");
        utilisateurRepo.save(u);
        composante.setIntitule("OSU Pythéas");
        composanteRepo.save(composante);
        for (String formation: formationTypes) {
            f = new Formation();
            f.init(composante.getIntitule(), formation);
            s = new Session();
            s.setSessionOuverte("1");
            s.setDebut(new Date());
            s.setFin(new Date());
            f.getAction().getSessions().add(s);
            formationRepo.save(f);
            composante.addFormation(f);
            composanteRepo.save(composante);
        }
        a = new Adresse();
        a.setLigne("163 Av. de Luminy, 13009 Marseille");
        adresseRepo.save(a);
        composante.addAdresse(a);
        composanteRepo.save(composante);
        System.out.println(composante.getId());



        /*for(int i = 0; i < 2; i++){
            Composante c = new Composante();
            c.setIntitule("composante".concat(Integer.toString(i)));
            c.setFormations(new ArrayList<>());
            for(int j = 0; j < 2; j++){
                Formation f = new Formation();
                f.init(2 * i + j);
                Session s = new Session();
                s.setSessionOuverte(1);
                s.setDebut(new Date());
                s.setFin(new Date());
                f.getAction().getSessions().add(s);
                composanteRepo.save(c);

               //f.setComposante(c);
                formationRepo.save(f);
                c.addFormation(f);
            }
            Collection<Adresse> adresses = new ArrayList<>();
            Adresse a = new Adresse();
            c.setAdresses(adresses);
            a.setLigne("163 Av. de Luminy, 13009 Marseille");
            adresseRepo.save(a);
            c.addAdresse(a);
            composanteRepo.save(c);
        }
        Composante c = new Composante();

        c.setIntitule("composante");
        c.setFormations(new ArrayList<>());

        Utilisateur utilisateurs = new Utilisateur();

        utilisateurs.setNom("Coquard");
        utilisateurs.setPrenom("Damien");
        utilisateurs.setAdmin(true);
        utilisateurs.setIdComposante(c);
        utilisateurs.setIdCAS("o18025131");

        utilisateurRepo.save(utilisateurs);

        composanteRepo.save(c);*/
    }
}
