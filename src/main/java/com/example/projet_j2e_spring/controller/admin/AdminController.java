package com.example.projet_j2e_spring.controller.admin;


import com.example.projet_j2e_spring.entity.Order;
import com.example.projet_j2e_spring.entity.Product;
import com.example.projet_j2e_spring.entity.User;
import com.example.projet_j2e_spring.repository.OrderRepository;
import com.example.projet_j2e_spring.repository.ProductRepository;
import com.example.projet_j2e_spring.repository.UserRepository;
import com.example.projet_j2e_spring.utils.NavUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public AdminController(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository){
        this.userRepository=userRepository;
        this.productRepository=productRepository;
        this.orderRepository=orderRepository;
    }

    @GetMapping(value={"/admin", "/admin/"}, name="app_admin_home")
    public String admin_home(Model model, Authentication authentication){
        User user = userRepository.findByEmail(authentication.getName());

        NavUtils.loadNavUtils(model, user, productRepository);
        return "/admin/home_admin";
    }
    //@TODO faire ça
    @GetMapping(value={"/admin/add_product"}, name="app_admin_add_product")
    public String admin_add_product(Model model){

        return "/admin/add_product_admin";
    }
    //@TODO faire ça
    @GetMapping(value={"/admin/edit_product"}, name="app_admin_edit_product")
    public String admin_edit_product(Model model){

        return "/admin/edit_products_admin";
    }

    @GetMapping(value={"/admin/products"}, name="app_admin_products")
    public String admin_products(Model model, Authentication authentication){
        User user = userRepository.findByEmail(authentication.getName());
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);

        NavUtils.loadNavUtils(model, user, productRepository);
        model.addAttribute("products", products);
        return "/admin/products_admin";
    }

    //@TODO faire le "Voir Détails"
    @GetMapping(value={"/admin/orders"}, name="app_admin_orders")
    public String admin_orders(Model model, Authentication authentication){
        User user = userRepository.findByEmail(authentication.getName());
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);

        NavUtils.loadNavUtils(model, user, productRepository);

        model.addAttribute("orders", orders);
        return "/admin/orders_admin";
    }

    @GetMapping(value={"/admin/users"}, name="app_admin_users")
    public String admin_users(Model model, Authentication authentication){
        User user = userRepository.findByEmail(authentication.getName());
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);

        NavUtils.loadNavUtils(model, user, productRepository);

        model.addAttribute("users", users);
        return "/admin/users_admin";
    }
}
