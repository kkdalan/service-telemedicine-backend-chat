package com.fet.telemedicine.backend.chat.auth.repository.po;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "account")
public class AccountPo implements Serializable{
    
    @Id
    @GeneratedValue
    private BigInteger id;
    private String username;
    private String password;

    public AccountPo() {
    }

    public AccountPo(String username, String password) {
	this.username = username;
	this.password = password;
    }

    public BigInteger getId() {
	return id;
    }

    public void setId(BigInteger id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
