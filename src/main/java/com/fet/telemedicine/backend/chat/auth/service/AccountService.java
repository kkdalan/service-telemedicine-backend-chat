package com.fet.telemedicine.backend.chat.auth.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.fet.telemedicine.backend.chat.auth.repository.po.AccountPo;

public interface AccountService {

    Optional<AccountPo> getAccount(String username);
    
    Optional<AccountPo> getAccount(BigInteger accountId);

    void saveAccount(AccountPo account);
    
    List<AccountPo> listAll();
    
}