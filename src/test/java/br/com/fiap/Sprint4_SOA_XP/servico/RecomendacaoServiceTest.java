package br.com.fiap.Sprint4_SOA_XP.servico;

import br.com.fiap.Sprint4_SOA_XP.modelo.Cliente;
import br.com.fiap.Sprint4_SOA_XP.modelo.PerfilInvestidor;
import br.com.fiap.Sprint4_SOA_XP.repository.ClienteRepository;
import br.com.fiap.Sprint4_SOA_XP.repository.RecomendacaoRepository;
import br.com.fiap.Sprint4_SOA_XP.servico.impl.RecomendacaoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class RecomendacaoServiceTest {

    @Test
    void shouldRecommendConservative() {
        var clienteRepo = Mockito.mock(ClienteRepository.class);
        var recRepo = Mockito.mock(RecomendacaoRepository.class);

        var service = new RecomendacaoServiceImpl(clienteRepo, recRepo);

        Cliente c = new Cliente();
        c.setId(1L);
        c.setPerfilInvestidor(PerfilInvestidor.CONSERVADOR);

        when(clienteRepo.findById(1L)).thenReturn(Optional.of(c));
        when(recRepo.save(Mockito.any())).thenAnswer(inv -> inv.getArgument(0));

        var dto = service.recommendForClient(1L);
        assertNotNull(dto);
        assertTrue(dto.texto().toLowerCase().contains("renda fixa"));
    }
}
