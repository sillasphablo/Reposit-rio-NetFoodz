package com.netfoodz.rest.config.spring;

import com.netfoodz.store.authentication.domain.repository.BasicUserRepository;
import com.netfoodz.store.authentication.infrastructure.SpringUserProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService(BasicUserRepository basicUserRepository) {
		return new SpringUserProvider(basicUserRepository);
	}

	@Bean
	public ProviderManager authenticationManager(UserDetailsService userDetailsService) {
		List<AuthenticationProvider> providers = new ArrayList<>();

		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setPasswordEncoder(new BCryptPasswordEncoder());
//		ReflectionSaltSource saltSource = new ReflectionSaltSource();
//		saltSource.setUserPropertyToUse("username");
//		daoProvider.setSaltSource(saltSource);
		daoProvider.setUserDetailsService(userDetailsService);

		AnonymousAuthenticationProvider anonymousProvider = new AnonymousAuthenticationProvider("anonymousKey");

		providers.add(daoProvider);
		providers.add(anonymousProvider);
		ProviderManager providerManager = new ProviderManager(providers);
		providerManager.setEraseCredentialsAfterAuthentication(true);
		return providerManager;
	}
}
