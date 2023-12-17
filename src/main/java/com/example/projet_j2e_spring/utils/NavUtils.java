package com.example.projet_j2e_spring.utils;

import com.example.projet_j2e_spring.entity.User;
import com.example.projet_j2e_spring.repository.ProductRepository;
import org.springframework.ui.Model;

import java.util.List;

public class NavUtils {

    public static void loadNavUtils(Model model, User user, ProductRepository productRepository)
    {
        List<String> list_cat = productRepository.findDistinctCats();

        model.addAttribute("LOGIN_USER", user);
        model.addAttribute("list_cat", list_cat);
    }

}
