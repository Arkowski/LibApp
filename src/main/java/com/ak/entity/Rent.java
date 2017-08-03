package com.ak.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "rents")
public class Rent extends BaseEntity {

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @JoinColumn (name = "book_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public Rent() {
    }

    public Rent(User user, Book book) {

        this.user = user;
        this.book = book;

        createdDate = new Date();

        status = Status.IN_PROGRESS;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
