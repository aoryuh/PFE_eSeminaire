package mybootapp.repo;

import mybootapp.model.Adresse;
import mybootapp.model.Composante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComposanteRepo extends JpaRepository<Composante, Long> {
}
