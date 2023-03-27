package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.repository.SeminarRepository;
import PFE.eSeminaire.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @Autowired
    SeminarService seminarService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    void testSave() {
        User user = new User();
        user.setFirstName("Test");
        user.setName("User");
        user.setMail("testuser@example.com");
        user.setPassword("testpassword");
        user = userService.save(user);
        Optional<User> savedUser = userService.findByMail(user.getMail());
        assertTrue(savedUser.isPresent());
        assertEquals("Test", savedUser.get().getFirstName());
        assertEquals("User", savedUser.get().getName());
        assertEquals("testuser@example.com", savedUser.get().getMail());
        assertTrue(passwordEncoder.matches("testpassword", savedUser.get().getPassword()));
    }

    @Test
    void testGet() {

        User user = new User();
        user.setFirstName("Test");
        user.setName("User");
        user.setMail("testuser@example.com");
        user.setPassword("testpassword");

        user = userService.save(user);

        Optional<User> retrievedUser = userService.get(user.getIdUser());

        assertTrue(retrievedUser.isPresent());
        assertEquals("Test", retrievedUser.get().getFirstName());
        assertEquals("User", retrievedUser.get().getName());
        assertEquals("testuser@example.com", retrievedUser.get().getMail());
        assertTrue(passwordEncoder.matches("testpassword", retrievedUser.get().getPassword()));
    }
    @Test
    void testGetList() {

        User user1 = new User();
        user1.setFirstName("Test1");
        user1.setName("User1");
        user1.setMail("testuser1@example.com");
        user1.setPassword("testpassword");

        User user2 = new User();
        user2.setFirstName("Test2");
        user2.setName("User2");
        user2.setMail("testuser2@example.com");
        user2.setPassword("testpassword");

        User user3 = new User();
        user3.setFirstName("Test3");
        user3.setName("User3");
        user3.setMail("testuser3@example.com");
        user3.setPassword("testpassword");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        UserRepository userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findAll()).thenReturn(userList);

        UserService userService = new UserService(userRepositoryMock);
        List<User> resultList = userService.getList();
        assertEquals(userList, resultList);
    }


    @Test
    public void testUpdateUser() {

        User user = new User();
        user.setName("John");
        user.setMail("john@example.com");
        user.setFirstName("Doe");
        user.setPassword("oldPassword");
        userService.save(user);

        user.setName("Jane");

        user.setPassword("newPassword");
        userService.update(user);

        User updatedUser = userService.findByMail(user.getMail()).orElse(null);

        assertNotNull(updatedUser);
        assertEquals("Jane", updatedUser.getName());
        //assertEquals("Doe", updatedUser.getFirstName());
        assertEquals("newPassword", updatedUser.getPassword());
    }


    @Test
    void testDeleteUser() {

        User user = new User();
        user.setFirstName("John");
        user.setName("Doe");
        user.setMail("john.doe@example.com");
        user.setPassword("password");
        user.setTeam(new Team());

        User savedUser = userService.save(user);

        String result = userService.delete(savedUser.getIdUser());

        assertEquals("User deleted", result);
        assertFalse(userService.get(savedUser.getIdUser()).isPresent());
    }


    @Test
    public void testFindByMail() {

        User user = new User();
        user.setFirstName("John");
        user.setName("Doe");
        user.setMail("john.doe@example.com");
        user.setPassword("password");
        userService.save(user);
        Optional<User> result = userService.findByMail("john.doe@example.com");

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }


    @Test
    public void testGetUsersOfTeam() {

        Team team = new Team();
        team.setName("team1");
        team.setMembers(new ArrayList<>());
        teamService.save(team);

        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setFirstName("Test1");
        user1.setName("User1");
        user1.setMail("testuser1@example.com");
        user1.setPassword("testpassword");
        user1.setTeam(team);
        team.getMembers().add(user1);
        users.add(user1);

        User user2 = new User();
        user2.setFirstName("Test2");
        user2.setName("User2");
        user2.setMail("testuser2@example.com");
        user2.setPassword("testpassword");
        user2.setTeam(team);
        team.getMembers().add(user2);
        users.add(user2);

        UserRepository fakeRepo = mock(UserRepository.class);
        Mockito.when(fakeRepo.findByTeam(team)).thenReturn(users);

        UserService userService = new UserService(fakeRepo);
        List<User> result = userService.getUsersOfTeam(team);

        assertEquals(2, result.size());

        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
    }

    @Test
    public void testGetUsersRelatedToSeminar() {

        Team team = new Team();
        team.setMembers(new ArrayList<>());
        teamService.save(team);

        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setFirstName("Test1");
        user1.setName("User1");
        user1.setMail("testuser1@example.com");
        user1.setPassword("testpassword");
        user1.setTeam(team);
        team.getMembers().add(user1);

        users.add(user1);

        User user2 = new User();
        user2.setFirstName("Test2");
        user2.setName("User2");
        user2.setMail("testuser2@example.com");
        user2.setPassword("testpassword");
        user2.setTeam(team);
        team.getMembers().add(user2);

        users.add(user2);

        Seminar seminar = new Seminar();
        seminar.setTitle("Test seminar");
        seminar.setDescription("This is a test seminar");
        seminar.setLocation("Lieu");
        seminar.setDescription("Description");
        seminar.setTeam(team);
        seminar.setDate(new Date());
        seminar.setOptionalContentLinks(Arrays.asList("lien1", "lien2"));
        List<User> authors = new ArrayList<>();
        authors.add(user1);
        seminar.setAuthors(authors);
        seminarService.save(seminar);

        UserRepository fakeRepo = mock(UserRepository.class);
        Mockito.when(fakeRepo.findByTeam(team)).thenReturn(users);

        UserService userService = new UserService(fakeRepo);
        List<User> result = userService.getUsersRelatedToSeminar(seminar);

        assertEquals(2, result.size());

        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
    }

    @Test
    public void testUserIsPresentByMail() {

        User user = new User();
        user.setFirstName("Test");
        user.setName("User");
        user.setMail("testuser@example.com");
        user.setPassword("testpassword");

        userService.save(user);
        boolean result = userService.userIsPresentByMail("testuser@example.com");

        assertTrue(result);
    }

}