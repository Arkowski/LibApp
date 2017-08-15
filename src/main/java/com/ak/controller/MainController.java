package com.ak.controller;


import com.ak.config.SecurityConfig;
import com.ak.entity.User;
import com.ak.service.EmailService;
import com.ak.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class MainController {

    @Autowired
     private UserService userService;

    @Autowired
    private EmailService emailService;



    @RequestMapping (value = "/", method = RequestMethod.GET)
    public String getMainPage() {
        return "main";
    }

    @RequestMapping (value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping (value = "/register", method = RequestMethod.GET)
    public String getRegisterPage() {
        return "register";
    }
    @RequestMapping (value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User model) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(SecurityConfig.PASSWORD_STRENGHT);
            String encodedPassword = encoder.encode(model.getPassword());
            model.setPassword(encodedPassword);
            userService.save(model);
        }
        catch (Exception ex) {
            return "redirect:/register";
        }
        emailService.sendEmail(
                "biblioteka@onet.pl",
                model.getEmail(),
                "Welcome to our Library!",
                "Registration is now complete, you can now log in to our page!");

        return "redirect:/login";
    }
}
