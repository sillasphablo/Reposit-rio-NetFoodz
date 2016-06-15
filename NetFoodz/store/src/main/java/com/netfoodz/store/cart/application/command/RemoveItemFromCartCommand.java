package com.netfoodz.store.cart.application.command;

import com.netfoodz.common.domain.model.EmailAddress;
import com.netfoodz.common.domain.model.Identity;
import lombok.Data;
import lombok.NonNull;

@Data
public class RemoveItemFromCartCommand {

	@NonNull
	private Identity productId;
	@NonNull
	private EmailAddress customerEmail;
}
