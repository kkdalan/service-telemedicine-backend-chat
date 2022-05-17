package com.fet.telemedicine.backend.chat.auth.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fet.telemedicine.backend.chat.auth.repository.AccountRepository;
import com.fet.telemedicine.backend.chat.auth.repository.po.AccountPo;
import com.fet.telemedicine.backend.chat.auth.service.AccountService;
import com.fet.telemedicine.backend.chat.config.RedisConfig;

@Service
@CacheConfig(cacheNames = "account")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

//    @Cacheable(key = "#username", unless = "#result == null")
    @Cacheable(keyGenerator = RedisConfig.CACHE_KEY_GENERATOR)
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

    @Cacheable(key = "#accountId", unless = "#result == null")
    @Override
    public Optional<AccountPo> getAccount(BigInteger accountId) {
	return accountRepository.findById(accountId);
    }

    @Override
    public void saveAccount(AccountPo account) {
	accountRepository.save(account);
    }

    @Cacheable(keyGenerator = RedisConfig.CACHE_KEY_GENERATOR)
    @Override
    public List<AccountPo> getAllAccounts() {
	return accountRepository.findAll();
    }

    /**
     * 執行時,將清除value = getAllUsers cache 【cacheNames = "userService"】 也可指定清除的key
     * 【@CacheEvict(value="abc")】
     */
    @CacheEvict(allEntries = true)
    @Override
    public void clearAllCache() {
    }

    @CacheEvict(key = "#accountId")
    @Override
    public void clear(BigInteger accountId) {
	// TODO Auto-generated method stub

    }

}
