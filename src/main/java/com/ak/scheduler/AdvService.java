package com.ak.scheduler;

import com.ak.config.AppConfig;
import com.ak.entity.User;
import com.ak.service.EmailService;
import com.ak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdvService {


    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @Scheduled(fixedDelay = 3600000)
    public void sendAdv() {
        List<User> users = userService.findAll();

        if (users == null) {
            return;
        }
        for (User user : users) {
            emailService.sendEmail(
                    AppConfig.EMAIL_FROM,
                    user.getEmail(),
                    "Reklama",
                    "NAJLEPSZA BIBLIOTEKA W KRAJU TYLKO U NAS!!!!"
            );
        }
    }

}
