package com.demobank.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demobank.entity.Account;
import com.demobank.entity.AccountTransaction;
import com.demobank.entity.PaymentTransferVO;
import com.demobank.service.AccountService;
import com.demobank.service.AccountServiceImpl;
import com.demobank.service.AccountTransactionService;

@RestController
public class TransactionController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	AccountTransactionService accountTransactionService;

	@Autowired
	AccountService accountService;

	/**
	 * View all account transactions
	 * 
	 * @return
	 */
	@GetMapping("transaction/viewAllTransactions")
	public List<AccountTransaction> viewAllTransactions() {

		logger.info("Start viewAllTransactions controller");

		List<AccountTransaction> accountTransactions = accountTransactionService.viewTransactions();

		logger.info("Start viewAllTransactions controller");

		return accountTransactions;
	}

	/**
	 * Use to transfer amount from one account to another
	 * 
	 * @param fromAccountId
	 * @param toAccountId
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/transaction/paymentTransfer")
	public ResponseEntity<Object> paymentTransfer(@RequestBody PaymentTransferVO paymentTransferVO) throws Exception {

		logger.info("Start paymentTransfer controller");

		Optional<Account> fromAccount = accountService
				.findByAccountNumber(Long.valueOf(paymentTransferVO.getFromAccountId()));
		if (!fromAccount.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Account not found with account number-" + fromAccount);
		}
		Optional<Account> toAccount = accountService
				.findByAccountNumber(Long.valueOf(paymentTransferVO.getToAccountId()));
		if (!toAccount.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Account not found with account number-" + toAccount);
		}

		accountTransactionService.paymentTransfer(fromAccount.get(), toAccount.get(), paymentTransferVO.getAmount());

		logger.info("End paymentTransfer controller");

		return ResponseEntity.ok().body("Payment transfered successfully");
	}
}
