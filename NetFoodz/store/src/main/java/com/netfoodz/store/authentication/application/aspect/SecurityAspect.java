package com.netfoodz.store.authentication.application.aspect;

import com.netfoodz.store.authentication.application.SessionService;
import com.netfoodz.store.authentication.application.annotation.Authenticated;
import com.netfoodz.store.authentication.domain.exception.NotAuthorizedException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

	private SessionService sessionService;

	@Autowired
	public SecurityAspect(SessionService sessionService) {
		this.sessionService = sessionService;
	}


	@Before("@annotation(authenticated)")
	public void isAuthenticated(Authenticated authenticated) {
		if(sessionService.isAnonymousUser()) {
			throw new NotAuthorizedException();
		}
	}
}
