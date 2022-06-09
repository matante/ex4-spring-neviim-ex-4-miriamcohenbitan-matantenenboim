package hac.ex4spring.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth
                .inMemoryAuthentication()
                .withUser("admin").password(encoder.encode("1234")).roles("ADMIN")
                .and()
                .withUser("jim").password(encoder.encode("demo")).roles("ADMIN")
                .and()
                .withUser("bob").password(encoder.encode("demo")).roles("USER")
                .and()
                .withUser("ted").password(encoder.encode("demo")).roles("USER","ADMIN");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .formLogin()
                //.loginPage("/login") // <=============== uncomment this for a custom login page (see also the controller)
                //.loginProcessingUrl("/login")
                .defaultSuccessUrl("/admin", true)
                //.failureUrl("/login-error") // <===============  uncomment this for a custom login page (see also the controller)
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
             //   .antMatchers("/user/**").hasRole("USER")
               // .antMatchers("/shared/**").hasAnyRole("USER", "ADMIN")
                // custom error page for exceptions
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403.html");
    }

}
