package com.fet.telemedicine.backend.chat.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.fet.telemedicine.backend.chat.model.Account;
import com.fet.telemedicine.backend.chat.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
	this.accountRepository = accountRepository;
    }

    public Optional<Account> getAccount(String username) {
	return accountRepository.findById(username);
    }

    public void saveAccount(Account account) {
	accountRepository.save(account);
    }
}
