package com.ak.dao;

import com.ak.entity.Rent;
import com.ak.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentDao extends JpaRepository<Rent, Long> {

    //Zwraca listę wypożyczeń posortowaną po dacie dodania wypożyczenia (od najnowszych)

    List<Rent> findByUserOrderByCreatedDateDesc  (User user);

}
