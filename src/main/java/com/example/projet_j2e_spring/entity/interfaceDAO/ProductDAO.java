package com.example.projet_j2e_spring.entity.interfaceDAO;

import com.example.projet_j2e_spring.entity.Product;

import java.util.List;

public interface ProductDAO {
    public boolean addProduct(Product product);
    public List<Product> getAllProduct();
    public Product getProduct(int id);
    public boolean updateEditProduct(Product p);
    public void updateQuantity(Product p);
    public boolean deleteProduct(int id);
    public List<Product> getProductByCat(String cat);
    public List<String> getAllCat();
}
