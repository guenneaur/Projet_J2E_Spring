package com.example.projet_j2e_spring.entity;

import com.example.projet_j2e_spring.entity.interfaceDAO.OrderDAO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements OrderDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column
    private String order_date;

    @Column
    private double price;

    @Column
    private String method_payment;

    @OneToMany(mappedBy = "order")
    private Set<Cart> carts;

    private int state;

    public Order(int id, User user, String order_date) {
        this.id = id;
        this.user = user;
        this.order_date = order_date;
    }

    public Order() {
        this.carts = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public LocalDateTime getOrderDate()
    {
        return LocalDateTime.parse(order_date, DateTimeFormatter.ISO_DATE_TIME);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMethod_payment() {
        return method_payment;
    }

    public void setMethod_payment(String method_payment) {
        this.method_payment = method_payment;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user_id=" + user.getId() +
                ", order_date='" + order_date + '\'' +
                ", price=" + price +
                ", method_payment='" + method_payment + '\'' +
                '}';
    }

    @Override
    public boolean updateOrder(int orderId, double ttc) {
        return false;
    }

    @Override
    public List<Order> getOrderUser(int userId) {
        return null;
    }

    @Override
    public List<Order> getAllOrder() {
        return null;
    }

    @Override
    public void deleteOrder(int userId) {

    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public void addCart(Cart cart) {
        if(!this.carts.contains(cart)) {
            this.carts.add(cart);
            cart.setOrder(this);
        }
    }

    public void removeCart(Cart cart) {
        if(this.carts.contains(cart)) {
        }
    }
}
