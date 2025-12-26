package org.pepello.controller.Impl;

import org.pepello.dto.auth.LoginRequest;
import org.pepello.dto.auth.RegisterRequest;
import org.pepello.dto.user.UserCreateRequest;
import org.pepello.service.IAuthenticationService;
import org.pepello.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAuthenticationService authenticationService;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetails userDetails = authenticationService.authenticate(
                    loginRequest.email(),
                    loginRequest.password());

            UUID userId = userService.getByEmail(loginRequest.email()).getId();
            String token = authenticationService.generateToken(userDetails);

            return ResponseEntity.ok(Map.of("userId", userId, "token", token));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Kullanıcı bulunamadı"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Email veya şifre hatalı"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Giriş yapılamadı"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            // RegisterRequest'i UserCreateRequest'e dönüştür
            UserCreateRequest userCreateRequest = new UserCreateRequest(
                    registerRequest.firstName(),
                    registerRequest.lastName(),
                    registerRequest.email(),
                    registerRequest.password(),
                    null, // profilePicId
                    registerRequest.birthday());

            // Kullanıcı oluştur
            userService.create(userCreateRequest);

            // Otomatik login yap
            UserDetails userDetails = authenticationService.authenticate(
                    registerRequest.email(),
                    registerRequest.password());

            UUID userId = userService.getByEmail(registerRequest.email()).getId();
            String token = authenticationService.generateToken(userDetails);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("userId", userId, "token", token));
        } catch (RuntimeException e) {
            // Email zaten kullanımda veya validation hatası
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Kayıt yapılamadı"));
        }
    }
}
