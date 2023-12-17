package com.example.projet_j2e_spring.entity.interfaceDAO;

import com.example.projet_j2e_spring.entity.Order;


import java.util.List;

public interface OrderDAO {

    public boolean updateOrder(int orderId, double ttc);
    public List<Order> getOrderUser(int userId);
    public List<Order> getAllOrder();
    public void deleteOrder(int userId);
}
