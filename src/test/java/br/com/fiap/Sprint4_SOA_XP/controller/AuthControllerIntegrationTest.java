package br.com.fiap.Sprint4_SOA_XP.controller;

import br.com.fiap.Sprint4_SOA_XP.config.JwtService;
import br.com.fiap.Sprint4_SOA_XP.dto.AuthRequest;
import br.com.fiap.Sprint4_SOA_XP.dto.RegisterRequest;
import br.com.fiap.Sprint4_SOA_XP.modelo.Role;
import br.com.fiap.Sprint4_SOA_XP.modelo.User;
import br.com.fiap.Sprint4_SOA_XP.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtService jwtService;

    @Test
    void deveRegistrarUsuarioComSucesso() throws Exception {
        String uniqueUsername = "testeuser_" + System.currentTimeMillis();
        RegisterRequest request = new RegisterRequest(uniqueUsername, "123456", "User");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void deveRealizarLoginComSucesso() throws Exception {
        String uniqueUsername = "loginuser_" + System.currentTimeMillis();
        User user = new User(uniqueUsername, encoder.encode("123456"), Role.USER);
        userRepository.save(user);

        AuthRequest request = new AuthRequest(uniqueUsername, "123456");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void deveRetornarErroAoLogarComSenhaIncorreta() throws Exception {
        String uniqueUsername = "wrongpassuser_" + System.currentTimeMillis();
        User user = new User(uniqueUsername, encoder.encode("123456"), Role.USER);
        userRepository.save(user);

        AuthRequest request = new AuthRequest(uniqueUsername, "senhaerrada");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }
}
