package com.fet.telemedicine.backend.chat.service.impl;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.fet.telemedicine.backend.chat.repository.AccountRepository;
import com.fet.telemedicine.backend.chat.repository.entity.AccountEntity;
import com.fet.telemedicine.backend.chat.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
	this.accountRepository = accountRepository;
    }

    @Override
    public Optional<AccountEntity> getAccount(String username) {
	return accountRepository.findById(username);
    }

    @Override
    public void saveAccount(AccountEntity account) {
	accountRepository.save(account);
    }
}
