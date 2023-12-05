package com.example.projet_alan.entity.interfaceDAO;

import com.example.projet_alan.entity.Cart;

import java.util.List;

public interface CartDAO {
    public boolean addToCart(Cart c, int user_id);
    public int getActiveOrderId(int userId);
    public int createOrder(int userId);
    public boolean productExistsInCart(Cart c, int orderId);
//    void updateQuantityInCart(Cart c, int orderId);
    List<Cart> getCartItems(int user_id);
    boolean decrementQuantity(Cart cart, int user_id);
    boolean incrementQuantity();
    void updateQuantityInCart(int productId, int orderId, int newQuantity);
    int getQuantityInCart(int productId, int orderId);
    int getQuantityInProduct(int productId);
    void deleteCartItem(Cart cart, int orderId);
    List<Cart> getCart(int orderId);
    void deleteItem(int orderId);

}

