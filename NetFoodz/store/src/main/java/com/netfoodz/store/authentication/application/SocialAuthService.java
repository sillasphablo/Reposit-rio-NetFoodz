package com.netfoodz.store.authentication.application;

import com.netfoodz.store.authentication.application.exception.WebRequestException;
import com.netfoodz.store.authentication.domain.model.FacebookUser;
import com.netfoodz.store.authentication.domain.model.SocialUser;
import com.netfoodz.store.authentication.domain.repository.SocialAuthRepository;
import com.netfoodz.store.authentication.domain.repository.SocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SocialAuthService {

	private SessionService sessionService;
	private FacebookOAuth2Service facebook;
	private SocialUserRepository socialUserRepository;
	private SocialAuthRepository socialAuthRepository;

	@Autowired
	public SocialAuthService(SessionService sessionService,
							 FacebookOAuth2Service facebook,
							 SocialUserRepository socialUserRepository,
							 SocialAuthRepository socialAuthRepository) {
		this.sessionService = sessionService;
		this.facebook = facebook;
		this.socialUserRepository = socialUserRepository;
		this.socialAuthRepository = socialAuthRepository;
	}


	public boolean authenticateWithFacebook(String userId, String accessToken) throws WebRequestException {
		String appId = socialAuthRepository.findFacebookAppId();
		String appSecret = socialAuthRepository.findFacebookAppSecret();
		SocialUser user = socialUserRepository.findFacebookUser(userId);

		FacebookUser facebookUser;
		try {
			facebookUser = facebook.login(userId, accessToken, appId, appSecret);
		} catch (IOException e) {
			throw new WebRequestException("Error on request to login facebook user", e);
		}

		if (facebookUser == null)
			return false;

		if (user == null) {
			socialUserRepository.save(facebookUser);
			user = facebookUser;
		}

		sessionService.login(user);

		return true;
	}
}