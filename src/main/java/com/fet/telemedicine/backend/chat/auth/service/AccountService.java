package com.fet.telemedicine.backend.chat.auth.service;

import java.util.Optional;

import com.fet.telemedicine.backend.chat.auth.repository.po.AccountPo;

public interface AccountService {

    Optional<AccountPo> getAccount(String username);

    void saveAccount(AccountPo account);

}