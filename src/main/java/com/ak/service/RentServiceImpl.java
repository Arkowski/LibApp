package com.ak.service;


import com.ak.dao.BookDao;
import com.ak.dao.RentDao;
import com.ak.entity.Book;
import com.ak.entity.Rent;
import com.ak.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private RentDao rentDao;

    @Override
    public void createRent(User user, Book book) {
        Rent rent = new Rent(user, book);
        rentDao.save(rent);
        book.decrementAvailable();
        bookDao.save(book);
    }

    @Override
    public List<Rent> findAll() {
        return rentDao.findAll();
    }

    @Override
    public List<Rent> findByUserOrderByCreatedDateDesc(User user) {
        return rentDao.findByUserOrderByCreatedDateDesc(user);
    }
}
