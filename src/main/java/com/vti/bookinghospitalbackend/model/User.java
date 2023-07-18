package com.vti.bookinghospitalbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    private String email;

    @CreatedDate
    private Date createdTime;

    @LastModifiedDate
    private Date modifiedDate;

    private boolean isActive;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    public User() {
        this.isActive = true;

    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public User(String userName, String fullName, String password,Date createdTime, boolean isActive, long parentID) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.createdTime = createdTime;
        this.isActive = isActive;
    }
    public User(String userName, String fullName, String password, long parentID, String email) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.isActive = true;
        this.email = email;
//        String email
    }
    public User(Long id, String userName, String fullName, String password, Date createdTime, boolean isActive, long parentID) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
       // this.email = email;
        this.createdTime = createdTime;
        this.isActive = isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}
