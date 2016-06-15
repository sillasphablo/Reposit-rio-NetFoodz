package com.netfoodz.store.authentication.infrastructure;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.netfoodz.store.authentication.application.FacebookOAuth2Service;
import com.netfoodz.store.authentication.domain.model.FacebookUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScribeFacebookOAuth2Service implements FacebookOAuth2Service {

	private ObjectMapper objectMapper;

	@Autowired
	public ScribeFacebookOAuth2Service(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public FacebookUser login(String userId, String accessToken, String appId, String appSecret) throws IOException {
		OAuth20Service service = new ServiceBuilder()
				.apiKey(appId)
				.apiSecret(appSecret)
				.build(FacebookApi.instance());

		OAuth2AccessToken token = new OAuth2AccessToken(accessToken);

		OAuthRequest request = new OAuthRequest(Verb.GET, "https://graph.facebook.com/v2.5/" + userId + "?fields=id,name,email,cover", service);
		service.signRequest(token, request);
		Response response = request.send();
		System.out.println(response.getBody());

		return getFacebookUserFromResponse(response);
	}

	private FacebookUser getFacebookUserFromResponse(Response response) throws IOException {
		FacebookUser fbUser = objectMapper.readValue(response.getBody(), FacebookUser.class);
		fbUser.setProfileUrl("http://facebook.com/" + fbUser.getId());
		fbUser.setProfileImageUrl("https://graph.facebook.com/" + fbUser.getId() + "/picture?type=large");
		return fbUser;
	}
}
