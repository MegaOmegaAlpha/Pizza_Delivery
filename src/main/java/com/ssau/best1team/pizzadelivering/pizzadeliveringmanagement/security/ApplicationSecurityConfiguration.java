package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.security;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Role;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.security.jwt.CustomUserDetailsService;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.security.jwt.JwtAuthenticationEntryPoint;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.security.jwt.JwtAuthenticationFilter;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationFilter jwtFilter;
    private final JwtAuthenticationEntryPoint entryPoint;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public ApplicationSecurityConfiguration(JwtAuthenticationFilter jwtFilter,
                                            JwtAuthenticationEntryPoint entryPoint,
                                            CustomUserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.entryPoint = entryPoint;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/pizzas").permitAll()
                .antMatchers("/api/register", "/api/auth").permitAll()
                .antMatchers("/api/admin/**").hasAuthority(Role.ADMIN_ROLE)
                .antMatchers("/api/customer/**").hasAnyAuthority(Role.CUSTOMER_ROLE, Role.ADMIN_ROLE)
                .antMatchers("/api/courier/**").hasAuthority(Role.COURIER_ROLE)
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
