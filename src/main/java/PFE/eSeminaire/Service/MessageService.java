package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Message;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.repository.MessageRepository;
import PFE.eSeminaire.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    MessageRepository MR;

    @Autowired
    UserService US;

    public Message save(Message message){
        return MR.save(message);

    }

    public List<Message> findAllMessages(){
        return MR.findAll();

    }

    public Message findById(Long id){

        Optional<Message> result = MR.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        else {
            return null;
        }

    }

    public void delete(Long id) {
        MR.deleteById(id);

    }

    public void add(Message message) {
        message.setDate(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserPrincipal loggedUser = (MyUserPrincipal) authentication.getPrincipal();
        User currentUser = US.findByMail(loggedUser.getUsername()).get();
        message.setUser(currentUser);
        MR.save(message);


    }

    public boolean isMessageHolder (Message message){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserPrincipal loggedUser = (MyUserPrincipal) authentication.getPrincipal();
        User currentUser = US.findByMail(loggedUser.getUsername()).get();
        User messagerHolder = message.getUser();
        return (currentUser.equals(messagerHolder));


    }





}
