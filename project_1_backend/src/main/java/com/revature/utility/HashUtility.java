package com.revature.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtility {
    public static byte[] createSalt(){
        byte[] bytes = new byte[20];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);
        return bytes;
    }

    public static String generateHashSaltPassword(String algorithm, String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();
        digest.update(salt);
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return new String(hash);
    }
}
