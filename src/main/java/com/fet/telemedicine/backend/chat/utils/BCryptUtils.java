package com.fet.telemedicine.backend.chat.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptUtils {

    public static String hash(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean isMatch(String plainTextPassword, String hashedPassword) {
       return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
    
    public static void main(String[] args) {
	System.out.println(BCryptUtils.hash("0000"));
	System.out.println(BCryptUtils.hash("imalan"));
	System.out.println(BCryptUtils.hash("mike"));
    }
    
}
