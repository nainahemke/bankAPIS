package com.demobank.controller;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demobank.entity.Account;
import com.demobank.entity.AccountDetailsVO;
import com.demobank.exceptions.InsufficientAccountBalanceException;
import com.demobank.service.AccountService;
import com.demobank.service.AccountServiceImpl;

/**
 * 
 * @author Santosh Darvandar
 *
 */
@RestController
public class AccountController {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	AccountService accountService;

	/**
	 * return account information by reading account number
	 * 
	 * @param accountNumber
	 * @return
	 */
	@GetMapping("account/getAccount/{accountNumber}")
	public Account getAccountDetails(@PathVariable long accountNumber) {

		logger.info("Start getAccountDetails controller");

		Optional<Account> account = accountService.findByAccountNumber(accountNumber);

		logger.info("End getAccountDetails controller");

		return account.get();
	}

	/**
	 * Use to create fresh account
	 * 
	 * @param accountDetails
	 * @return
	 */
	@PostMapping("account/createAccount")
	public ResponseEntity<Object> createAccount(@RequestBody AccountDetailsVO accountDetails) {

		logger.info("Start createAccount controller");

		Account account = accountService.createAccount(accountDetails);

		logger.info("End createAccount controller");

		return ResponseEntity.ok()
				.body("Account created successfully with account number = " + account.getAccountNumber());
	}

	/**
	 * Use to update account type by for an account
	 * 
	 * @param accountType
	 * @param accountNumber
	 * @return
	 */
	@PutMapping("account/changeAccountType/{accountNumber}")
	public ResponseEntity<Object> changeAccountType(@RequestBody AccountDetailsVO accountDetails,
			@PathVariable long accountNumber) {

		logger.info("Start changeAccountType controller");

		Optional<Account> account = accountService.findByAccountNumber(accountNumber);
		if (!account.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Account not found with account number-" + accountNumber);
		}
		accountService.changeAccountType(account.get(), accountDetails.getAcccounType());

		logger.info("End changeAccountType controller");

		return ResponseEntity.ok().body("Account type changed successfully");
	}

	/**
	 * Use to deposit amount in an Account
	 * 
	 * @param amount
	 * @param accountNumber
	 * @return
	 */
	@PutMapping("account/deposit/{accountNumber}")
	public ResponseEntity<Object> deposit(@RequestBody AccountDetailsVO accountDetailsVO,
			@PathVariable long accountNumber) {

		logger.info("Start deposit controller");

		Optional<Account> account = accountService.findByAccountNumber(accountNumber);
		if (!account.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Account not found with account number-" + accountNumber);
		}
		accountService.deposit(account.get(), Double.parseDouble(accountDetailsVO.getAmount()));

		logger.info("End deposit controller");

		return ResponseEntity.ok().body("Amount deposited successfully");
	}

	/**
	 * Use to withdraw amount from an account
	 * 
	 * @param amount
	 * @param accountNumber
	 * @return
	 */
	@PutMapping("account/withdraw/{accountNumber}")
	public ResponseEntity<Object> withdraw(@RequestBody AccountDetailsVO accountDetailsVO,
			@PathVariable long accountNumber) {

		logger.info("Start withdraw controller");

		Optional<Account> account = accountService.findByAccountNumber(accountNumber);
		if (!account.isPresent()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Account not found with account number-" + accountNumber);
		}
		try {
			
			accountService.withdraw(account.get(), Double.parseDouble(accountDetailsVO.getAmount()));
			
		} catch (InsufficientAccountBalanceException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body("Insufficient account balance");
		}

		logger.info("End withdraw controller");

		return ResponseEntity.ok().body("Amount widthdrawn successfully");
	}
}
