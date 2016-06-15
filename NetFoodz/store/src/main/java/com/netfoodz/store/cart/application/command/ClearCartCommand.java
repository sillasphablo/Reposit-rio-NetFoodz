package com.netfoodz.store.cart.application.command;

import com.netfoodz.common.domain.model.EmailAddress;
import lombok.Data;
import lombok.NonNull;

@Data
public class ClearCartCommand {

	@NonNull
	private EmailAddress customerEmail;
}
