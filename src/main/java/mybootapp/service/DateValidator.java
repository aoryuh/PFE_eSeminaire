package mybootapp.service;

import mybootapp.model.Session;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Service
public class DateValidator implements Validator {

    @Override    public boolean supports(Class<?> clazz) {
        return Session.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Session session = (Session) target;

        if ((session.getDebut().after(session.getFin()))) {
            errors.rejectValue("debut", "debut après fin",
                    "La date de début doit être inférieure à la date de fin");
        }
    }

}