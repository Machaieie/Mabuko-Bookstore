package com.livrariamabuko.Livraria.Mabuko.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.livrariamabuko.Livraria.Mabuko.security.SecurityFilter;
import com.livrariamabuko.Livraria.Mabuko.security.filter.AuthenticationFilter;
import com.livrariamabuko.Livraria.Mabuko.security.filter.AuthorizationFilter;
import com.livrariamabuko.Livraria.Mabuko.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	

	@Autowired
    private final UserService userService;

	private final PasswordEncoder passwordEncoder;

	public SecurityConfigurations(UserService userService, @Lazy PasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;

	}
 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		AuthenticationFilter customAuthenticationFilter = new AuthenticationFilter(authenticationManagerBean());
		customAuthenticationFilter.setFilterProcessesUrl("/api/v1/auth/login");
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/api/v1/auth/**").permitAll();
		http.authorizeRequests().antMatchers("/api/v1/settings/**").permitAll();
		http.authorizeRequests().antMatchers("/api/v1/auth/signup/**").permitAll();
		http.authorizeRequests().antMatchers("/api/v1/clientsignup/**").permitAll();
		http.authorizeRequests().antMatchers("/api/v1/sendingemail/**").permitAll();
		http.authorizeRequests().antMatchers("/api/v1/auth/reset/**").permitAll();
		
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(customAuthenticationFilter);
		http.addFilterBefore( new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.formLogin().disable().formLogin().disable().httpBasic().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
