package mybootapp.web;


import org.jasig.cas.client.session.SingleSignOutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.stereotype.Component;


@Component
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private AuthenticationEntryPoint entryPoint;

    @Autowired
    private CasAuthenticationFilter casAuthenticationFilter;

    @Autowired
    private SingleSignOutFilter singleSignOutFilter;

    @Autowired
    private LogoutFilter requestSingleLogoutFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**")
                .hasRole("USER")
                .antMatchers("/", "/login/cas", "/favicon.ico", "/error")
                .permitAll()
               .antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/correspondant").hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                //Set authentication entry
                .authenticationEntryPoint(entryPoint)
                .and()
                //Add filters to perform single sign on processing logic
                .addFilter(casAuthenticationFilter)
                //Process authentication logic
                .addFilterBefore(singleSignOutFilter, CasAuthenticationFilter.class)
                //Process logout
                .addFilterBefore(requestSingleLogoutFilter, LogoutFilter.class);
    }

}
