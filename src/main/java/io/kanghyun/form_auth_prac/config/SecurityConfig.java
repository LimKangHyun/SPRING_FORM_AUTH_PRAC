package io.kanghyun.form_auth_prac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .authorizeHttpRequests(
                        auth ->
                        {
                            auth.requestMatchers("/signup", "/signin")
                                    .anonymous()
                                .requestMatchers("/user/**")
                                    .hasAnyAuthority("ADMIN", "MANAGER", "MEMBER")
                                .requestMatchers("/manager/**")
                                    .hasAnyAuthority("ADMIN", "MANAGER")
                                .requestMatchers("/admin/**")
                                    .hasAuthority("ADMIN")
                                .anyRequest()
                                    .authenticated();
                        }
                )
                .build();
    }
}
