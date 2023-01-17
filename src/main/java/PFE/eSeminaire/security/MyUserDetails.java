package PFE.eSeminaire.security;

import PFE.eSeminaire.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) {
        var user = userRepository.findByMail(mail);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(mail);
        }
        return new MyUserPrincipal(user.get());
    }
}