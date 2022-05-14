package com.fet.telemedicine.backend.chat.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fet.telemedicine.backend.chat.auth.repository.po.AccountPo;

@Repository
public interface AccountRepository extends JpaRepository<AccountPo, String> {
}
