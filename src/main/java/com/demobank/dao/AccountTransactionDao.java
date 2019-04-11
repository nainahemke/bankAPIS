package com.demobank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demobank.entity.AccountTransaction;

public interface AccountTransactionDao extends JpaRepository<AccountTransaction, Long> {

}
