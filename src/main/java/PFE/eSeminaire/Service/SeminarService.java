package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.repository.SeminarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Seminar> groupByKeyword(String motCle) {
        List<Seminar> liste = getList();
        List<Seminar> resultats = new ArrayList<Seminar>();
        for (Seminar seminar : liste) {
            String motsCles = (seminar.getTitle() + " " + seminar.getAuthors().toString()
                    + " " + seminar.getDate()).toLowerCase()
                    + " " + seminar.getLocation()
                    + " " + seminar.getTeam()
                    + " " + seminar.getDescription();

            for (String link : seminar.getOptionalContentLinks()) {
                motsCles = motsCles + " " + link;
            }

            if (motsCles.contains(motCle.toLowerCase())) {
                resultats.add(seminar);
            }
        }
        return resultats;
    }

    public List<Seminar> searchByKeyword (String motCle){
        List<Seminar> resultats = groupByKeyword(motCle);
        Collections.sort(resultats, new Comparator<Seminar>() {
            @Override
            public int compare(Seminar s1, Seminar s2) {
                String motsCles1 = (s1.getTitle() + " " + s1.getAuthors().toString()
                        + " " + s1.getDate()).toLowerCase()
                        + " " + s1.getLocation()
                        + " " + s1.getTeam()
                        + " " + s1.getDescription();

                for (String link : s1.getOptionalContentLinks()) {
                    motsCles1 = motsCles1 + " " + link;
                }
                String motsCles2 = (s2.getTitle() + " " + s2.getAuthors().toString()
                        + " " + s2.getDate()).toLowerCase()
                        + " " + s2.getLocation()
                        + " " + s2.getTeam()
                        + " " + s2.getDescription();

                for (String link : s2.getOptionalContentLinks()) {
                    motsCles2 = motsCles2 + " " + link;
                }
                if (motsCles1.indexOf(motCle.toLowerCase()) < motsCles2.indexOf(motCle.toLowerCase())) {
                    return -1;
                } else if (motsCles1.indexOf(motCle.toLowerCase()) > motsCles2.indexOf(motCle.toLowerCase())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        System.out.println("result" + resultats.size() + resultats.get(0).getTitle());
        return resultats;
    }

}
