package com.demobank.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demobank.dao.AccountDao;
import com.demobank.dao.AccountTransactionDao;
import com.demobank.entity.Account;
import com.demobank.entity.AccountTransaction;
import com.demobank.util.DateUtility;
/**
 * 
 * @author Santosh Darvandar
 *
 */
@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	AccountTransactionDao accountTransactionDao;

	@Autowired
	AccountDao accountDao;

	@Override
	public void paymentTransfer(Account fromAccount, Account toAccount, String amount) throws Exception {

		logger.info("Start paymentTransfer service");

		fromAccount.setAccountBalance(fromAccount.getAccountBalance().subtract(new BigDecimal(amount)));
		toAccount.setAccountBalance(toAccount.getAccountBalance().add(new BigDecimal(amount)));

		accountDao.save(fromAccount);
		accountDao.save(toAccount);

		AccountTransaction accountTransaction = new AccountTransaction(DateUtility.getCurrentDateInString(),
				"Payment transfer from Account Number " + fromAccount.getAccountNumber() + " to "
						+ toAccount.getAccountNumber(),
				"Account", "Finished", Double.parseDouble(amount), fromAccount.getAccountBalance(), fromAccount);
		accountTransactionDao.save(accountTransaction);

		logger.info("End paymentTransfer service");
	}

	@Override
	public List<AccountTransaction> viewTransactions() {

		logger.info("Start viewTransactions service");

		List<AccountTransaction> accountTransactions = accountTransactionDao.findAll();

		logger.info("End viewTransactions service");

		return accountTransactions;
	}

	@Override
	public void saveTransaction(AccountTransaction accountTransaction) {
		logger.info("Start saveTransaction service");

		accountTransactionDao.save(accountTransaction);

		logger.info("End saveTransaction service");
	}

}
