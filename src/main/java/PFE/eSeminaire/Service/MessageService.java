package PFE.eSeminaire.Service;

import PFE.eSeminaire.model.Message;
import PFE.eSeminaire.model.Seminar;
import PFE.eSeminaire.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository MR;

    public Message save(Message message){
        return MR.save(message);

    }

    public List<Message> findAllMessages(){
        return MR.findAll();

    }

    public void delete(Long id) {
        MR.deleteById(id);

    }

}
