package com.fet.telemedicine.backend.chat.auth.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fet.telemedicine.backend.chat.auth.repository.po.AccountPo;
import java.lang.String;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountPo, BigInteger> {
    
    List<AccountPo> findByUsername(String username);
    
}
