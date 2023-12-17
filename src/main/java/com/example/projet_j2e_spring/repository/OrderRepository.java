package com.example.projet_j2e_spring.repository;

import com.example.projet_j2e_spring.entity.Order;
import com.example.projet_j2e_spring.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    Order findOrderByUserAndState(User user, int state);

    List<Order> findAllByUserAndState(User user, int state);
}
