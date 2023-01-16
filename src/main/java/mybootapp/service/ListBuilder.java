package mybootapp.service;

import mybootapp.model.Adresse;
import mybootapp.model.Composante;
import mybootapp.model.Formation;
import mybootapp.repo.ComposanteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Service
public class ListBuilder {

    @Autowired
    ComposanteRepo composanteRepo;

    @Value("#{'${ref.typeDeParcours}'.split('---')}")
    private ArrayList<String> typeDeParcours;

    @Value("#{'${ref.objectifGeneralDeFormation}'.split('---')}")
    private ArrayList<String> objectifGeneral;

    @Value("#{'${ref.modaliteDenseignement}'.split('---')}")
    private ArrayList<String> modaliteEnseignement;

    @Value("#{'${ref.rythmeFormation}'.split('---')}")
    private ArrayList<String> rythmeFormation;

    @Value("#{'${ref.langueFormation}'.split('---')}")
    private ArrayList<String> langueFormation;

    @Value("#{'${ref.modaliteAdmission}'.split('---')}")
    private ArrayList<String> modaliteAdmission;

    @Value("#{'${ref.tauxTVA}'.split('---')}")
    private ArrayList<String> tauxTVA;

    @Value("#{'${ref.fraisInclusANPEC}'.split('---')}")
    private ArrayList<String> fraisInclusANPEC;

    @Value("#{'${ref.niveauObligatoire}'.split('---')}")
    private ArrayList<String> niveauObligatoire;

    @Value("#{'${ref.fraisInclusANPEC}'.split('---')}")
    private ArrayList<String> modaliteEntreeSortie;

    @Value("#{'${ref.fraisInclusANPEC}'.split('---')}")
    private ArrayList<String> preRequis;

    @Value("#{'${ref.indiceRepetition}'.split('---')}")
    private ArrayList<String> indiceRepetition;

    @Value("#{'${ref.natureVoie}'.split('---')}")
    private ArrayList<String> natureVoieList;

    @Value("#{'${ref.codePays}'.split('---')}")
    private ArrayList<String> codePaysList;

    public Map<String, String> typeDeParcoursList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: typeDeParcours){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }

    public Map<String, String> objectifGeneralList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: objectifGeneral){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }

    public Map<String, String> modaliteEnseignementList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: modaliteEnseignement){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }

    public Map<String, String> rythmeFormationList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: rythmeFormation){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }
    public Map<String, String> langueFormationList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: langueFormation){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }
    public Map<String, String> modaliteAdmissionList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: modaliteAdmission){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }
    public Map<String, String> tauxTVAList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: tauxTVA){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }

    public Map<String, String> fraisInclusANPECList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: fraisInclusANPEC){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }

    public Map<String, String> modaliteEntreeSortieList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: modaliteEntreeSortie){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }
    public Map<String, String> preRequisList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: preRequis){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }
    public Map<String, String> niveauObligatoireList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: niveauObligatoire){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }

    public Map<String, String> indiceRepetitionList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: indiceRepetition){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }

    public Map<String, String> natureVoieList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: natureVoieList){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }
    public Map<String, String> codePaysList() {
        Map<String, String> list = new LinkedHashMap<>();
        for (String s: codePaysList){
            String [] res = s.split("--");
            list.put(res[0], res[1]);
        }
        return list;
    }

public Map<Adresse, String> ListAdressesOfComposante(Composante composante) {
        Map<Adresse, String> addressesList = new LinkedHashMap<>();
        Collection<Adresse> adresses = composante.getAdresses();
        for(Adresse a : adresses)
            addressesList.put(a, a.getLigne());
        return addressesList;
    }

    public Map<Composante, String> ListComposantes() {
        Map<Composante, String> compoList = new LinkedHashMap<>();
        List<Composante> composantes = composanteRepo.findAll();
        for(Composante c : composantes)
            compoList.put(c, c.getIntitule());
        return compoList;
    }
}
