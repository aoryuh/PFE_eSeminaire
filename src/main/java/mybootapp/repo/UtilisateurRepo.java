package mybootapp.repo;

import mybootapp.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepo extends JpaRepository<Utilisateur,Long> {

    public List<Utilisateur> getByIdCAS(String idCAS);
}
