package com.resotechsolutions.ecommerce.config;


import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class TokenGenerator {

    public String generateToken(String username) {

        String randomString = generateRandomString();
        String encryptedToken = encryptSHA256(randomString);
        if (encryptedToken.length() > 40) {
            encryptedToken = encryptedToken.substring(0, 40);
        }

        return encryptedToken;
    }

    private String generateRandomString() {
        String allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomStringBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 40; i++) {
            int index = random.nextInt(allowedCharacters.length());
            char randomChar = allowedCharacters.charAt(index);
            randomStringBuilder.append(randomChar);
        }

        return randomStringBuilder.toString();
    }

    private String encryptSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
