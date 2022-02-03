package com.nnk.springboot.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/*").hasAuthority("ADMIN")
                .antMatchers("/", "/app/login", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/app/error")
                .and()
                .formLogin(form -> form
                        .defaultSuccessUrl("/bidList/list")
                        .loginPage("/app/login")
                        .loginProcessingUrl("/login")
                        .failureUrl("/app/login?error"))
                .oauth2Login()
                .defaultSuccessUrl("/bidList/list")
                .and()
                .logout()
                .logoutSuccessUrl("/app/login?logout")
                .logoutUrl("/app/logout")
                .deleteCookies("JSESSIONID");
    }
}
