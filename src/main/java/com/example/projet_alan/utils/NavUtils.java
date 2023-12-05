package com.example.projet_alan.utils;

import com.example.projet_alan.entity.User;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public class NavUtils {

    public static void loadNavUtils(Model model, User user, List<String> list_cat)
    {
            model.addAttribute("LOGIN_USER", user);
            model.addAttribute("list_cat", list_cat);
    }

    public static void loadNavUtils(Model model, User user)
    {
        loadNavUtils(model, user, new ArrayList<>());
    }

}
