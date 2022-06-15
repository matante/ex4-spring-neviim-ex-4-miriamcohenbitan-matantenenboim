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
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();


        auth
                .inMemoryAuthentication()
                .withUser("admin").password(encoder.encode("admin")).roles("ADMIN")
                .and()
                .withUser("user1").password(encoder.encode("user")).roles("USER")
                .and()
                .withUser("user2").password(encoder.encode("user")).roles("USER")
                .and()
                .withUser("user3").password(encoder.encode("user")).roles("USER");
        // username "admin" and password "admin"
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.formLogin()
             //   .defaultSuccessUrl("/admin", true)
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/cart/checkout/**").hasRole("USER")

                .and().exceptionHandling().accessDeniedPage("/403.html");
        //                .antMatchers("/checkout/**").hasRole("USER")
    }

}
