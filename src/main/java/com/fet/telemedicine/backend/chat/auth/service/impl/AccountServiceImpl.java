package com.fet.telemedicine.backend.chat.auth.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.fet.telemedicine.backend.chat.auth.repository.AccountRepository;
import com.fet.telemedicine.backend.chat.auth.repository.po.AccountPo;
import com.fet.telemedicine.backend.chat.auth.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
	this.accountRepository = accountRepository;
    }

    @Override
    public Optional<AccountPo> getAccount(String username) {
	List<AccountPo> accounts = accountRepository.findByUsername(username);
	if (CollectionUtils.isEmpty(accounts)) {
	    return Optional.empty();
	} else {
	    if (accounts.size() == 1) {
		return Optional.of(accounts.get(0));
	    } else {
		throw new RuntimeException("Duplicate users with username: " + username);
	    }
	}
    }

    @Override
    public void saveAccount(AccountPo account) {
	accountRepository.save(account);
    }
}
