package com.fet.telemedicine.backend.chat.auth.service;

import java.util.Optional;

import com.fet.telemedicine.backend.chat.auth.repository.entity.Account;

public interface AccountService {

    Optional<Account> getAccount(String username);

    void saveAccount(Account account);

}