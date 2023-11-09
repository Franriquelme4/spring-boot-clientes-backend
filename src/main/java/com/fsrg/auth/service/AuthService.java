package com.fsrg.auth.service;

import com.fsrg.auth.dto.AuthResponse;
import com.fsrg.auth.dto.LoginRequest;
import com.fsrg.auth.dto.RegisterRequest;
import com.fsrg.jwt.JWTService;
import com.fsrg.model.dto.UserDTO;
import com.fsrg.model.entity.Role;
import com.fsrg.model.entity.User;
import com.fsrg.repository.UserRepository;
import com.fsrg.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
     private final IUserService iUserService;
     private final UserRepository userRepository;
     private final JWTService jwtService;
     private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .country(request.getCountry())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user)).build();
    }
}
