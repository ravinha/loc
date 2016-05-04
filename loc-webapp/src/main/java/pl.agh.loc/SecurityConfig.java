package pl.agh.loc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.agh.loc.services.MongoUserDetailService;

/**
 * Created by Rafal on 2016-03-27.
 */
@Configuration
@EnableWebSecurity
@ComponentScan("pl.agh.loc")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MongoUserDetailService mongoUserDetailService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider());
    }

    @Bean
    AuthenticationProvider customAuthenticationProvider() {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setUserDetailsService(mongoUserDetailService);
        /* other properties etc */
        return impl ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/dodaj").hasRole("USER").and()
                .formLogin()
                .and()
                .httpBasic();
    }
}