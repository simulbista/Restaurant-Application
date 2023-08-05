package com.humber.j2ee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		admin page is /restaurant/admin/** and user page is /restaurant/**
		http.authorizeRequests().requestMatchers("/restaurant/admin/**").hasRole("ADMIN")
				.requestMatchers("/restaurant/**").hasRole("USER");
//		allow everyone to access login page without any authentication
		http.formLogin().loginPage("/login").permitAll();
		
		http.logout().logoutUrl("/logout").permitAll();
		
		//route after successful login
		http.formLogin().defaultSuccessUrl("/restaurant/menu");
		return http.build();
		
	}

	// credentials for admin and user
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withUsername("user")
				.password(passwordEncoder().encode("password"))  //hashing user password using bcrypt
				.roles("USER")
				.build();
		UserDetails admin = User.withUsername("admin")
				.password(passwordEncoder().encode("password"))  //hashing admin password using bcrypt
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin);
	}

//	ignore authentication for some pages (like you want them to be publicly available with need for login)
	@Bean
	public WebSecurityCustomizer ignoreResources() {
		return (webSecurity) -> webSecurity.ignoring().requestMatchers("/restaurant/menu");
	}

//	encode password
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
