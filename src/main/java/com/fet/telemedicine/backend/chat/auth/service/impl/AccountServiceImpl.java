package com.fet.telemedicine.backend.chat.auth.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.fet.telemedicine.backend.chat.auth.repository.AccountRepository;
import com.fet.telemedicine.backend.chat.auth.repository.entity.Account;
import com.fet.telemedicine.backend.chat.auth.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
	this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> getAccount(String username) {
	return accountRepository.findById(username);
    }

    @Override
    public void saveAccount(Account account) {
	accountRepository.save(account);
    }
}
