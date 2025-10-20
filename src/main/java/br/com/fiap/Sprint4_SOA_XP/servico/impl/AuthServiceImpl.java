package br.com.fiap.Sprint4_SOA_XP.servico.impl;

import br.com.fiap.Sprint4_SOA_XP.dto.AuthRequest;
import br.com.fiap.Sprint4_SOA_XP.dto.AuthResponse;
import br.com.fiap.Sprint4_SOA_XP.dto.RegisterRequest;
import br.com.fiap.Sprint4_SOA_XP.modelo.Role;
import br.com.fiap.Sprint4_SOA_XP.modelo.User;
import br.com.fiap.Sprint4_SOA_XP.repository.UserRepository;
import br.com.fiap.Sprint4_SOA_XP.servico.AuthService;
import br.com.fiap.Sprint4_SOA_XP.config.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public AuthResponse authenticate(AuthRequest request) {
        var userOpt = userRepository.findByUsername(request.username());
        var user = userOpt.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        // ✅ Corrigido: passando um Map vazio e o objeto User (UserDetails)
        String token = jwtService.generateToken(new HashMap<>(), user);
        return new AuthResponse(token);
    }

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username já existe");
        }

        var user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));

        try {
            user.setRole(Role.valueOf(request.role().toUpperCase()));
        } catch (IllegalArgumentException e) {
            user.setRole(Role.USER); // caso venha algo inválido
        }

        userRepository.save(user);
    }
}
