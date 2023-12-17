package com.example.projet_j2e_spring.repository;

import com.example.projet_j2e_spring.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
