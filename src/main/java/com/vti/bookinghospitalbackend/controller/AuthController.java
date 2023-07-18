package com.vti.bookinghospitalbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*","http://localhost:8081",
        "http://localhost:3000","http://localhost:8080",
        "http://localhost:3001"},maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {



}
