package mybootapp.service;

import mybootapp.model.Formation;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import org.springframework.validation.Validator;
import java.text.Normalizer;

@Service
public class FormationCreationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Formation.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Formation product = (Formation) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "composante",
                "composante", "choisissez une composante");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "CERTIFINFO",
                "CERTIFINFO", "choisissez un code CERTIFINFO");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code",
                "code", "choisissez un code de formation");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "intitule",
                "intitule", "choisissez un intitulé de formation");
    }
}
