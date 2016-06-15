package com.netfoodz.store.authentication.domain.model;

import com.netfoodz.common.domain.model.EmailAddress;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class FacebookUser implements SocialUser {

	private String id;
	private String name;
	private EmailAddress email;
	private Cover cover;
	private boolean enabled = true;
	private String profileUrl;
	private String profileImageUrl;
	private List<Role> roles = new ArrayList<>();

	public String getCoverUrl() {
		return Optional.of(cover)
				.map(Cover::getSource)
				.orElse(null);
	}

	public Optional<Cover> getCover() {
		return Optional.of(cover);
	}

	public void setCover(Cover cover) {
		this.cover = cover;
	}

	public static class Cover {
		private String id;
		private String source;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}
	}
}
