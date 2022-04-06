package com.revature.utility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class HashUtilityTest {


    @Test
    public void test_hashedPassword() throws NoSuchAlgorithmException {
        String password = "HelloWorld";
        byte[] salt = HashUtility.createSalt();
        String hashedPassword = HashUtility.generateHashSaltPassword("SHA-512",password,salt);

        Assertions.assertEquals(hashedPassword,HashUtility.generateHashSaltPassword("SHA-512",password,salt));
    }

    @Test
    public void test_createSalt() throws NoSuchAlgorithmException {

        //Salt is generate using SecureRandom class to ensure that hashed passwords are differnt
        //even if the password is the same
        byte[] salt1 = HashUtility.createSalt();
        byte[] salt2 = HashUtility.createSalt();
        byte[] salt3 = HashUtility.createSalt();
        byte[] salt4 = HashUtility.createSalt();

        String password= "HelloWorld";

        String hashedPassword1 = HashUtility.generateHashSaltPassword("SHA-512",password,salt1);
        String hashedPassword2 = HashUtility.generateHashSaltPassword("SHA-512",password,salt2);
        String hashedPassword3 = HashUtility.generateHashSaltPassword("SHA-512",password,salt3);
        String hashedPassword4 = HashUtility.generateHashSaltPassword("SHA-512",password,salt4);

        Assertions.assertNotEquals(hashedPassword1,hashedPassword2);
        Assertions.assertNotEquals(hashedPassword1,hashedPassword3);
        Assertions.assertNotEquals(hashedPassword1,hashedPassword4);
        Assertions.assertNotEquals(hashedPassword2,hashedPassword3);
        Assertions.assertNotEquals(hashedPassword2,hashedPassword4);
        Assertions.assertNotEquals(hashedPassword3,hashedPassword4);
    }


}
