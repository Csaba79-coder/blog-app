package com.example.blogapp.config;

import com.example.blogapp.model.User;
import com.example.blogapp.model.UserAuthority;
import com.example.blogapp.repository.UserAuthorityRepository;
import com.example.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/register", "/blogs").permitAll()
                .anyRequest()
                .authenticated();
        http.csrf().disable();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled "
                        + "from users "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select username, authority "
                        + "from authorities "
                        + "where username = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository,
                                               UserAuthorityRepository userAuthorityRepository) {
        return args -> {
            userRepository.save(User.builder()
                    .username("admin")
                    .password(new BCryptPasswordEncoder().encode("password"))
                    .enabled(true)
                    .build()
            );
            userAuthorityRepository.save(UserAuthority.builder()
                    .username("admin")
                    .authority("ROLE_ADMIN")
                    .build()
            );
        };
    }
}
