package org.pepello.service;

public interface IPasswordService {
    String hash(String rawPassword);

    boolean verify(String rawPassword, String hashedPassword);
}
