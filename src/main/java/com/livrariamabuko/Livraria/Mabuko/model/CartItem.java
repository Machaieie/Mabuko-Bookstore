package com.livrariamabuko.Livraria.Mabuko.model;

public class CartItem {
    private Book book;
    private int quantity;
    private StockBook stockBook;

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public double calculateSubtotal() {
        return book.getPrice() * quantity;
    }

    public boolean checkStockAvailability() {
        return stockBook.getAmount() >= quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public StockBook getStockBook() {
        return stockBook;
    }

    public void setStockBook(StockBook stockBook) {
        this.stockBook = stockBook;
    }

    
}
