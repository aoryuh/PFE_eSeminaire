package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.model.Team;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.repository.SeminarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class SeminarBuilderTest {

    @Autowired
    SeminarBuilder seminarBuilder;

    @Test
    public void testBuild_withValidFile_shouldReturnSeminar() throws IOException {

        String content = "NOM:\nTestTeam\nMAIL:\nauthor1@gmail.com\nauthor2@gmail.com\n\nDATE:\n2023-04-10 14:30\n\nLIEU:\nTestLocation\n\nTITRE:\nTestTitle\n\nRESUME:\nTestDescription\n\nLIENS:\nwww.testlink1.com\nwww.testlink2.com\n\n-- FIN --\n";
        MultipartFile multipartFile = new MockMultipartFile("file.txt", content.getBytes());

        Team team = new Team();
        team.setName("TestTeam");


        TeamService fakeRepo = mock(TeamService.class);
        Mockito.when(fakeRepo.teamIsPresentByName("TestTeam")).thenReturn(true);
        Mockito.when(fakeRepo.getByName("TestTeam")).thenReturn(team);

        User author1 = new User();
        author1.setMail("author1@gmail.com");
        User author2 = new User();
        author2.setMail("author2@gmail.com");

        UserService fakeRepo2 = mock(UserService.class);

        Mockito.when(fakeRepo2.userIsPresentByMail("author1@gmail.com")).thenReturn(true);
        Mockito.when(fakeRepo2.userIsPresentByMail("author2@gmail.com")).thenReturn(true);
        Mockito.when(fakeRepo2.findByMail("author1@gmail.com")).thenReturn(Optional.of(author1));
        Mockito.when(fakeRepo2.findByMail("author2@gmail.com")).thenReturn(Optional.of(author2));

        SeminarService fakeRepo3 = mock(SeminarService.class);

        when(fakeRepo3.SeminarIsPresentByTitle("TestTitle")).thenReturn(false);

        Seminar seminar = seminarBuilder.build(multipartFile);

        assertTrue(seminar.isOK());
        assertEquals("TestTitle", seminar.getTitle());
        assertEquals(team, seminar.getTeam());
        assertEquals("TestLocation", seminar.getLocation());
        assertEquals("TestDescription", seminar.getDescription());
        assertEquals(2, seminar.getAuthors().size());
        assertEquals("author1@gmail.com", seminar.getAuthors().get(0).getMail());
        assertEquals("author2@gmail.com", seminar.getAuthors().get(1).getMail());
        assertEquals(2, seminar.getOptionalContentLinks().size());
        assertEquals("www.testlink1.com", seminar.getOptionalContentLinks().get(0));
        assertEquals("www.testlink2.com", seminar.getOptionalContentLinks().get(1));
    }

}