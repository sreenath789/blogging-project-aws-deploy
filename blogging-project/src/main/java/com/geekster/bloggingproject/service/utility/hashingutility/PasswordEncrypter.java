package com.geekster.bloggingproject.service.utility.hashingutility;

import jakarta.xml.bind.DatatypeConverter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncrypter {
    public static String getPasswordEncrypter(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();
        return DatatypeConverter.printHexBinary(digested);
    }
}
