package PFE.eSeminaire.Controller;

import PFE.eSeminaire.Service.MessageService;
import PFE.eSeminaire.Service.UserService;
import PFE.eSeminaire.model.Message;
import PFE.eSeminaire.model.User;
import PFE.eSeminaire.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/forum")
@Controller
public class ForumController {

    @Autowired
    MessageService MS;

    @Autowired
    UserService US;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView forum() {
        Collection<Message> messages = MS.findAllMessages();
        return new ModelAndView("forum", "messages", messages);
    }

    @RequestMapping(value = "/addMessage",method = RequestMethod.GET )
    public String addMessage(Model model) {
        model.addAttribute("message", new Message());
        return "updateMessage";
    }

    @PostMapping(value = "")
    public String addMessage(@ModelAttribute @Valid Message message, BindingResult result) {
        if (result.hasErrors()) {
            return "";
        }
        MS.add(message);
        return "redirect:/forum";
    }

    @GetMapping(value = "/updateMessage/{id}")
    public ModelAndView updateMessage(@PathVariable Long id) {
        Message message = MS.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserPrincipal loggedUser = (MyUserPrincipal) authentication.getPrincipal();
        User currentUser;
        currentUser = US.findByMail(loggedUser.getUsername()).get();
        if(MS.isMessageHolder(MS.findById(id)) || currentUser.getRoles().contains("ADMIN")){
            return new ModelAndView("updateMessage", "message", message);
        }
        return new ModelAndView("error/messageManipulationError");

    }


    @RequestMapping(value = "/updateMessage/{id}", method = RequestMethod.POST)
    public String updateMessage(@PathVariable Long id, @ModelAttribute @Valid Message message,BindingResult result) {
        if (result.hasErrors()) {
            return "updateMessage";
        }
            MS.delete(id);
            MS.add(message);
            return "redirect:/forum";

    }

    @RequestMapping(value = "/deleteMessage/{id}")
    public String deleteMessage(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserPrincipal loggedUser = (MyUserPrincipal) authentication.getPrincipal();
        User currentUser;
        currentUser = US.findByMail(loggedUser.getUsername()).get();
        if(MS.isMessageHolder(MS.findById(id)) || currentUser.getRoles().contains("ADMIN")){
            MS.delete(id);
            return "redirect:/forum";
        }
        else{
            return "error/messageManipulationError";
        }


    }







}
