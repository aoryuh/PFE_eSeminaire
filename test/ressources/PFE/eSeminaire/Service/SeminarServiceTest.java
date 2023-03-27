package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Message;
import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.repository.SeminarRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
@Transactional
class SeminarServiceTest {

    @Autowired
    SeminarService seminarService;

    @Autowired
    TeamService teamService;

    @Test
    public void testSaveSeminar() {

        Seminar seminar = new Seminar();
        seminar.setTitle("Title");
        seminar.setDescription("Description");
        seminar.setLocation("Location");
        seminar.setDate(new Date());

        seminar.setAuthors(new ArrayList<>());
        seminar.setOptionalContentLinks(new ArrayList<>());


        assertNotNull(seminarService.save(seminar));
        assertNotNull(seminarService.save(seminar).getIdSeminar());

        assertEquals(seminar.getTitle(), seminarService.save(seminar).getTitle());
        assertEquals(seminar.getDescription(), seminarService.save(seminar).getDescription());
        assertEquals(seminar.getDate(), seminarService.save(seminar).getDate());
        assertEquals(seminar.getLocation(), seminarService.save(seminar).getLocation());

    }


    @Test
    public void testGet() {

        Long seminarId = 1L;
        Seminar seminar = new Seminar();
        seminar.setIdSeminar(seminarId);

        SeminarRepository fakeRepo = mock(SeminarRepository.class);
        Mockito.when(fakeRepo.findById(seminarId)).thenReturn(Optional.of(seminar));

        SeminarService seminarService = new SeminarService(fakeRepo);

        Optional<Seminar> seminarOptional = seminarService.get(seminarId);

        assertTrue(seminarOptional.isPresent());
        assertEquals(seminar, seminarOptional.get());
    }


    @Test
    public void testGetList() {

        Seminar seminar1 = new Seminar();
        Seminar seminar2 = new Seminar();
        List<Seminar> seminars = Arrays.asList(seminar1, seminar2);


        SeminarRepository fakeRepo = mock(SeminarRepository.class);
        Mockito.when(fakeRepo.findAll()).thenReturn(seminars);

        SeminarService seminarService = new SeminarService(fakeRepo);

        List<Seminar> seminarList = seminarService.getList();

        assertEquals(seminars, seminarList);
    }


    @Test
    public void testUpdate() {

        Seminar seminarToUpdate = new Seminar();
        seminarToUpdate.setTitle("Nouveau titre");
        seminarToUpdate.setDescription("Nouvelle description");
        seminarToUpdate.setAuthors(new ArrayList<>());
        seminarToUpdate.setDate(new Date());
        seminarToUpdate.setLocation("Nouvel emplacement");
        seminarToUpdate.setOptionalContentLinks(new ArrayList<>());
        seminarService.save(seminarToUpdate);

        Seminar updatedSeminar = seminarService.update(seminarToUpdate);

        assertEquals("Nouveau titre", updatedSeminar.getTitle());
        assertEquals("Nouvelle description", updatedSeminar.getDescription());
        assertNotNull(updatedSeminar.getDate());
        assertEquals("Nouvel emplacement", updatedSeminar.getLocation());

    }


    @Test
    void testDelete() {

        Seminar seminar = new Seminar();
        seminarService.save(seminar);
        assertNotNull(seminarService.get(seminar.getIdSeminar()));
        assertEquals(seminarService.delete(seminar.getIdSeminar()),"Seminar deleted");
    }

    @Test
    public void testGetSeminarsOfTeam() {

        Team team = new Team();
        team.setName("team1");
        teamService.save(team);
        Team team2 = new Team();
        team.setName("team2");
        teamService.save(team2);

        Seminar seminar2 = new Seminar();
        Seminar seminar1 = new Seminar();

        seminar1.setTeam(team);
        seminar2.setTeam(team2);
        seminarService.save(seminar1);
        seminarService.save(seminar2);

        List<Seminar> seminarList = new ArrayList<>();
        seminarList.add(seminar1);
        seminarList.add(seminar2);

        SeminarRepository fakeRepo = mock(SeminarRepository.class);
        Mockito.when(fakeRepo.findByTeam(team)).thenReturn(seminarList);

        SeminarService seminarService = new SeminarService(fakeRepo);


        if (seminarService.SR == null) {
            throw new IllegalStateException("Le repository n'est pas disponible");
        }

        assertEquals(seminarList, seminarService.getSeminarsOfTeam(team));
    }

    @Test
    public void testGetSeminarsOfUser() {

        String userName = "Katia";
        User user = new User();
        user.setName(userName);

        String seminarTitle = "Séminaire 1";
        Seminar seminar = new Seminar();
        seminar.setTitle(seminarTitle);
        List<User> authors = new ArrayList<>();
        authors.add(user);
        seminar.setAuthors(authors);

        SeminarRepository fakeRepo = mock(SeminarRepository.class);
        Mockito.when(fakeRepo.findByAuthorsContaining(user)).thenReturn(Arrays.asList(seminar));

        SeminarService seminarService = new SeminarService(fakeRepo);

        List<Seminar> seminarsOfUser = seminarService.getSeminarsOfUser(user);

        assertEquals(Arrays.asList(seminar), seminarsOfUser);
    }



    @Test
    public void testFindUpcomingSeminars() {

        Seminar upcomingSeminar = new Seminar( ); // date = demain
        upcomingSeminar.setDate(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)));
        Seminar pastSeminar = new Seminar( ); // date = hier
        pastSeminar.setDate(new Date(System.currentTimeMillis() - (24 * 60 * 60 * 1000)));

        SeminarRepository fakeRepo = mock(SeminarRepository.class);
        Mockito.when(fakeRepo.findAll()).thenReturn(Arrays.asList(upcomingSeminar, pastSeminar));

        SeminarService seminarService = new SeminarService(fakeRepo);

        List<Seminar> upcomingSeminars = seminarService.findUpcomingSeminars();

        assertEquals(1, upcomingSeminars.size());
        assertTrue(upcomingSeminars.contains(upcomingSeminar));

    }

    @Test
    public void testGroupByKeyword() {

        SeminarRepository mockRepo = mock(SeminarRepository.class);
        List<Seminar> mockSeminars = new ArrayList<>();

        Seminar seminar1 = new Seminar();
        seminar1.setTitle("Titre 1");
        seminar1.setLocation("Lieu 1");
        seminar1.setDescription("Description 1");
        seminar1.setTeam(new Team());
        seminar1.setDate(new Date());
        seminar1.setOptionalContentLinks(Arrays.asList("lien1", "lien2"));
        User user1 = new User();
        user1.setFirstName("Katia");
        user1.setName("HALLAI");
        User user2 = new User();
        user2.setFirstName("Damien");
        user2.setName("COQUARD");
        seminar1.setAuthors(Arrays.asList(user1,user2));
        mockSeminars.add(seminar1);

        Seminar seminar2 = new Seminar();
        seminar2.setTitle("Titre 2");
        seminar2.setLocation("Lieu 2");
        seminar2.setDescription("Description 2");
        seminar2.setTeam(new Team());
        seminar2.setDate(new Date());
        seminar2.setOptionalContentLinks(Arrays.asList("lien3", "lien4"));
        User user3= new User();
        user3.setFirstName("Lysa");
        user3.setName("SELLAH");
        User user4= new User();
        user4.setName("CURIE");
        user4.setFirstName("Marie");
        seminar2.setAuthors(Arrays.asList(user3, user4));
        mockSeminars.add(seminar2);

        Seminar seminar3 = new Seminar();
        seminar3.setTitle("Titre 3");
        seminar3.setLocation("Lieu 3");
        seminar3.setDescription("Description 3");
        seminar3.setTeam(new Team());
        seminar3.setDate(new Date());
        seminar3.setOptionalContentLinks(Arrays.asList("lien5", "lien6"));
        seminar3.setAuthors(Arrays.asList(user1, user4));
        mockSeminars.add(seminar3);

        Mockito.when(mockRepo.findAll()).thenReturn(mockSeminars);

        SeminarService seminarService = new SeminarService(mockRepo);

        List<Seminar> result = seminarService.groupByKeyword("Katia");
        assertEquals(2, result.size());
        assertEquals("Titre 1", result.get(0).getTitle());

        result = seminarService.groupByKeyword("inexistant");
        assertEquals(0, result.size());
    }

    @Test
    public void testSearchByKeyword() {

        SeminarRepository fakeRepo = mock(SeminarRepository.class);
        List<Seminar> mockSeminars = new ArrayList<>();


        Seminar s1 = new Seminar( );
        s1.setTitle("Séminaire sur la biologie marine");
        s1.setDescription("Description du séminaire");
        s1.setDate(new Date());
        s1.setLocation("Paris");
        s1.setTeam(new Team());
        s1.setAuthors(new ArrayList<>());
        s1.setOptionalContentLinks(new ArrayList<>());
        mockSeminars.add(s1);

        Seminar s2 = new Seminar();
        s2.setTitle("Séminaire sur l'informatique");
        s2.setDescription("Description du séminaire");
        s2.setDate(new Date());
        s2.setLocation("Lyon");
        s2.setTeam(new Team());
        s2.setAuthors(new ArrayList<>());
        s2.setOptionalContentLinks(new ArrayList<>());
        mockSeminars.add(s2);

        Seminar s3 = new Seminar();
        s3.setTitle("Séminaire sur la femme et l'informatique");
        s3.setDescription("Description du séminaire");
        s3.setDate(new Date());
        s3.setLocation("Marseille");
        s3.setTeam(new Team());
        s3.setAuthors(new ArrayList<>());
        s3.setOptionalContentLinks(new ArrayList<>());
        mockSeminars.add(s3);


        Mockito.when(fakeRepo.findAll()).thenReturn(mockSeminars);

        SeminarService seminarService = new SeminarService(fakeRepo);
        List<Seminar> resultats = seminarService.searchByKeyword("informatique");

        assertEquals(Arrays.asList(s2, s3), resultats);

        resultats = seminarService.searchByKeyword("séminaire");

        assertEquals(Arrays.asList(s1, s2, s3), resultats);

        resultats = seminarService.searchByKeyword("astronomie");

        assertEquals(Collections.emptyList(), resultats);
    }

    @Test
    public void testGetByTitle() {

        String seminarTitle = "Séminaire 1";
        Seminar seminar = new Seminar();
        seminar.setTitle(seminarTitle);

        SeminarRepository fakeRepo = mock(SeminarRepository.class);
        Mockito.when(fakeRepo.findByTitle(seminarTitle)).thenReturn(Optional.of(seminar));

        SeminarService seminarService = new SeminarService(fakeRepo);

        Seminar resultSeminar = seminarService.getByTitle(seminarTitle);

        assertEquals(seminar, resultSeminar);
    }


    @Test
    public void testSeminarIsPresentByTitle() {

        String seminarTitle = "Séminaire 1";

        Seminar seminar = new Seminar();
        seminar.setTitle(seminarTitle);


        SeminarRepository fakeRepo = mock(SeminarRepository.class);
        Mockito.when(fakeRepo.findByTitle(seminarTitle)).thenReturn(Optional.of(seminar));

        SeminarService seminarService = new SeminarService(fakeRepo);

        boolean seminarIsPresent = seminarService.SeminarIsPresentByTitle(seminarTitle);

        assertTrue(seminarIsPresent);
    }

}