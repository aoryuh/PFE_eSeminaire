package mybootapp.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mybootapp.model.Composante;
import mybootapp.model.Utilisateur;
import mybootapp.service.UtilisateurService;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CasUserDetailService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {


    @Autowired
    UtilisateurService utilisateurService;

    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken authentication)
            throws UsernameNotFoundException {

        AttributePrincipal principal = authentication.getAssertion().getPrincipal();
        Map attributes = principal.getAttributes();
        String email = (String) attributes.get("mail");
        String affi = (String) attributes.get("eduPersonPrimaryAffiliation");
        String username = authentication.getName();
        System.out.println("username = " + username);
        System.out.println("email = " + email);
        System.out.println("affi = " + affi);


        Utilisateur utilisateur = utilisateurService.getByidCAS(username);
        Collection<SimpleGrantedAuthority> collection = new ArrayList<SimpleGrantedAuthority>();
        if (utilisateur!= null && utilisateur.isAdmin()){
            collection.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            collection.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        var composante = new Composante();
        if (utilisateur!= null){
            composante = utilisateur.getIdComposante();}
        if(composante != null ){
            var role ="ROLE_COMPOSANTE" + composante.getId();
            collection.add(new SimpleGrantedAuthority(role));
        }
        var user = new User(username, "", collection);
        return user;
    }
}

