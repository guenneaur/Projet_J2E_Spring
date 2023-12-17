package com.example.projet_j2e_spring.controller;

import com.example.projet_j2e_spring.entity.Product;
import com.example.projet_j2e_spring.entity.User;
import com.example.projet_j2e_spring.repository.ProductRepository;
import com.example.projet_j2e_spring.repository.UserRepository;
import com.example.projet_j2e_spring.utils.NavUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public HomeController(ProductRepository productRepository, UserRepository userRepository){
        this.productRepository=productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping(value={"/"}, name="app_home")
    public String home(Model model, Authentication authentication){

        User user = userRepository.findByEmail(authentication.getName());

        //List<Product> var = this.productRepository.findDistinctByCat("cat");

        Page<Product> productPage = this.productRepository.findAll(PageRequest.of(0, 4));
        List<Product> products = productPage.getContent();

        //List<String> list_cat = var.stream().map(Product::getCat).toList();


        //model.addAttribute("list_cat", list_cat);
        model.addAttribute("products", products);
        NavUtils.loadNavUtils(model, user, productRepository);
        return "/accueil";
    }
}
