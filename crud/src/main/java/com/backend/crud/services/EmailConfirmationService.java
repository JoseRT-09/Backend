package com.backend.crud.services;

import com.backend.crud.entities.Users;
import com.backend.crud.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmailConfirmationService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsersRepository usersRepository;

    private Map<String, String> confirmationTokenMap = new ConcurrentHashMap<>();

    public Map<String, Object> sendConfirmationEmail(Users user) {
        // Generate a unique confirmation token
        String confirmationToken = generateConfirmationToken();

        // Store the token temporarily
        confirmationTokenMap.put(user.getMail(), confirmationToken);

        // Send confirmation email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jumpscoreinc@gmail.com");
        message.setTo(user.getMail());
        message.setSubject("Confirma tu cuenta");
        message.setText("Para confirmar tu cuenta, utiliza el siguiente código: " + confirmationToken +
                "\n\nEste código expirará en 15 minutos.");

        mailSender.send(message);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Correo de confirmación enviado");
        return response;
    }

    public boolean verifyConfirmationToken(String email, String token) {
        String storedToken = confirmationTokenMap.get(email);

        if (storedToken == null || !storedToken.equals(token)) {
            return false;
        }

        // Remove the token after successful verification
        confirmationTokenMap.remove(email);
        return true;
    }

    private String generateConfirmationToken() {
        // Generate a 6-digit confirmation code
        return String.format("%06d", new Random().nextInt(999999));
    }
}