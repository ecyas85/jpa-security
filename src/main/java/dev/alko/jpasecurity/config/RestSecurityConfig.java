package dev.alko.jpasecurity.config;

import dev.alko.jpasecurity.service.JpaUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class RestSecurityConfig {

    private static final String H2_CONSOLE_PATTERN = "/h2-console/**";

    private final JpaUserDetailsService jpaUserDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
//                .csrf(csrf -> csrf.ignoringRequestMatchers(request -> {
//                    String contextPath = request.getServletContext().getContextPath();
//                    return request.getRequestURI().startsWith(contextPath + "/h2-console/");
//                }))
                .csrf(csrf -> csrf.ignoringAntMatchers(H2_CONSOLE_PATTERN))
                //.csrf().disable()
                .authorizeRequests(auth -> auth
                        .antMatchers(H2_CONSOLE_PATTERN).permitAll()
                        .mvcMatchers("/api/v1/posts").permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(jpaUserDetailsService)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .formLogin(Customizer.withDefaults())
                .build();



                // ===================================================================
                // Spring Boot 3.x config
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/h2-console/**").permitAll()
//                        .requestMatchers("/api/v1/posts").permitAll()
//                        .anyRequest().authenticated())
//                .headers(headers -> headers.frameOptions().sameOrigin())
//                .build();
                // ===================================================================
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
