package com.backend.crud.services;

import com.backend.crud.entities.Users;
import com.backend.crud.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PasswordRecoveryService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Map<String, String> recoveryCodeMap = new ConcurrentHashMap<>();

    public Map<String, Object> requestPasswordRecovery(String email) {
        // Verificar si el usuario existe
        Users user = usersRepository.findByUsername(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Generar código de recuperación
        String recoveryCode = generateRecoveryCode();

        // Almacenar código temporalmente
        recoveryCodeMap.put(email, recoveryCode);

        // Enviar correo electrónico
        sendRecoveryEmail(user.getMail(), recoveryCode);

        // Preparar respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Código de recuperación enviado");
        return response;
    }

    public Map<String, Object> verifyRecoveryCode(String email, String code) {
        // Verificar código
        String storedCode = recoveryCodeMap.get(email);

        if (storedCode == null || !storedCode.equals(code)) {
            throw new RuntimeException("Código de recuperación inválido");
        }

        // Preparar respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Código verificado correctamente");
        return response;
    }

    public Map<String, Object> resetPassword(String email, String newPassword, String confirmPassword) {
        // Verificar que las contraseñas coincidan
        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("Las contraseñas no coinciden");
        }

        // Verificar que el código de recuperación haya sido verificado
        if (!recoveryCodeMap.containsKey(email)) {
            throw new RuntimeException("Proceso de recuperación no iniciado");
        }

        // Encontrar usuario
        Users user = usersRepository.findByUsername(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Codificar y actualizar contraseña
        user.setPassword(passwordEncoder.encode(newPassword));
        usersRepository.save(user);

        // Limpiar código de recuperación
        recoveryCodeMap.remove(email);

        // Preparar respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Contraseña restablecida exitosamente");
        return response;
    }

    private String generateRecoveryCode() {
        // Generar código de 6 dígitos
        return String.format("%06d", new Random().nextInt(999999));
    }

    private void sendRecoveryEmail(String to, String recoveryCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jumpscoreinc@gmail.com");
        message.setTo(to);
        message.setSubject("Código de Recuperación de Contraseña");
        message.setText("Su código de recuperación es: " + recoveryCode +
                "\n\nEste código expirará en 15 minutos.");

        mailSender.send(message);
    }
}