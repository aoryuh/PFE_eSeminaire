package PFE.eSeminaire.repository;

import PFE.eSeminaire.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByName(@Param("name") String Name);
}
