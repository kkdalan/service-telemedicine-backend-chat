package com.fet.telemedicine.backend.chat.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptUtil {

    public static String hash(String plainTextPassword) {
	return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean isMatch(String plainTextPassword, String hashedPassword) {
	return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

    public static void main(String[] args) {
	System.out.println(BCryptUtil.hash("admin"));
	System.out.println(BCryptUtil.hash("alan"));
	System.out.println(BCryptUtil.hash("mike"));
	System.out.println(BCryptUtil.hash("sally"));

    }

}
