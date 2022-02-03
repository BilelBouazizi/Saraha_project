package com.bouaziz.saraha.controller;

import com.bouaziz.saraha.dto.AuthentificationRequest;
import com.bouaziz.saraha.dto.AuthentificationResponse;
import com.bouaziz.saraha.dto.UserDto;
import com.bouaziz.saraha.services.UserService;
import com.bouaziz.saraha.services.auth.AppUserDetailsService;
import com.bouaziz.saraha.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")//issm ressource à la fin 's':pluriels
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;


    @PostMapping("/register")
    public ResponseEntity<UserDto> save(@RequestBody UserDto user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthentificationResponse> login(@RequestBody AuthentificationRequest request) {
       //this code snippet does all the authentification process
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        final UserDetails userDetails=userDetailsService.loadUserByUsername(request.getEmail());
        final String jwt=jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(
                AuthentificationResponse.builder()
                        .token(jwt)
                        .build()
        );
    }
}
