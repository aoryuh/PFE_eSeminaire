package PFE.eSeminaire.web;

import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.repository.SeminarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SeminarTest {

    @Autowired
    SeminarRepository SR ;

/*
    @Test
    void testGetSeminars() {
        Seminar seminar = new Seminar();
        seminar.setTitle("La femme et l'informatique");
        this.SR.save(seminar);

        List<Seminar> expected = SR.findAll();

        assertTrue(expected.size()!=0);
    }

 */
}