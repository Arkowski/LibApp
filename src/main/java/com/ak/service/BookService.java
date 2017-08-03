package com.ak.service;

import com.ak.entity.Book;

import java.util.List;


public interface BookService{

    void save(Book book);

    List<Book> findAll();

    void delete(Long id);

    Book findOne(Long id);
}
