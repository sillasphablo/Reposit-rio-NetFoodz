package com.netfoodz.store.authentication.infrastructure;

import com.netfoodz.common.domain.model.EmailAddress;
import com.netfoodz.store.authentication.domain.model.BasicUser;
import com.netfoodz.store.authentication.domain.repository.BasicUserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class IMBasicUserRepository implements BasicUserRepository {

	private Map<EmailAddress, BasicUser> map = new HashMap<>();

	@PostConstruct
	public void init() {
		EmailAddress email = new EmailAddress("user@netfoodz.com");
		BasicUser user = new BasicUser(email, "$2a$10$v35XUkr32oCWu8ZZ41/Eh.nfqqLq1vr.MJQOLTNoDk9bwe1SGQbPO");
		map.put(email, user);
	}

	@Override
	public BasicUser findByEmail(EmailAddress emailAddress) {
		return map.get(emailAddress);
	}
}
