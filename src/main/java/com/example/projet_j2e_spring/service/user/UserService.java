package com.example.projet_j2e_spring.service.user;

import com.example.projet_j2e_spring.entity.User;
import com.example.projet_j2e_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    public User getUser(Authentication authentication)
    {
        return userRepository.findByEmail(authentication.getName());
    }

}
