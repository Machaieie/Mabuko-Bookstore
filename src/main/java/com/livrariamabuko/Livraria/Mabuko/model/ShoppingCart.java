package com.livrariamabuko.Livraria.Mabuko.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> cartItems = new ArrayList<>();

    public void addItem(long bookId, int quantity) {
        cartItems.add(new CartItem(bookId, quantity));
    }

    public double calculateTotal() {
        double total = 0.0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.calculateSubtotal();
        }
        return total;
    }

    public boolean checkStockAvailability() {
        for (CartItem cartItem : cartItems) {
            if (!cartItem.checkStockAvailability()) {
                return false;
            }
        }
        return true;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    
  
}


    

