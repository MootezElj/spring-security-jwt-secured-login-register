package com.project.doc.security.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/login/**", "/register-doc/**", "/register-pat/**,/api/user/").permitAll()

                // Testing Endpoints START
                .antMatchers(HttpMethod.GET, "/test/heyDoc/").hasAuthority("DOCTOR")
                .antMatchers(HttpMethod.GET, "/test/heyPatient/").hasAuthority("PATIENT")
                // Testing Endpoints END

                .antMatchers(HttpMethod.GET, "/api/documents/currentPatient/").hasAuthority("PATIENT")
                .antMatchers(HttpMethod.POST, "/api/documents/currentPatient/").hasAuthority("PATIENT")

                .antMatchers(HttpMethod.GET, "/api/documents/**").hasAnyAuthority("DOCTOR")
                .antMatchers(HttpMethod.PUT, "/api/documents/**").hasAuthority("DOCTOR")
                .antMatchers(HttpMethod.POST, "/api/documents/**").hasAnyAuthority("DOCTOR")
                .antMatchers(HttpMethod.DELETE, "/api/documents/**").hasAuthority("DOCTOR")

                .antMatchers(HttpMethod.GET, "/api/patients/").hasAuthority("DOCTOR")
                .antMatchers(HttpMethod.GET, "/api/users/**").hasAuthority("DOCTOR");

        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
