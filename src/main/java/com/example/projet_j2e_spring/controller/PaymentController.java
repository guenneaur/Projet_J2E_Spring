package com.example.projet_j2e_spring.controller;

import com.example.projet_j2e_spring.entity.Cart;
import com.example.projet_j2e_spring.entity.Order;
import com.example.projet_j2e_spring.entity.User;
import com.example.projet_j2e_spring.repository.OrderRepository;
import com.example.projet_j2e_spring.repository.ProductRepository;
import com.example.projet_j2e_spring.repository.UserRepository;
import com.example.projet_j2e_spring.utils.NavUtils;
import jakarta.persistence.EntityManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public PaymentController(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping(value={"/panier/validation"}, name="app_payment")
    public String payment(Model model, Authentication authentication){

        User user = userRepository.findByEmail(authentication.getName());

        NavUtils.loadNavUtils(model, user, productRepository);
        return "/paiement";
    }

    @PostMapping(value={"/panier/validation"}, name="app_payment_post")
    public String paymentValidate(Model model, Authentication authentication, EntityManager manager){

        User user = userRepository.findByEmail(authentication.getName());
        Order order = orderRepository.findOrderByUserAndState(user, 1);

        if(order == null)
            return "redirect:/";

        double price = 0.0;

        NavUtils.loadNavUtils(model, user, productRepository);

        try {
            for(Cart cart : order.getCarts()) {
                if(cart.getProduct().getQuantity() < cart.getQuantity())
                    throw new RuntimeException("product quantity");

                price += cart.getProduct().getPrice() * cart.getQuantity();
                cart.getProduct().setQuantity(cart.getProduct().getQuantity() - cart.getQuantity());
                productRepository.save(cart.getProduct());
            }
        } catch (RuntimeException e) {
            //manager.getTransaction().rollback();
            model.addAttribute("msg_failed", "Probleme server");
            return "/paiement";
        }

        order.setState(0);
        order.setMethod_payment("card");
        order.setPrice(price);
        orderRepository.save(order);


        model.addAttribute("msg_success", "Commande passé avec succes !");
        return "/paiement";
    }


}
