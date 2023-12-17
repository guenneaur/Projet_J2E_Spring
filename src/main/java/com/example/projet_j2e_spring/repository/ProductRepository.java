package com.example.projet_j2e_spring.repository;

import com.example.projet_j2e_spring.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query("SELECT DISTINCT p.cat FROM Product p")
    List<String> findDistinctCats();

    Page<Product> findAll(Pageable pageable);

    List<Product> findAllByCat(String cat);
}
