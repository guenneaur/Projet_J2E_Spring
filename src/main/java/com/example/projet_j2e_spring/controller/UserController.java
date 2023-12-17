package com.example.projet_j2e_spring.controller;

import com.example.projet_j2e_spring.entity.User;
import com.example.projet_j2e_spring.entity.Order;
import com.example.projet_j2e_spring.repository.OrderRepository;
import com.example.projet_j2e_spring.repository.ProductRepository;
import com.example.projet_j2e_spring.repository.UserRepository;
import com.example.projet_j2e_spring.utils.NavUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class UserController {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    public UserController(ProductRepository productRepository, UserRepository userRepository, OrderRepository orderRepository){
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping(value = {"/utilisateur", "/utilisateur/"}, name="app_user")
    public String user(Authentication authentication,
                       Model model){
        User user = userRepository.findByEmail(authentication.getName());
        NavUtils.loadNavUtils(model, user, productRepository);
        return "/user/home_user";
    }

    @GetMapping(value ="/utilisateur/infos", name="app_user_infos")
    public String userInfo(Authentication authentication,
                           Model model){
        User user = userRepository.findByEmail(authentication.getName());
        NavUtils.loadNavUtils(model, user, productRepository);
        return "/user/infos_user";
    }

    @PostMapping(value = {"/utilisateur/infos"}, name="app_user_infos")
    public String userInfosPost(Authentication authentication,
                                Model model,
                                @RequestParam(name="nom") String name,
                                @RequestParam(name="adresse") String adress,
                                @RequestParam(name="ville") String city,
                                @RequestParam(name="codePostal") int postCode,
                                @RequestParam(name="mail") String email,
                                @RequestParam(name="pwd") String password,
                                @RequestParam(name="pwd_verif") String password_conf){
        User user = userRepository.findByEmail(authentication.getName());
        NavUtils.loadNavUtils(model, user, productRepository);
        user.setName(name);
        user.setAdress(adress);
        user.setCity(city);
        user.setPostal(postCode);
        user.setEmail(email);
        if(!password.equals(password_conf)){
            model.addAttribute("msg_fail", "Les mots de passe ne correspondent pas, veuillez réessayer.");
            return "user/infos_user";
        }
        user.setPassword(password);
        userRepository.save(user);
        return "user/infos_user";
    }

    @GetMapping("/utilisateur/commandes")
    public String order(Model model, Authentication authentication)
    {
        User user = userRepository.findByEmail(authentication.getName());
        List<Order> orders = orderRepository.findAllByUserAndState(user, 0);

        NavUtils.loadNavUtils(model, user, productRepository);
        model.addAttribute("orders", orders);

        return "/user/orders_user";
    }

    //@TODO Trouver comment récupérer les items de l'order
    @GetMapping("/utilisateur/commandes/{order_id}")
    public String orderDetails(@PathVariable Long order_id, Model model, Authentication authentication)
    {
        User user = userRepository.findByEmail(authentication.getName());
        Order order = orderRepository.findById(order_id).get();

        NavUtils.loadNavUtils(model, user, productRepository);
        model.addAttribute("order", order);

        return "/user/order_details_user";
    }
}
