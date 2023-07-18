package com.vti.bookinghospitalbackend.payload.response;


import com.vti.bookinghospitalbackend.model.Role;
import com.vti.bookinghospitalbackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String userName;
    private String fullName;
    private String password;
    private Date createdTime;
    private boolean isActive;
    private long parentID;


    public UserResponse(User user)
    {
        this.setId(user.getId());
        this.setActive(user.isActive());
        this.setUserName(user.getUserName());
        this.setCreatedTime(user.getCreatedTime());
        this.setFullName(user.getFullName());
        this.setRoles(user.getRoles());
    }
    private Set<Role> roles = new HashSet<>();
}
