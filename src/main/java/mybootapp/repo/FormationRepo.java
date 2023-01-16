package mybootapp.repo;

import mybootapp.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormationRepo extends JpaRepository<Formation, Long> {
}
