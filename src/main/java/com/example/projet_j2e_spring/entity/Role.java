package com.example.projet_j2e_spring.entity;

import com.example.projet_j2e_spring.entity.interfaceDAO.RoleDAO;
import jakarta.persistence.*;

@Table
@Entity
public class Role implements RoleDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
