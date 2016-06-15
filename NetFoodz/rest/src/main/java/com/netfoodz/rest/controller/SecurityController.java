package com.netfoodz.rest.controller;

import com.netfoodz.common.domain.model.EmailAddress;
import com.netfoodz.store.authentication.application.PasswordAuthService;
import com.netfoodz.store.authentication.application.SocialAuthService;
import com.netfoodz.store.authentication.application.exception.WebRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SecurityController {

	private PasswordAuthService passwordAuthService;
	private SocialAuthService socialAuthService;

	@Autowired
	public SecurityController(PasswordAuthService passwordAuthService, SocialAuthService socialAuthService) {
		this.passwordAuthService = passwordAuthService;
		this.socialAuthService = socialAuthService;
	}


	@PreAuthorize("hasRole('ROLE_ANONYMOUS')")
	@RequestMapping(value = "/login/basic", method = RequestMethod.POST)
	public ResponseEntity basicLogin(@RequestParam String email, @RequestParam String password) {

		boolean success = passwordAuthService.authenticateWithPassword(new EmailAddress(email), password);

		if (success)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}


	@PreAuthorize("hasRole('ROLE_ANONYMOUS')")
	@RequestMapping(value = "/login/{providerId}", method = RequestMethod.POST)
	public ResponseEntity socialLogin(@PathVariable String providerId,
									  @RequestParam String userId,
									  @RequestParam String accessToken) throws WebRequestException {

		boolean success;
		if("facebook".equals(providerId)) {
			success = socialAuthService.authenticateWithFacebook(userId, accessToken);
		} else {
			return ResponseEntity.badRequest().body("Provider ID is invalid");
		}

		if (success)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
