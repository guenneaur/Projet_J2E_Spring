package com.example.projet_alan.entity;

import com.example.projet_alan.entity.interfaceDAO.UserDAO;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class User implements UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private String city;
    @Column
    private int postal;
    @Column
    private String adress;
    @Column
    private String role;

    public User(){
        super();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public int getPostal() {
        return postal;
    }

    public String getAdress() {
        return adress;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", postal=" + postal +
                ", adress='" + adress + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean userRegister(User user) {
        return false;
    }

    @Override
    public boolean checkUser(String email) {
        return false;
    }

    @Override
    public User login(String email, String pwd) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public String doHashPassword(String pwd) {
        return null;
    }

    @Override
    public boolean updateEditUser(User user) {
        return false;
    }

    @Override
    public boolean updateEditUserRole(User user) {
        return false;
    }

    @Override
    public boolean updateEditUserMail(User user) {
        return false;
    }

    @Override
    public boolean updateEditUserPwd(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }
}

