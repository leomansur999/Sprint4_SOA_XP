package br.com.fiap.Sprint4_SOA_XP.servico;

import br.com.fiap.Sprint4_SOA_XP.dto.AuthRequest;
import br.com.fiap.Sprint4_SOA_XP.dto.AuthResponse;
import br.com.fiap.Sprint4_SOA_XP.dto.RegisterRequest;

public interface AuthService {
    AuthResponse authenticate(AuthRequest request);
    void register(RegisterRequest request);
}
