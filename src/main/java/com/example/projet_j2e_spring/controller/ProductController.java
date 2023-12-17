package com.example.projet_j2e_spring.controller;

import com.example.projet_j2e_spring.entity.Product;
import com.example.projet_j2e_spring.entity.User;
import com.example.projet_j2e_spring.repository.ProductRepository;
import com.example.projet_j2e_spring.repository.UserRepository;
import com.example.projet_j2e_spring.utils.NavUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public ProductController(ProductRepository productRepository, UserRepository userRepository){
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }
    @GetMapping(value = "/page_product/{product_id}", name="page_product_app")
    public String productPage(@PathVariable Long product_id, Model model, Authentication authentication){;
        Product product = productRepository.findById(product_id).get();
        model.addAttribute("product", product);
        User user = userRepository.findByEmail(authentication.getName());
        NavUtils.loadNavUtils(model, user, productRepository);
        return "/page_product";
    }

    @GetMapping(value="/category/{category}", name = "app_category")
    public String categories(Model model, Authentication authentication, @PathVariable String category)
    {
        User user = userRepository.findByEmail(authentication.getName());

        List<Product> products = productRepository.findAllByCat(category);

        NavUtils.loadNavUtils(model, user, productRepository);
        model.addAttribute("products", products);
        model.addAttribute("category", category);

        return "/categories";
    }
}
