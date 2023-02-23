package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.repository.SeminarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
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
                    s.setAuthors(seminar.getAuthors());
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
        return sr.findByAuthorsContaining(user);
    }

    public List<Seminar> findUpcomingSeminars(){
        List<Seminar> AllSeminars = sr.findAll();
        List<Seminar> upcomingSeminars = new ArrayList<>();
        Date currentDate=new Date();
        for(Seminar s : AllSeminars){
            int value = currentDate.compareTo(s.getDate());
            if(value > 0 || value==0){
                upcomingSeminars.add(s);
            }
        }
        return upcomingSeminars;

    }
}
