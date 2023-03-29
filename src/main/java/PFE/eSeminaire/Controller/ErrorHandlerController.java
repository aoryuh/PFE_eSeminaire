package PFE.eSeminaire.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
class ErrorHandlerController implements ErrorController {

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public String handleException(Exception e) {
        System.err.println("rException:");
        e.printStackTrace(System.err);
        return "une erreur inconnue s'est produite : <a class=\"navbar-brand nav-right\" href=\"/\">retourner à l'accueil</a>\n";
    }



}