package mybootapp.service;

import mybootapp.model.Adresse;
import mybootapp.model.Composante;
import mybootapp.model.Formation;
import mybootapp.repo.ComposanteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComposanteService {

    @Autowired
    ComposanteRepo composanteRepo;

    public Long getIdComposanteWithAdress(Adresse adresse){
        List<Composante> composantes = composanteRepo.findAll();
        for(Composante composante: composantes){
            if(composante.getAdresses().contains(adresse)){
                return composante.getId();
            }
        }
        return null;
    }

    public Long getIdComposanteWithFormation(Formation formation) {
        List<Composante> composantes = composanteRepo.findAll();
        for (Composante composante : composantes) {
            if (composante.getFormations().contains(formation)) {
                return composante.getId();
            }
        }
        return null;
    }
}
