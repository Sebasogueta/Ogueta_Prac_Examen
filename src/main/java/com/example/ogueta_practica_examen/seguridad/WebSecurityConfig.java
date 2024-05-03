package com.example.ogueta_practica_examen.seguridad;

import com.example.ogueta_practica_examen.entities.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig{


    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {


        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( authz -> authz
                        .requestMatchers(HttpMethod.POST,Constans.LOGIN_URL).permitAll()
                        .requestMatchers(HttpMethod.POST,Constans.SIGNUP_URL).permitAll()

                        .requestMatchers(HttpMethod.DELETE, "/vuelos/**").hasAuthority("ROLE_" + Rol.ADMIN)
                        .requestMatchers(HttpMethod.POST, "/vuelos/**").hasAuthority("ROLE_" + Rol.ADMIN)
                        .requestMatchers(HttpMethod.PUT, "/vuelos/**").hasAuthority("ROLE_" + Rol.ADMIN)
                        .requestMatchers(HttpMethod.GET, "/vuelos/**").hasAnyAuthority("ROLE_" + Rol.ADMIN, "ROLE_" + Rol.ENTRENADOR)

                        .requestMatchers(HttpMethod.DELETE, "/sesiones/**").hasAuthority("ROLE_" + Rol.ENTRENADOR) //debe borrar las propias, no todas
                        .requestMatchers(HttpMethod.POST, "/sesiones/**").hasAuthority("ROLE_" + Rol.ENTRENADOR)
                        .requestMatchers(HttpMethod.PUT, "/sesiones/{id}").hasAuthority("ROLE_" + Rol.ENTRENADOR)
                        .requestMatchers(HttpMethod.GET, "/sesiones/mis-sesiones").hasAuthority("ROLE_" + Rol.ENTRENADOR)

                        .requestMatchers(HttpMethod.PUT, "/sesiones/{id}/apuntarse").hasAuthority("ROLE_" + Rol.USER)
                        .requestMatchers(HttpMethod.PUT, "/sesiones/{id}/desapuntarse").hasAuthority("ROLE_" + Rol.USER)
                        .requestMatchers(HttpMethod.GET, "/sesiones/apuntadas").hasAuthority("ROLE_" + Rol.USER)

                        .requestMatchers(HttpMethod.GET, "/sesiones/**").hasAnyAuthority("ROLE_" + Rol.ADMIN, "ROLE_" + Rol.ENTRENADOR, "ROLE_" + Rol.USER)

                        .requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyAuthority("ROLE_" + Rol.ADMIN)
                        .requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyAuthority("ROLE_" + Rol.ADMIN)
                        .requestMatchers(HttpMethod.POST, "/usuarios/**").hasAnyAuthority("ROLE_" + Rol.ADMIN)
                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasAnyAuthority("ROLE_" + Rol.ADMIN)

                        .anyRequest().authenticated())
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}


