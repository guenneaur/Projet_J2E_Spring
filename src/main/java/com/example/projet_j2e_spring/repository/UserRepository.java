package com.example.projet_j2e_spring.repository;

import com.example.projet_j2e_spring.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByEmail(String email);

}
