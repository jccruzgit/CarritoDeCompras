package com.shopcart.authservice.service;

import com.shopcart.authservice.dto.AuthRequestDTO;
import com.shopcart.authservice.dto.AuthResponseDTO;
import com.shopcart.authservice.entity.User;
import com.shopcart.authservice.repository.UserRepository;
import com.shopcart.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(AuthRequestDTO requestDTO) {
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setRoles(Set.of("ROLE_USER"));
        userRepository.save(user);
    }

    public AuthResponseDTO login(AuthRequestDTO requestDTO) {
        User user = userRepository.findByUsername(requestDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());
            return new AuthResponseDTO(token);
        }else{
            throw new RuntimeException("Incorrect password");
        }
    }
}
