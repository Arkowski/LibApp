package com.ak.controller;

import com.ak.entity.Book;
import com.ak.entity.Rent;
import com.ak.entity.Role;
import com.ak.entity.User;
import com.ak.service.BookService;
import com.ak.service.RentService;
import com.ak.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;


    //stary sposób inincjalizacji loggera
    private final static Logger logger = Logger.getLogger(RentController.class.getName());

    @RequestMapping(value = "/rents", method = RequestMethod.GET)
    public String getRentsPage(Model model, Principal principal) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        List<Rent> rents;
        if (user.getRole() == Role.CUSTOMER) {
            rents = rentService.findByUserOrderByCreatedDateDesc(user);
        } else {
            rents = rentService.findAll();
        }
        model.addAttribute("rentsList", rents);

        return "rents";
    }

    //metoda dodawania wypozyczenia ksiazki o id= bookid, zalogowanego usera
    @RequestMapping(value = "/rent/book/{bookId}", method = RequestMethod.GET)
    public String createRent(@PathVariable Long bookId, Principal principal) {

        logger.log(Level.INFO, String.format("Wykonywanie metody createRent dla bookId =%d", bookId));
        System.out.println(String.format("Wykonywanie metody createRent dla bookId =%d", bookId));
        String email = principal.getName();
        User user = userService.findByEmail(email);
        Book book = bookService.findOne(bookId);
        rentService.createRent(user, book);

        return "redirect:/rents";
    }
}
