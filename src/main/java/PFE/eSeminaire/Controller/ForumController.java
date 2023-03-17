package PFE.eSeminaire.Controller;

import PFE.eSeminaire.Service.MessageService;
import PFE.eSeminaire.model.Message;
import PFE.eSeminaire.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    @RequestMapping(value = "/addMessage",method = RequestMethod.GET )
    @PreAuthorize("hasAuthority('USER')")
    public String addMessage(Model model) {
        model.addAttribute("message", new Message());
        return "newMessage";
    }

    @RequestMapping(value = "/addMessage",method = RequestMethod.POST )
    @PreAuthorize("hasAuthority('USER')")
    public String addMessage(@ModelAttribute @Valid Message message,BindingResult result) {
        if (result.hasErrors()) {
            return "newMessage";
        }
        MS.add(message);
        return "redirect:/forum";
    }
    @RequestMapping(value = "/updateMessage/{id}",method = RequestMethod.GET )
    @PreAuthorize("hasAuthority('USER')")
    public String updateMessage(@PathVariable Long id) {
        return "newMessage";
    }
    @RequestMapping(value = "/updateMessage/{id}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('USER')")
    public String updateMessage(@PathVariable Long id, @ModelAttribute @Valid Message message,BindingResult result) {
        if (result.hasErrors()) {
            return "newMessage";
        }
        MS.delete(id);
        MS.add(message);
        return "redirect:/forum";
    }

    @RequestMapping(value = "/deleteMessage/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public String deleteMessage(@PathVariable Long id) {
        MS.delete(id);
        return "redirect:/forum";
    }







}
