package PFE.eSeminaire.Controller;

import PFE.eSeminaire.Service.MessageService;
import PFE.eSeminaire.model.Message;
import PFE.eSeminaire.model.Seminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@RequestMapping("/forum")
@Controller
public class ForumController {

    @Autowired
    MessageService MS;


    @RequestMapping(value = "", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView forum() {

        Collection<Message> messages = MS.findAllMessages();
        return new ModelAndView("forum", "messages", messages);
    }

    @RequestMapping(value = "/messageDelete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String messageDelete(@PathVariable Long id) {
        MS.delete(id);
        return "redirect:/forum";
    }

}
