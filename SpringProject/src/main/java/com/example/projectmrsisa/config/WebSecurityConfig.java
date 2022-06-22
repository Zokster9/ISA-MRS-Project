package com.example.projectmrsisa.config;

import com.example.projectmrsisa.security.RestAuthenticationEntryPoint;
import com.example.projectmrsisa.security.TokenAuthenticationFilter;
import com.example.projectmrsisa.service.CustomUserDetailsService;
import com.example.projectmrsisa.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/auth/**").permitAll()
                .antMatchers("/users/register").permitAll()
                .antMatchers("/ships/getAll").permitAll()
                .antMatchers("/adventures/getAll").permitAll()
                .antMatchers("/users/findByEmail/{id}").permitAll()
                .antMatchers("/retreats/getAll").permitAll()
                .antMatchers("/users/activate-client/{id}").permitAll()
                .antMatchers("/users/adventures/getAll").permitAll()
                .antMatchers("/tags").permitAll()
                .antMatchers("/services/search").permitAll()
                .antMatchers("/retreats/get/{id}").permitAll()
                .antMatchers("/ships/get/{id}").permitAll()
                .antMatchers("/users/getAllInstructors").permitAll()
                .antMatchers("/users/searchInstructors").permitAll()
                .antMatchers("/adventures/getInstructorsAdventures/{id}").permitAll()
                .antMatchers("/adventures/get/{id}").permitAll()
                .anyRequest().authenticated().and()
                .cors().and()
                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, customUserDetailsService), BasicAuthenticationFilter.class);
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, "/auth/login");
        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
                "/**/*.css", "/**/*.js");

    }
}
