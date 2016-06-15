package com.netfoodz.store.authentication.infrastructure;

import com.netfoodz.store.authentication.domain.model.FacebookUser;
import com.netfoodz.store.authentication.domain.model.SocialUser;
import com.netfoodz.store.authentication.domain.repository.SocialUserRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Component
public class IMSocialUserRepository implements SocialUserRepository {

	private final Map<String, FacebookUser> facebookUsers = new HashMap<>();

	@Override
	public FacebookUser findFacebookUser(String userId) {
		Assert.notNull(userId, "User id must not be null");

		for (String s : facebookUsers.keySet()) {
			if(s.equals(userId))
				return facebookUsers.get(s);
		}
		return null;
	}

	@Override
	public void save(SocialUser socialUser) {
		Assert.notNull(socialUser, "User must not be null");

		if(socialUser instanceof FacebookUser) {
			facebookUsers.put(socialUser.getId(), (FacebookUser) socialUser);
		}
	}
}
