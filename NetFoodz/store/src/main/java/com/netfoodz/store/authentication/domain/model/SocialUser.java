package com.netfoodz.store.authentication.domain.model;


public interface SocialUser extends User {

	String getId();
	String getProfileUrl();
	String getProfileImageUrl();
}
