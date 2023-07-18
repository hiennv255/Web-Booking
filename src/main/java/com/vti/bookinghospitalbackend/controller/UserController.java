package com.vti.bookinghospitalbackend.controller;

import com.vti.bookinghospitalbackend.model.ResponseObject;
import com.vti.bookinghospitalbackend.model.Role;
import com.vti.bookinghospitalbackend.model.ERole;
import com.vti.bookinghospitalbackend.model.User;
import com.vti.bookinghospitalbackend.payload.request.SignupRequest;
import com.vti.bookinghospitalbackend.repository.RoleRepository;
import com.vti.bookinghospitalbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = {"*","http://localhost:8081",
        "http://localhost:3000","http://localhost:8080",
        "http://localhost:3001"},maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest)
        {
            Optional<User> optionalUser = null;
                optionalUser = userRepository.findByUserName(signUpRequest.getUsername());
            if (userRepository.existsByUserName(signUpRequest.getUsername()))
            {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(400,"User already exist ", ""));
            }

             // Create new user's account
            User user = new User();
            user.setEmail(signUpRequest.getUsername());
            user.setFullName(signUpRequest.getFullName());
            user.setUserName(signUpRequest.getUsername());
            user.setPassword(encoder.encode(signUpRequest.getPassword()));

            Set<Role> roles = new HashSet<>();
            // Set Role default is ROLE_USER
                Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found!"));
                roles.add(userRole);
            user.setRoles(roles);

            user.setCreatedTime(new Date());
            user.setModifiedDate(new Date());
            user.setActive(true);
             return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(200,"User registered successfully!", userRepository.save(user)));
        }
}
