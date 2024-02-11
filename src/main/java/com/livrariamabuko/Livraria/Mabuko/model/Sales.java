package com.livrariamabuko.Livraria.Mabuko.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "Sales")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sales implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBook", nullable = false)
    private Book book;

    private int amount;
    

  
    private String saleDate;

    @ManyToMany(mappedBy = "sales")
    private Set<Payment> Payment;

    

    public Set<Payment> getPayment() {
        return Payment;
    }

    public void setPayment(Set<Payment> payment) {
        Payment = payment;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    
   

    
    
    
    
    
}
