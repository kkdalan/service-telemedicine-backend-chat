package com.fet.telemedicine.backend.chat.auth.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fet.telemedicine.backend.chat.auth.repository.AccountRepository;
import com.fet.telemedicine.backend.chat.auth.repository.po.AccountPo;
import com.fet.telemedicine.backend.chat.auth.service.AccountService;
import com.fet.telemedicine.backend.chat.config.RedisConfig;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

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
    
    @Cacheable(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:brand:'+#id", unless = "#result==null")
    @Override
    public Optional<AccountPo> getAccount(BigInteger accountId) {
	return accountRepository.findById(accountId);
    }

    @CacheEvict(value = RedisConfig.REDIS_KEY_DATABASE, key = "'pms:brand:'+#id")
    @Override
    public void saveAccount(AccountPo account) {
	accountRepository.save(account);
    }

    @Override
    public List<AccountPo> listAll() {
	return accountRepository.findAll();
    }

  
}
