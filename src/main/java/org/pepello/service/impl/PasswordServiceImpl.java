package org.pepello.service.impl;

import org.pepello.service.IPasswordService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements IPasswordService {
    private final BCryptPasswordEncoder encoder;

    public PasswordServiceImpl() {
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String hash(String rawPassword) {
        //TODO: exception mimarisi yap ve hata fırlar
        if (rawPassword == null)
            return null;
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean verify(String rawPassword, String hashedPassword) {
        if (rawPassword == null || hashedPassword == null)
            return false;
        return encoder.matches(rawPassword, hashedPassword);
    }
}
