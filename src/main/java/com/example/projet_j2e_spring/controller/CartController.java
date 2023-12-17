package com.example.projet_j2e_spring.controller;

import com.example.projet_j2e_spring.entity.*;
import com.example.projet_j2e_spring.repository.CartRepository;
import com.example.projet_j2e_spring.repository.OrderRepository;
import com.example.projet_j2e_spring.repository.ProductRepository;
import com.example.projet_j2e_spring.repository.UserRepository;
import com.example.projet_j2e_spring.resource.UserResource;
import com.example.projet_j2e_spring.service.user.UserService;
import com.example.projet_j2e_spring.utils.NavUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/panier")
public class CartController {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;

    public CartController(
            ProductRepository productRepository,
            UserRepository userRepository,
            CartRepository cartRepository,
            OrderRepository orderRepository
    ) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }


    @GetMapping(value="",name="app_cart")
    public String index(Model model, Authentication authentication){

        User user = userRepository.findByEmail(authentication.getName());

        Order order = orderRepository.findOrderByUserAndState(user, 1);
        List<Cart> cartItems = new ArrayList<>();

        System.out.println(order);

        if (order != null) {
            cartItems = new ArrayList<>(order.getCarts());
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("order", order);
        NavUtils.loadNavUtils(model, user, productRepository);

        return "/cart";
    }

    @PostMapping(value = "/increment", name = "app_panier_inc_cart")
    public ResponseEntity<?> incrementQuantity(HttpServletRequest request, Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        String message = "";

        User user = userRepository.findByEmail(authentication.getName());

        try {
            Cart cart = cartRepository.findById(Long.parseLong(request.getParameter("cartId"))).get();

            if (user.getId() != cart.getOrder().getUser().getId())
                throw new RuntimeException("Cannot update other cart");

            if (cart.incrementQuantity()) {
                message = "Quantité incrémentée !";
                cartRepository.save(cart);
            } else {
                message = "minimum de quantité atteinte !";
            }

        } catch (Exception e) {
            e.printStackTrace();

            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/decrement", name = "app_panier_dec_cart")
    public ResponseEntity<?> decrementQuantity(HttpServletRequest request, Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        String message = "";

        User user = userRepository.findByEmail(authentication.getName());

        try {

            Cart cart = cartRepository.findById(Long.parseLong(request.getParameter("cartId"))).get();

            if (user.getId() != cart.getOrder().getUser().getId())
                throw new RuntimeException("Cannot update other cart");

            if (cart.decrementQuantity()) {
                message = "Quantité decrémenté !";
                if (cart.getQuantity() == 0)
                    cartRepository.delete(cart);
                else
                    cartRepository.save(cart);

            } else {
                message = "maximum de quantité atteinte !";
            }
        } catch (Exception e) {
            e.printStackTrace();

            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/updateCart", name = "app_panier_update_cart")
    public ResponseEntity<?> updateQuantity(HttpServletRequest request, Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        String message = "";

        User user = userRepository.findByEmail(authentication.getName());

        try {
            Cart cart = cartRepository.findById(Long.parseLong(request.getParameter("cartId"))).get();
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            if (user.getId() != cart.getOrder().getUser().getId())
                throw new RuntimeException("Cannot update other cart");

            if (quantity > 0 && quantity <= cart.getProduct().getQuantity()) {
                cart.setQuantity(quantity);
                cartRepository.save(cart);
                message = "Quantité mise à jour !";
            }
        } catch (Exception e) {
            e.printStackTrace();

            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        response.put("message", message);
        return ResponseEntity.ok(response);
    }


    @PostMapping(value = "/delete", name = "app_panier_delete_cart")
    public ResponseEntity<?> deleteQuantity(HttpServletRequest request, Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        String message = "";

        User user = userRepository.findByEmail(authentication.getName());

        try {

            Cart cart = cartRepository.findById(Long.parseLong(request.getParameter("cartId"))).get();

            if (user.getId() != cart.getOrder().getUser().getId())
                throw new RuntimeException("Cannot update other cart");
            message = "Produit supprimé du panier !";
            cartRepository.delete(cart);
        } catch (Exception e) {
            e.printStackTrace();

            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/add", name = "app_panier_add_cart")
    public ResponseEntity<?> addToCart(HttpServletRequest request, Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        String message = "";

        User user = userRepository.findByEmail(authentication.getName());

        try {
            OrderController._addProduct(user, Long.parseLong(request.getParameter("productId")), orderRepository, productRepository, cartRepository);
            message = "Produit ajouter du panier !";
        } catch (Exception e) {
            e.printStackTrace();

            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        response.put("message", message);
        return ResponseEntity.ok(response);
    }

//    @PostMapping(value = "/add/{product_id}", name = "app_add_product")
//    public String addProduct(Authentication authentication, Long product_id){
//        Product product = productRepository.findById(product_id).get();
//        User user = userRepository.findByEmail(authentication.getName());
//        Order order = orderRepository.findOrderByUserAndState(user, 1);
//        return "/page_product";
//    }
}

