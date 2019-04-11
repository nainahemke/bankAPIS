package com.demobank.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * @author Santosh Darvandar
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountNumber;
	private String accountHolderFirstName;
	private String accountHolderLastName;
	private String accountHolderDateOfBirth;
	private String acccounType;
	private BigDecimal accountBalance;
	private String accountCreationDate;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<AccountTransaction> acountTransactionList;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

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

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountCreationDate() {
		return accountCreationDate;
	}

	public void setAccountCreationDate(String accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}

	public List<AccountTransaction> getAcountTransactionList() {
		return acountTransactionList;
	}

	public void setAcountTransactionList(List<AccountTransaction> acountTransactionList) {
		this.acountTransactionList = acountTransactionList;
	}
}
