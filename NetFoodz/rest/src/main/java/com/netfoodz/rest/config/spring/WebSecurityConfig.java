package com.netfoodz.rest.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
//				.antMatchers(HttpMethod.POST, "/api/**").authenticated()
//				.antMatchers(HttpMethod.PUT, "/api/**").authenticated()
//				.antMatchers(HttpMethod.DELETE, "/api/**").authenticated()
				.anyRequest().permitAll()
				.and()
				.httpBasic()
				.and()
				.sessionManagement().maximumSessions(-1).sessionRegistry(sessionRegistry());
	}
}
