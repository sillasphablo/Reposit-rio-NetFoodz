package com.netfoodz.store.authentication.domain.repository;

import com.netfoodz.store.authentication.domain.model.FacebookUser;
import com.netfoodz.store.authentication.domain.model.SocialUser;

public interface SocialUserRepository {

	FacebookUser findFacebookUser(String userId);

	void save(SocialUser socialUser);
}
