package com.mychatsocial.usermicroservice.auth;

import com.mychatsocial.usermicroservice.config.JwtService;
import com.mychatsocial.usermicroservice.user.Role;
import com.mychatsocial.usermicroservice.user.User;
import com.mychatsocial.usermicroservice.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .lastname(request.getEmail())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        System.out.println("llegado hasta aqui");
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        System.out.println("llegado hasta aqui");
        var token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }
}
