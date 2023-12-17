package com.example.projet_j2e_spring.entity;

import com.example.projet_j2e_spring.entity.interfaceDAO.UserDAO;
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

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


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if(!this.roles.contains(role)) {
            this.roles.add(role);
        }
    }

    public boolean hasRole(String role)
    {
        return !this.roles.stream().filter(r -> r.getName().equals(role)).collect(Collectors.toList()).isEmpty();
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
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
                ", role='" + roles + '\'' +
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

