package com.example.projet_alan.entity.interfaceDAO;

import com.example.projet_alan.entity.Order;

import java.util.List;

public interface OrderDAO {

    public boolean updateOrder(int orderId, double ttc);
    public List<Order> getOrderUser(int userId);
    public List<Order> getAllOrder();
    public void deleteOrder(int userId);
}
