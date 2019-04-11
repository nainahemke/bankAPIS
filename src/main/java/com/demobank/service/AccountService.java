package com.demobank.service;

import java.util.Optional;

import com.demobank.entity.Account;
import com.demobank.entity.AccountDetailsVO;
import com.demobank.exceptions.InsufficientAccountBalanceException;

public interface AccountService {

	Account createAccount(AccountDetailsVO accountDetails);

	void deposit(Account account, double amount);

	void withdraw(Account account, double amount) throws InsufficientAccountBalanceException;

	void changeAccountType(Account account, String accountType);

	Optional<Account> findByAccountNumber(long accountNumber);
}
