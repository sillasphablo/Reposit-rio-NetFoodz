package com.netfoodz.store.authentication.infrastructure;

import com.netfoodz.common.domain.model.EmailAddress;
import com.netfoodz.store.authentication.domain.model.BasicUser;
import com.netfoodz.store.authentication.domain.repository.BasicUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SpringUserProvider implements UserDetailsService {

	private BasicUserRepository basicUserRepository;

	public SpringUserProvider(BasicUserRepository basicUserRepository) {
		this.basicUserRepository = basicUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		BasicUser user = basicUserRepository.findByEmail(new EmailAddress(email));

		return new SpringUser(user);
	}
}
