package org.example.backend.sendEmail;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("${spring.application.api-prefix}/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @GetMapping("/sendNewAccountNVEmail")
    public String sendNewAccountNVEmail() {
        try {
            emailService.sendNewAccountNVEmail("thuongcm262@gmail.com", "thuongcm262@gmail.com", "a1234b5c");
            return "Email sent successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "Email not sent";
    }

    @GetMapping("/sendNewAccountKHEmail")
    public String sendNewAccountKHEmail() {
        try {
            emailService.sendNewAccountKHEmail("thuongcm262@gmail.com", "thuongcm262@gmail.com", "a1234b5c");
            return "Email sent successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "Email not sent";
    }

    @GetMapping("/sendPasswordEmail")
    public String sendPasswordEmail() {
        try {
            emailService.sendPasswordEmail("thuongcm262@gmail.com", "hahaha");
            return "Email sent successfully";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "Email not sent";
    }


}
