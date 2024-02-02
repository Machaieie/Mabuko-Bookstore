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
    

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private ZonedDateTime saleDate;
    

    private double total;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Client client;

    @ManyToMany(mappedBy = "sales")
    private Set<Payment> Payment = new HashSet<>();

    

    public Set<Payment> getPayment() {
        return Payment;
    }

    public void setPayment(Set<Payment> payment) {
        Payment = payment;
    }

    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public ZonedDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(ZonedDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    
    
    
}
