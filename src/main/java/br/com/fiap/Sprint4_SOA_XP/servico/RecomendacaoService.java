package br.com.fiap.Sprint4_SOA_XP.servico;

import br.com.fiap.Sprint4_SOA_XP.dto.RecomendacaoDTO;

public interface RecomendacaoService {
    RecomendacaoDTO recommendForClient(Long clienteId);
}
