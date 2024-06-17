package net_javaguides.teacher.config;

import lombok.AllArgsConstructor;
import net_javaguides.teacher.Security.JwtRequestFilter;
import net_javaguides.teacher.Utils.RoleName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    private AuthenticationProvider authenticationProvider;
    private JwtRequestFilter jwtRequestFilter;
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http

                .authorizeHttpRequests(authorize -> authorize
                       .requestMatchers(
                                "/registro**",
                                "/js/**",
                                "/css/**",
                                "/img/**")

                        .permitAll()
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers(HttpMethod.POST).permitAll()
                        .requestMatchers(HttpMethod.DELETE).permitAll()
                        .requestMatchers(HttpMethod.PUT).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()


                   //     .requestMatchers("/grupo/**").hasAnyAuthority(RoleName.ADMIN.name())
                        .requestMatchers("/auth/**").permitAll()

                )
                .sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf
                        .disable())
                .build();

    }
}
