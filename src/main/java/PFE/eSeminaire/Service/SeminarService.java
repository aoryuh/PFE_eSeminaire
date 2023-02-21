package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;

import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;

import PFE.eSeminaire.repository.SeminarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service


public class SeminarService  {

    @Autowired
    SeminarRepository SR;


    public Seminar save(Seminar seminar){
        return SR.save(seminar);

    }

    public List<Seminar> findAllSeminars(){
        return SR.findAll();

    }

    public List<Seminar> findUpcomingSeminars(){
        List<Seminar> AllSeminars = SR.findAll();
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
        List<Seminar> liste = findAllSeminars();
        List<Seminar> resultats = new ArrayList<Seminar>();
        for (Seminar seminar : liste) {
            String motsCles = (seminar.getTitle() + " " + seminar.getAuthor()
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
                String motsCles1 = (s1.getTitle() + " " + s1.getAuthor()
                        + " " + s1.getDate()).toLowerCase()
                        + " " + s1.getLocation()
                        + " " + s1.getTeam()
                        + " " + s1.getDescription();

                for (String link : s1.getOptionalContentLinks()) {
                    motsCles1 = motsCles1 + " " + link;
                }
                String motsCles2 = (s2.getTitle() + " " + s2.getAuthor()
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
        return resultats;
        }


    public Optional<Seminar> findById(Long id) {
        var seminar = SR.findById(id);
        return seminar;

    }

    public Seminar update(Long id, Seminar updatedSeminar) {
        return SR.findById(id)
                .map(seminar -> {
                    seminar.setTitle(updatedSeminar.getTitle());
                    seminar.setDescription(updatedSeminar.getDescription());
                    seminar.setDate(updatedSeminar.getDate());
                    seminar.setLocation(updatedSeminar.getLocation());
                    seminar.setAuthor(updatedSeminar.getAuthor());
                    seminar.setOptionalContentLinks(updatedSeminar.getOptionalContentLinks());
                    return SR.save(seminar);
                })
                .orElseThrow(() -> new RuntimeException("Seminar not found"));
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
