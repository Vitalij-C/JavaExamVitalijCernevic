package lt.java.exam.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                            request.requestMatchers(
                                    "api/v1/auth/login",
                                    "api/v1/auth/register",
                                    "api/v1/auth/test",

                                    "swagger-ui/**",
                                    "v3/api-docs/**"
                            ).permitAll();

                            //User
                            request.requestMatchers(
                                    "api/v1/user-test"
                            ).hasAnyAuthority("USER");

                            //Admin
                            request.requestMatchers(
                                    "api/v1/admin-test"
                            ).hasAnyAuthority("ADMIN");

                            //User, Admin
                            request.requestMatchers(
                                    "api/v1/auth"
                            ).hasAnyAuthority("USER", "ADMIN");

                            request.anyRequest().authenticated();
                        }
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
