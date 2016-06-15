package com.netfoodz.store.authentication.domain.repository;

public interface SocialAuthRepository {

	String findFacebookAppId();
	String findFacebookAppSecret();
}
