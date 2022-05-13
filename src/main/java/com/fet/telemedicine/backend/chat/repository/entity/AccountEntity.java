package com.fet.telemedicine.backend.chat.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "account")
public class AccountEntity {
    @Id
    private String username;
    private String password;

    public AccountEntity() {
    }

    public AccountEntity(String username, String password) {
	this.username = username;
	this.password = password;
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
