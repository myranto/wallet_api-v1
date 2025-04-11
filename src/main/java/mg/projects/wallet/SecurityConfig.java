package mg.projects.wallet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import mg.projects.wallet.services.AuthService;
import mg.projects.wallet.services.security.jwt.JWTUtility;
import mg.projects.wallet.services.security.jwt.JwtRequestFilter;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthService userDetailsService;

    public SecurityConfig(AuthService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors().and()
                .addFilterBefore(new JwtRequestFilter(userDetailsService, new JWTUtility()),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/reset/password").permitAll()
                        .anyRequest().authenticated()
                // .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
