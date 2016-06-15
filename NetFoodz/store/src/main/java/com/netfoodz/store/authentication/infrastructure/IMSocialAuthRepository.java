package com.netfoodz.store.authentication.infrastructure;

import com.netfoodz.store.authentication.domain.repository.SocialAuthRepository;
import org.springframework.stereotype.Repository;

@Repository
public class IMSocialAuthRepository implements SocialAuthRepository {

	@Override
	public String findFacebookAppId() {
		return "142373552842180";
	}

	@Override
	public String findFacebookAppSecret() {
		return "1f89612ed6fff55843fc3dc4a00ff94e";
	}
}
