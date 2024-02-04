package com.livrariamabuko.Livraria.Mabuko.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "Promotion")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Promotion implements Serializable{
    private long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double discount;
    private String startDate;
    private String endDate;
    
     @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PromotionBook",
               joinColumns = @JoinColumn(name = "promotion_id"),
               inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books = new HashSet<>();
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    


}
