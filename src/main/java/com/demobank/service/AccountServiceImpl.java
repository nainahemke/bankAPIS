package com.demobank.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demobank.dao.AccountDao;
import com.demobank.entity.Account;
import com.demobank.entity.AccountDetailsVO;
import com.demobank.entity.AccountTransaction;
import com.demobank.exceptions.InsufficientAccountBalanceException;
import com.demobank.util.DateUtility;
/**
 * 
 * @author Santosh Darvandar
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	AccountDao accountDao;

	@Autowired
	AccountTransactionService accountTransactionService;

	@Override
	public Account createAccount(AccountDetailsVO accountDetails) {

		logger.info("Start createAccount Service");

		Account account = new Account();
		account.setAccountHolderFirstName(accountDetails.getAccountHolderFirstName());
		account.setAccountHolderLastName(accountDetails.getAccountHolderLastName());
		boolean validateDOB = DateUtility.validateInputDate(accountDetails.getAccountHolderDateOfBirth());
		if (validateDOB == true) {
			account.setAccountBalance(new BigDecimal(0.0));
			account.setAcccounType(accountDetails.getAcccounType());
			account.setAccountCreationDate(DateUtility.getCurrentDateInString());
			account.setAccountHolderDateOfBirth(accountDetails.getAccountHolderDateOfBirth());
		} else {
			logger.error("Invalid input date format");
		}
		logger.info("End createAccount Service");

		return accountDao.save(account);
	}

	@Override
	public void deposit(Account account, double amount) {

		logger.info("Start deposit Service");

		account.setAccountBalance(account.getAccountBalance().add(new BigDecimal(amount)));

		AccountTransaction accountTransaction = new AccountTransaction(DateUtility.getCurrentDateInString(),
				"Deposit to Account", "Account", "Finished", amount, account.getAccountBalance(), account);
		accountTransactionService.saveTransaction(accountTransaction);

		logger.info("End deposit Service");
	}

	@Override
	public void withdraw(Account account, double amount) throws InsufficientAccountBalanceException {

		logger.info("Start withdraw Service");
		
		BigDecimal accountBalance = account.getAccountBalance().subtract(new BigDecimal(amount));
		
		if(accountBalance.compareTo(BigDecimal.ZERO)<0) {
			throw new InsufficientAccountBalanceException();
		}

		account.setAccountBalance(accountBalance);

		AccountTransaction accountTransaction = new AccountTransaction(DateUtility.getCurrentDateInString(),
				"Withdraw from Account", "Account", "Finished", amount, account.getAccountBalance(), account);
		accountTransactionService.saveTransaction(accountTransaction);

		logger.info("End withdraw Service");
	}

	@Override
	public void changeAccountType(Account account, String accountType) {

		logger.info("Start changeAccountType Service");

		account.setAcccounType(accountType);
		accountDao.save(account);

		logger.info("End changeAccountType Service");
	}

	@Override
	public Optional<Account> findByAccountNumber(long accountNumber) {

		logger.info("Start findByAccountNumber Service");

		Optional<Account> account = accountDao.findById(accountNumber);

		logger.info("End findByAccountNumber Service");

		return account;
	}

}
