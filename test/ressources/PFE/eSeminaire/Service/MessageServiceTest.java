package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Message;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.repository.MessageRepository;
import PFE.eSeminaire.repository.SeminarRepository;
import PFE.eSeminaire.repository.UserRepository;
import PFE.eSeminaire.security.MyUserPrincipal;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
@Transactional
class MessageServiceTest {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Test
    void testSave() {

        Message message = new Message();
        message.setContent("Test message content");
        message.setDate(new Date());
        message.setUser(new User());

        message = messageService.save(message);

        Optional<Message> savedMessage = Optional.ofNullable(messageService.findById(message.getId()));
        assertTrue(savedMessage.isPresent());
        assertEquals("Test message content", savedMessage.get().getContent());
    }


    @Test
    void testFindAllMessages() {

        Message message1 = new Message();
        message1.setContent("Test message 1");
        message1.setDate(new Date());
        message1.setSubject("Subject");
        message1 = messageService.save(message1);

        Message message2 = new Message();
        message2.setContent("Test message 2");
        message2.setDate(new Date());
        message2.setSubject("Subject");
        message2 = messageService.save(message2);

        List<Message> messages = messageService.findAllMessages();

        assertTrue(messages.contains(message1));
        assertTrue(messages.contains(message2));
    }


    @Test
    void findById() {
        Message message = new Message();
        User user = new User();
        user.setPassword("aaa");
        user.setMail("azert@azert.fr");
        user.setName("azert");
        user.setFirstName("azert");
        userService.save(user);
        message.setUser(user);
        message.setDate(new Date());
        message.setContent("Bonjour");
        message.setSubject("AI");
        messageService.save(message);
        Message expected = messageService.findById(message.getId());
        assertNotNull(expected);
        assertEquals(message, expected);

    }

    @Test
    void testDelete() {

        Message message = new Message();
        message.setContent("Test message content");
        message.setDate(new Date());
        message.setUser(new User());
        message = messageService.save(message);

        messageService.delete(message.getId());

        Optional<Message> deletedMessage = Optional.ofNullable(messageService.findById(message.getId()));

        assertFalse(deletedMessage.isPresent());
    }


    @Test
    public void testAdd() {

        List<User> users = new ArrayList<>();
        User testUser = new User();
        testUser.setFirstName("HALLAI");
        testUser.setName("Katia");
        testUser.setMail("mailRespo");
        testUser.setPassword("aaa");
        users.add(testUser);

        UserRepository fakeRepo2 = mock(UserRepository.class);
        Mockito.when(fakeRepo2.findAll()).thenReturn(users);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                new MyUserPrincipal(testUser), null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Message message = new Message();
        message.setContent("Test message");
        messageService.add(message);
        List<Message> messages = new ArrayList<>();
        messages.add(message);

        MessageRepository fakeRepo1 = mock(MessageRepository.class);
        Mockito.when(fakeRepo1.findAll()).thenReturn(messages);

        MessageService messageService = new MessageService(fakeRepo1, fakeRepo2);

        assertTrue(messageService.findAllMessages().contains(message));

        assertEquals(testUser.getMail(), message.getUser().getMail());

    }

    @Test
    void testIsMessageHolder() {

        List<User> users = new ArrayList<>();

        User testUser = new User();
        testUser.setFirstName("HALLAI");
        testUser.setName("Katia");
        testUser.setMail("katia.hallai@gmail.com");
        testUser.setPassword("aaa");
        users.add(testUser);

        User otherUser = new User();
        otherUser.setFirstName("COQUARD");
        otherUser.setName("Damien");
        otherUser.setMail("damien.coquard@gmail.com");
        otherUser.setPassword("aaa");

        users.add(otherUser);

        UserRepository fakeRepo2 = mock(UserRepository.class);
        Mockito.when(fakeRepo2.findAll()).thenReturn(users);

        List<Message> messages = new ArrayList<>();

        Message message1 = new Message();
        message1.setContent("Contenu du message 1");
        message1.setUser(testUser);
        messages.add(message1);

        Message message2 = new Message();
        message2.setContent("Contenu du message 2");
        message2.setUser(otherUser);
        messages.add(message2);

        MessageRepository fakeRepo1 = mock(MessageRepository.class);
        Mockito.when(fakeRepo1.findAll()).thenReturn(messages);

        MessageService messageService = new MessageService(fakeRepo1, fakeRepo2);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                new MyUserPrincipal(testUser), null);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        assertTrue(messageService.isMessageHolder(message1));

        assertFalse(messageService.isMessageHolder(message2));

        Authentication otherAuthentication = new UsernamePasswordAuthenticationToken(
                new MyUserPrincipal(otherUser), null);
        SecurityContextHolder.getContext().setAuthentication(otherAuthentication);

        assertFalse(messageService.isMessageHolder(message1));

        assertTrue(messageService.isMessageHolder(message2));

        SecurityContextHolder.getContext().setAuthentication(null);

        assertFalse(messageService.isMessageHolder(message1));
        assertFalse(messageService.isMessageHolder(message2));
    }


}