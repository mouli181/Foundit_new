package com.SpringBoot.FoundIt_BackEnd.Service;

import com.SpringBoot.FoundIt_BackEnd.Model.Enum.Role;
import com.SpringBoot.FoundIt_BackEnd.Model.Login;
import com.SpringBoot.FoundIt_BackEnd.Model.Register;
import com.SpringBoot.FoundIt_BackEnd.Model.User;
import com.SpringBoot.FoundIt_BackEnd.Repository.UserRepository;
import com.SpringBoot.FoundIt_BackEnd.Security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<String> register(Register data) {
        try {
            boolean userNameExist = userRepository.existsByUserName(data.getUserName());
            if (!userNameExist) {
                User newUser = new User();
                newUser.setUserName(data.getUserName());
                newUser.setEmail(data.getEmail());
                newUser.setPassword(passwordEncoder.encode(data.getPassword()));
                newUser.setContact(data.getContact());
                newUser.setRole(Role.USER);

                userRepository.save(newUser);
                return ResponseEntity.status(HttpStatus.CREATED).body("Registered Successfully...!");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body("That UserName is taken, Try another...!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went wrong, please try again later....!");
        }
    }

    public ResponseEntity<?> login(Login data) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUserName(), data.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UserName and Password are invalid.");
        }
        Optional<User> userOpt = userRepository.findByUserName(data.getUserName());
        if (userOpt.isPresent()) {
            String token = jwtUtils.generateToken(new HashMap<>(), userOpt.get());
            if (token == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<Map<String, Object>> getUserService(String userName) {
        User userDetails = userRepository.findByUserDetails(userName);
        Map<String, Object> response = Map.of("userName", userDetails.getUsername(),
                "email", userDetails.getEmail(),
                "contact", userDetails.getContact());
        return ResponseEntity.ok(response);
    }

}

