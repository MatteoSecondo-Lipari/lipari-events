package com.lipari.events.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lipari.events.security.exceptions.AuthEntryPointJwt;
import com.lipari.events.security.jwt.AuthTokenFilter;


@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthEntryPointJwt unauthorizedHandler;
	
	@Autowired
	AccessDeniedHandler accessDeniedHandler;
	
	@Bean
	public AuthTokenFilter authTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
	    
	    return authenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
			.exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler))
			.authorizeHttpRequests(auth ->
				auth.requestMatchers("/auth/**").permitAll()
					.requestMatchers("/event/*/image").permitAll()
					.requestMatchers("/entertainer/stage-name/**").permitAll()
					.requestMatchers("/event/category/all-events").permitAll()
					.requestMatchers("/location/all").permitAll()
					.requestMatchers("/event/search/entertainer/{Entertainer}").permitAll()
					.requestMatchers("/event/search/name/{name}").permitAll()
					.requestMatchers("/event/searchbar/{search}").permitAll()
					.requestMatchers("/email/send/{Receiveremail}").permitAll()
					.anyRequest().authenticated()
			);
		
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

}
