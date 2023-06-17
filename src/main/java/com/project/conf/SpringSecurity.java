package com.project.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/registration/**").permitAll()
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/home/**").hasAnyRole("USER", "ADMIN", "FULLUSER")
                        .requestMatchers("/notes/**").hasAnyRole("FULLUSER", "ADMIN")
                        .requestMatchers("/addNote/**").hasAnyRole("FULLUSER", "ADMIN")
                        .requestMatchers("/adminPanel/**").hasAnyRole("ADMIN")
                        .requestMatchers("/editUser/**").hasAnyRole("ADMIN")
                        .requestMatchers("/sharedNotes/**").hasAnyRole("USER", "ADMIN", "FULLUSER")
                        .requestMatchers("/addCategory/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/editNote/**").hasAnyRole("FULLUSER", "ADMIN")

                        
                        .requestMatchers("/style.css/**").permitAll()
                        .requestMatchers("/logo.png/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home/")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .exceptionHandling().accessDeniedPage("/access-denied");
        return http.build();
    }
}
