package PFE.eSeminaire.repository;

import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.awt.desktop.OpenFilesEvent;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface
SeminarRepository extends JpaRepository<Seminar, Long> {
    List<Seminar> findByTeam(@Param("team") Team team);
    List<Seminar> findByAuthorsContaining(@Param("author") User user);
    Optional<Seminar> findByTitle(@Param("title") String title);


}
