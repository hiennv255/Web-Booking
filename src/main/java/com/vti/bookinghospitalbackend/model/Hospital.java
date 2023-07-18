package com.vti.bookinghospitalbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class Hospital {
    @Id
    private Long id;

    @Transient
    public static final String SEQUENCE_NAME = "hospital_sequence";

    private String hospitalName;

    private String slogan;

    private String webSite;

    //Địa chỉ
    private String address;

    //Đường dây nóng(Sdt của bv)
    private String hotLine;

    //Giới thiệu
    private String about;

    //Hình ảnh
    private List<String> imageURL;

    //Bảng giá dịch vụ
    private String servicePriceList;

    //Chuyên khám..
    private List<String> specialized;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "hos_roles",
            joinColumns = @JoinColumn(name = "hos_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();





}
