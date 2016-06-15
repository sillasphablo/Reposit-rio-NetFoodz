package com.netfoodz.store.authentication.infrastructure;

import com.netfoodz.common.domain.model.EmailAddress;
import com.netfoodz.store.authentication.application.SessionService;
import com.netfoodz.store.authentication.domain.exception.NoUserAuthenticatedException;
import com.netfoodz.store.authentication.domain.model.AnonymousUser;
import com.netfoodz.store.authentication.domain.model.Role;
import com.netfoodz.store.authentication.domain.model.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpringSessionService implements SessionService {

	@Override
	public void login(User user) {
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null, getAuthorities(user.getRoles()));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private List<GrantedAuthority> getAuthorities(List<Role> roles) {
		if(roles == null) return null;

		return roles.stream()
				.map(r -> new SimpleGrantedAuthority(r.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public void logout() {
		User user = new AnonymousUser();
		Authentication auth = new AnonymousAuthenticationToken("anonymousUser", user, getAuthorities(user.getRoles()));
		SecurityContextHolder.getContext().setAuthentication(auth);

	}

	@Override
	public EmailAddress getEmailFromLoggedUser() {
		EmailAddress email;
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			email = (EmailAddress) authentication.getPrincipal();
		} catch (NullPointerException | ClassCastException e) {
			throw new NoUserAuthenticatedException();
		}

		return email;
	}

	@Override
	public boolean isAnonymousUser() {
		boolean isAnonymous;

		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Object details = authentication.getDetails();
			isAnonymous = details.equals("anonymousUser");
		} catch (NullPointerException e) {
			return false;
		}

		return isAnonymous;
	}
}
