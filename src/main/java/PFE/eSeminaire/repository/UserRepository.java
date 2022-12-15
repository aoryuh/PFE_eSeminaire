package PFE.eSeminaire.repository;

import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(@Param("name") String name);
    List<User> findByNameLike(@Param("name") String name);
    List<User> findByFirstName(@Param("firstName") String firstName);
    List<User> findByFirstNameLike(@Param("firstName") String firstName);
    List<User> findByTeam(@Param("team") Team team);
}
