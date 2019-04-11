package com.demobank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demobank.entity.Account;

public interface AccountDao extends JpaRepository<Account, Long> {

}
