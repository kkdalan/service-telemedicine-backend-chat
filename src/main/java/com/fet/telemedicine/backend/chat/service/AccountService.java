package com.fet.telemedicine.backend.chat.service;

import java.util.Optional;

import com.fet.telemedicine.backend.chat.repository.entity.AccountEntity;

public interface AccountService {

    Optional<AccountEntity> getAccount(String username);

    void saveAccount(AccountEntity account);

}