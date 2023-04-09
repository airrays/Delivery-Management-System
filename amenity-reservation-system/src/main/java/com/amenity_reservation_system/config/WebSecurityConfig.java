package com.amenity_reservation_system.config;

import com.amenity_reservation_system.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                .requestMatchers("/", "/h2-console/**").permitAll()
//                .requestMatchers(toH2Console()).permitAll()
//                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
//                .requestMatchers("/", "/webjars/**").permitAll()                 //.anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .logoutSuccessUrl("/");
        http.authorizeHttpRequests().requestMatchers("/**").permitAll();
        //http.headers().frameOptions().sameOrigin();
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //http.headers().frameOptions().disable();
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .requestMatchers("/h2-console/**");
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsServiceImpl userDetailsService)
            throws Exception
    {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }


}
