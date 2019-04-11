package com.demobank.service;

import java.util.List;

import com.demobank.entity.Account;
import com.demobank.entity.AccountTransaction;

public interface AccountTransactionService {

	void paymentTransfer(Account fromAccount, Account toAccount, String amount) throws Exception;

	List<AccountTransaction> viewTransactions();

	void saveTransaction(AccountTransaction accountTransaction);
}
