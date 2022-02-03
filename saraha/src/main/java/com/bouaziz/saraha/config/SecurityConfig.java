package com.bouaziz.saraha.config;

import com.bouaziz.saraha.services.auth.AppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationFilter applicationsFilter;
    private final AppUserDetailsService appUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       //login par email ou numéro de téléphone ou username
        auth.userDetailsService(appUserDetailsService)
                .passwordEncoder(passwordEncoder());
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.addFilterBefore(corsFilter(), SessionManagementFilter.class)
        //definir les chemin url authorisés ou non authorisés bel authetification
        //JWT:fama filter issta3mlou pour executer des traitements
       .csrf().disable()
               .authorizeRequests().antMatchers(
      //les patterns=requetes /url eli n7ébhom autorisés
    "/**/register",
               "/**/login",
               //tout ce qui concerne swagger
               "/api/**",
               "/v2/api-docs",
               "/swagger-resources",//tout ce qui image
               "/swagger-resources/**",
               "/configuration/ui",
               "/configuration/security",
               "/swagger-ui.html",
               "/webjars/**",
               "/v3/api-docs/**",
               "/swagger-ui/**"
               ).permitAll()
               .anyRequest()
              //n7ébou ykoun authentifié
               .authenticated()
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//kifeh n7éb nessna3 les sessions mte3i
        //applicationFilter se fait avant UsernamePasswordAuthentificationFilter
        //applicationsFilter(le filtre qui va valider le jwt token)
        //UsernamePasswordAuthenticationFilter
    http.addFilterBefore(applicationsFilter, UsernamePasswordAuthenticationFilter.class);
    }
    //on aura besoin de ce bean à l'extérieur
    @Bean//gestionnaire d'authentification
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManagerBean();
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // Don't do this in production, use a proper list  of allowed origins
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean//injecter la méthode car on aura besoin de cette méthode pour la partie authentification

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
