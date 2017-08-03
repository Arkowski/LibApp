package com.ak.service;

import com.ak.entity.Book;
import com.ak.entity.Rent;
import com.ak.entity.User;

import java.util.List;

/**
 * Created by Arkowski on 2017-07-08.
 */
public interface RentService {

    void createRent(User user, Book book);

    List<Rent> findAll();

    List<Rent> findByUserOrderByCreatedDateDesc(User user);
}


