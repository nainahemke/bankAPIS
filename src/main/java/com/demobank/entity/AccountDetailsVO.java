package com.demobank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * @author Santosh Darvandar
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetailsVO {

	private String accountHolderFirstName;
	private String accountHolderLastName;
	private String accountHolderDateOfBirth;
	private String acccounType;
	private String amount;

	public String getAccountHolderFirstName() {
		return accountHolderFirstName;
	}

	public void setAccountHolderFirstName(String accountHolderFirstName) {
		this.accountHolderFirstName = accountHolderFirstName;
	}

	public String getAccountHolderLastName() {
		return accountHolderLastName;
	}

	public void setAccountHolderLastName(String accountHolderLastName) {
		this.accountHolderLastName = accountHolderLastName;
	}

	public String getAccountHolderDateOfBirth() {
		return accountHolderDateOfBirth;
	}

	public void setAccountHolderDateOfBirth(String accountHolderDateOfBirth) {
		this.accountHolderDateOfBirth = accountHolderDateOfBirth;
	}

	public String getAcccounType() {
		return acccounType;
	}

	public void setAcccounType(String acccounType) {
		this.acccounType = acccounType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
