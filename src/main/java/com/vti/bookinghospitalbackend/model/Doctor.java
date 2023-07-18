package com.vti.bookinghospitalbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Doctor{

    @Id
    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "doctor_sequence";

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String password;

    private String email;

    private String avatar;

    //Chuyên khoa
    private String specialty;

    //Chức vụ
    private Position positionId;

    //Nơi công tác
    private String workPlace;

    private String about;

    private String address;

    private List<String> imageURL;

    private String studyProcess;

    private String experience;

    private boolean isActive;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "doctor_roles",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
