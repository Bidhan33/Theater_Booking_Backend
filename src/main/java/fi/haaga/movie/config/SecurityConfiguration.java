package fi.haaga.movie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
	
	@Autowired
	private JwtAuthFilter authFilter;

	@Bean
	UserDetailsService userDetailsService() {
		return new UserInfoUserDetailsService();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(c -> c.disable())
		.authorizeHttpRequests(req -> 
		req.requestMatchers("/user/**").permitAll()
		// Require specific roles for administrative operations
		.requestMatchers("/movie/add", "/movie/update/**", "/movie/delete/**").hasAuthority("ADMIN")
		.requestMatchers("/theater/add", "/theater/update/**", "/theater/delete/**").hasAuthority("ADMIN")
		.requestMatchers("/show/add", "/show/update/**", "/show/delete/**").hasAuthority("ADMIN")
		// Allow read operations for all authenticated users
		.requestMatchers("/movie/getAll", "/movie/get/**").authenticated()
		.requestMatchers("/theater/getAll", "/theater/get/**").authenticated()
		.requestMatchers("/show/getAll", "/show/get/**").authenticated()
		.requestMatchers("/ticket/**").authenticated()
		.anyRequest().authenticated())
	.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	.authenticationProvider(authenticationProvider())
	.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
