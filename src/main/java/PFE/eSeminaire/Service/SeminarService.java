package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.repository.SeminarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .map(t->{
                    seminar.setTitle(seminar.getTitle());
                    seminar.setContent(seminar.getContent());
                    seminar.setAuthor(seminar.getAuthor());
                    seminar.setDate(seminar.getDate());
                    seminar.setLocation(seminar.getLocation());
                    seminar.setOptionalContentLinks(seminar.getOptionalContentLinks());
                    return sr.save(t);
                }).orElseThrow(() -> new RuntimeException("seminar not found"));
    }

    public String delete(Long id) {
        sr.deleteById(id);
        return "Seminar deleted";
    }
}
