package com.example.projet_j2e_spring.controller;

import com.example.projet_j2e_spring.entity.User;
import com.example.projet_j2e_spring.repository.RoleRepository;
import com.example.projet_j2e_spring.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public AuthController(UserRepository userRepository, RoleRepository roleRepository)
    {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping(value={"/login"}, name="app_login")
    public String login(){
        return "/login";
    }

    @GetMapping(value={"/register"}, name="app_register")
    public String admin_home(){

        return "/register";
    }

    @RequestMapping("/success")
    public String success(Authentication authentication) {
        System.out.println(authentication);

        return "accueil";
    }

    @PostMapping(value = {"/register"}, name = "app_register_post")
    public String register(HttpServletRequest servletRequest,
                           @RequestParam(name="name") String name,
                           @RequestParam(name="email") String email,
                           @RequestParam(name="password") String password) throws ServletException {

        if(userRepository.findByEmail(email) != null){
            return "/register";
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.addRole(roleRepository.findById(1L).get());
        userRepository.save(user);
        servletRequest.login(user.getEmail(), user.getPassword());
        return "redirect:/";

    }

}
