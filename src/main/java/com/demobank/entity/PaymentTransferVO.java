package com.demobank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * @author Santosh Darvandar
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentTransferVO {

	String fromAccountId;
	String toAccountId;
	String amount;

	public String getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
