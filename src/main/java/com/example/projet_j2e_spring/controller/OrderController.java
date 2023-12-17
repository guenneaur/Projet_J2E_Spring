package com.example.projet_j2e_spring.controller;

import com.example.projet_j2e_spring.entity.Cart;
import com.example.projet_j2e_spring.entity.Order;
import com.example.projet_j2e_spring.entity.Product;
import com.example.projet_j2e_spring.entity.User;
import com.example.projet_j2e_spring.repository.CartRepository;
import com.example.projet_j2e_spring.repository.OrderRepository;
import com.example.projet_j2e_spring.repository.ProductRepository;
import com.example.projet_j2e_spring.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;

import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/order", name = "app_order")
public class OrderController {
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private CartRepository cartRepository;

    public OrderController(ProductRepository productRepository,
                           UserRepository userRepository,
                           OrderRepository orderRepository,
                           CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @PostMapping(value = "/add/{product_id}", name = "app_add_product")
    public String addProduct(Authentication authentication, @PathVariable Long product_id){

        User user = userRepository.findByEmail(authentication.getName());
        _addProduct(user, product_id, orderRepository, productRepository, cartRepository);
        return "redirect:/panier";
    }


    public static void _addProduct(User user, Long product_id, OrderRepository orderRepository, ProductRepository productRepository, CartRepository cartRepository)
    {
        Product product = productRepository.findById(product_id).get();
        Order order = orderRepository.findOrderByUserAndState(user, 1);
        if(order == null){
            order = new Order();
            order.setState(1);
            order.setPrice(0);
            order.setUser(user);
            order.setOrder_date(String.valueOf(LocalDateTime.now()));
            order = orderRepository.save(order);
        }

        boolean inc = false;

        for(Cart c : order.getCarts()) {
            if(c.getProduct().getId() == product.getId()) {
                c.incrementQuantity();
                c.setPrice(product.getPrice() * c.getQuantity());
                cartRepository.save(c);
                inc = true;
                break;
            }
        }

        if(!inc && product.getQuantity() > 0) {
            Cart cart = new Cart();
            cart.setProduct(product);
            cart.setQuantity(1);
            cart.setPrice(product.getPrice());
            cart.setImage(product.getImage());
            order.addCart(cart);
            cart = cartRepository.save(cart);

        }
        order = orderRepository.save(order);
    }
}
