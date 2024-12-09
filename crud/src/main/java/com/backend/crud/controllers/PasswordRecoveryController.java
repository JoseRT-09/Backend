package com.backend.crud.controllers;

import com.backend.crud.services.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/password-recovery")
public class PasswordRecoveryController {

    @Autowired
    private PasswordRecoveryService passwordRecoveryService;

    @PostMapping("/request")
    public ResponseEntity<Map<String, Object>> requestPasswordRecovery(
            @RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        return ResponseEntity.ok(passwordRecoveryService.requestPasswordRecovery(email));
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyRecoveryCode(
            @RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String code = payload.get("code");
        return ResponseEntity.ok(passwordRecoveryService.verifyRecoveryCode(email, code));
    }

    @PostMapping("/reset")
    public ResponseEntity<Map<String, Object>> resetPassword(
            @RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String newPassword = payload.get("newPassword");
        String confirmPassword = payload.get("confirmPassword");
        return ResponseEntity.ok(passwordRecoveryService.resetPassword(
                email, newPassword, confirmPassword));
    }
}