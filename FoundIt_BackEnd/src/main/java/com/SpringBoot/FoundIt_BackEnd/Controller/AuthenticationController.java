package com.SpringBoot.FoundIt_BackEnd.Controller;

import com.SpringBoot.FoundIt_BackEnd.Model.Login;
import com.SpringBoot.FoundIt_BackEnd.Model.Register;
import com.SpringBoot.FoundIt_BackEnd.Service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<String> register (@Valid @RequestBody Register data) {
        return service.register(data);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginApi ( @Valid @RequestBody Login data) {
        return service.login(data);
    }

    @GetMapping("user/getUserDetails")
    public ResponseEntity<Map<String, Object>> getUser () {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.getUserService(userName);
    }

}

