package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;
<<<<<<< HEAD
=======
import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
>>>>>>> 933d77e54008c6e236150b5d136bab8ebdbc4530
import PFE.eSeminaire.repository.SeminarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
<<<<<<< HEAD

public class SeminarService {

    @Autowired
    SeminarRepository SR;


    public Seminar save(Seminar seminar){
        return SR.save(seminar);

    }

    public List<Seminar> findAllSeminars(){
        return SR.findAll();

    }

    public Optional<Seminar> findById(Long id){
        var seminar = SR.findById(id);
        return seminar;

=======
public class SeminarService {
    @Autowired
    SeminarRepository sr;

    public Seminar save(Seminar seminar) {
        return sr.save(seminar);
    }

    public Optional<Seminar> get(Long id){
        return sr.findById(id);
    }

    public List<Seminar> getList() {
        return sr.findAll();
    }

    public Seminar update(Seminar seminar) {
        return sr.findById(seminar.getIdSeminar())
                .map(s->{
                    s.setTitle(seminar.getTitle());
                    s.setDescription(seminar.getDescription());
                    s.setAuthor(seminar.getAuthor());
                    s.setDate(seminar.getDate());
                    s.setLocation(seminar.getLocation());
                    s.setOptionalContentLinks(seminar.getOptionalContentLinks());
                    return sr.save(s);
                }).orElseThrow(() -> new RuntimeException("seminar not found"));
    }

    public String delete(Long id) {
        sr.deleteById(id);
        return "Seminar deleted";
    }

    public List<Seminar> getSeminarsOfTeam(Team team){
        return sr.findByTeam(team);
    }

    public List<Seminar> getSeminarsOfUser(User user){
        return sr.findByAuthor(user);
>>>>>>> 933d77e54008c6e236150b5d136bab8ebdbc4530
    }

}
