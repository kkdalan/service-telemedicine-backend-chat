package com.fet.telemedicine.backend.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fet.telemedicine.backend.chat.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}
