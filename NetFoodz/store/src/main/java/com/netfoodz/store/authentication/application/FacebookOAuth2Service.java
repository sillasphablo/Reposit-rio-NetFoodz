package com.netfoodz.store.authentication.application;

import com.netfoodz.store.authentication.domain.model.FacebookUser;

import java.io.IOException;

public interface FacebookOAuth2Service {

	FacebookUser login(String userId, String accessToken, String appId, String appSecret) throws IOException;
}
