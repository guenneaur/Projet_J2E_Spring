package com.example.projet_j2e_spring.entity;

import com.example.projet_j2e_spring.entity.interfaceDAO.ProductDAO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Product implements ProductDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private String cat;
    @Column
    private double price;
    @Column
    private int quantity;
    @Column
//    @ManyToOne(targetEntity = User.class)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private int user_id;
    @Column
    private String image;

    public Product(String name, String cat, double price, int quantity, int user_id) {
        this.name = name;
        this.cat = cat;
        this.price = price;
        this.quantity = quantity;
        this.user_id = user_id;
    }

    public Product() {

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cat='" + cat + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", user=" + user_id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean addProduct(Product product) {
        return false;
    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    @Override
    public Product getProduct(int id) {
        return null;
    }

    @Override
    public boolean updateEditProduct(Product p) {
        return false;
    }

    @Override
    public void updateQuantity(Product p) {

    }

    @Override
    public boolean deleteProduct(int id) {
        return false;
    }

    @Override
    public List<Product> getProductByCat(String cat) {
        return null;
    }

    @Override
    public List<String> getAllCat() {
        return null;
    }
}
