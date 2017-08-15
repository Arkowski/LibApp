package com.ak.dao;

import com.ak.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository <User, Long>{

    //zwraca dene użytkownika na podstawie loginu (maila)

    User findByEmail (String email);

}
