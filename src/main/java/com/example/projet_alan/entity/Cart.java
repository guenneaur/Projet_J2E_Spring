package com.example.projet_alan.entity;

import com.example.projet_alan.entity.interfaceDAO.CartDAO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="cart")
public class Cart implements CartDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //@Column()
    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    //@Column()
    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column()
    private int quantity;
    @Column()
    private double price;
    @Column()
    private String image;



//    public Cart(int id, int order_id, int product_id, int quantity) {
//        this.id = id;
//        this.order_id = order_id;
//        this.product_id = product_id;
//        this.quantity = quantity;
//    }
//    public Cart(int product_id, int quantity) {
//        this.product_id = product_id;
//        this.quantity = quantity;
//    }
//
//    public Cart(int id, int productId, int quantity, double price, String image) {
//        this.id = id;
//        this.product_id = productId;
//        this.quantity = quantity;
//        this.price = price;
//        this.image = image;
//    }


    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", order_id=" + order.getId() +
                ", product_id=" + product.getId() +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean addToCart(Cart c, int user_id) {
        return false;
    }

    @Override
    public int getActiveOrderId(int userId) {
        return 0;
    }

    @Override
    public int createOrder(int userId) {
        return 0;
    }

    @Override
    public boolean productExistsInCart(Cart c, int orderId) {
        return false;
    }

    @Override
    public List<Cart> getCartItems(int user_id) {

        return null;
    }

    @Override
    public boolean decrementQuantity(Cart cart, int user_id) {
        return false;
    }

    /*@Override
    public boolean incrementQuantity(Cart cart, int user_id) {
        return false;
    }*/

    @Override
    public boolean incrementQuantity() {
        boolean f = false;

        int maxQuantity = this.product.getQuantity();
        int currentQuantity = this.quantity;

        if ( maxQuantity > currentQuantity) {
            this.quantity += 1;
            return true;
        }

        return false;
    }

    @Override
    public void updateQuantityInCart(int productId, int orderId, int newQuantity) {

    }

    @Override
    public int getQuantityInCart(int productId, int orderId) {
        return 0;
    }

    @Override
    public int getQuantityInProduct(int productId) {
        return 0;
    }

    @Override
    public void deleteCartItem(Cart cart, int orderId) {

    }

    @Override
    public List<Cart> getCart(int orderId) {
        return null;
    }

    @Override
    public void deleteItem(int orderId) {

    }
}
