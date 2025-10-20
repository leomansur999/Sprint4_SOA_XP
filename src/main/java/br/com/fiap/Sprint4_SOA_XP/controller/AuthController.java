package br.com.fiap.Sprint4_SOA_XP.controller;

import br.com.fiap.Sprint4_SOA_XP.config.JwtService;
import br.com.fiap.Sprint4_SOA_XP.dto.AuthRequest;
import br.com.fiap.Sprint4_SOA_XP.dto.RegisterRequest;
import br.com.fiap.Sprint4_SOA_XP.modelo.Role;
import br.com.fiap.Sprint4_SOA_XP.modelo.User;
import br.com.fiap.Sprint4_SOA_XP.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication; // ✅ IMPORT ESSENCIAL
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager; // ✅ AGORA RECONHECIDO
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // ✅ Registro de novo usuário
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Usuário já existe!"));
        }

        Role role;
        try {
            role = Role.valueOf(request.role().trim().toUpperCase(Locale.ROOT));
        } catch (Exception e) {
            role = Role.USER; // padrão
        }

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(role);

        userRepository.save(user);
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new TokenResponse(token));
    }


    // ✅ Login de usuário existente
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );

            UserDetails user = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(user);

            return ResponseEntity.ok().body(Map.of("token", token));

        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("erro", "Credenciais inválidas"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("erro", e.getMessage()));
        }
    }


    // ✅ DTOs internos
    record TokenResponse(String token) {}
    record ErrorResponse(String erro) {}
}
