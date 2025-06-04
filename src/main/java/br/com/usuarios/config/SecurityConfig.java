package br.com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable()) // Desativa CSRF
	        .authorizeHttpRequests(auth -> auth
	            /*.requestMatchers(HttpMethod.POST, "/usuarios").authenticated()
	            .requestMatchers(HttpMethod.POST, "/usuarios/login").authenticated()
	            .requestMatchers(HttpMethod.GET, "/usuarios").authenticated()	*/            
	            .anyRequest().permitAll()
	            
	        )
				//.httpBasic(withDefaults()); // Mantém autenticação Basic
			;

	    return http.build();
	}

	//Criptografia de senhas
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}