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

    }

}
