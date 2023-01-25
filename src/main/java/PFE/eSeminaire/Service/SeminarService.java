package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;

import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;

import PFE.eSeminaire.repository.SeminarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service


public class SeminarService {

    @Autowired
    SeminarRepository SR;


    public Seminar save(Seminar seminar){
        return SR.save(seminar);

    }

    public List<Seminar> findAllSeminars(){
        return SR.findAll();

    }

    public Optional<Seminar> findById(Long id) {
        var seminar = SR.findById(id);
        return seminar;

    }

    public Seminar update(Seminar seminar) {
        return SR.findById(seminar.getIdSeminar())
                .map(s->{
                    s.setTitle(seminar.getTitle());
                    s.setDescription(seminar.getDescription());
                    s.setAuthor(seminar.getAuthor());
                    s.setDate(seminar.getDate());
                    s.setLocation(seminar.getLocation());
                    s.setOptionalContentLinks(seminar.getOptionalContentLinks());
                    return SR.save(s);
                }).orElseThrow(() -> new RuntimeException("seminar not found"));
    }

    public void delete(Long id) {
        SR.deleteById(id);

    }

    public List<Seminar> getSeminarsOfTeam(Team team){
        return SR.findByTeam(team);
    }

    public List<Seminar> getSeminarsOfUser(User user){
        return SR.findByAuthor(user);

    }

}
