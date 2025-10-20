package br.com.fiap.Sprint4_SOA_XP.servico.impl;

import br.com.fiap.Sprint4_SOA_XP.dto.RecomendacaoDTO;
import br.com.fiap.Sprint4_SOA_XP.modelo.Cliente;
import br.com.fiap.Sprint4_SOA_XP.repository.ClienteRepository;
import br.com.fiap.Sprint4_SOA_XP.repository.RecomendacaoRepository;
import br.com.fiap.Sprint4_SOA_XP.servico.RecomendacaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecomendacaoServiceImpl implements RecomendacaoService {

    private final ClienteRepository clienteRepository;
    private final RecomendacaoRepository recomendacaoRepository;

    public RecomendacaoServiceImpl(ClienteRepository clienteRepository, RecomendacaoRepository recomendacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.recomendacaoRepository = recomendacaoRepository;
    }

    @Override
    @Transactional
    public RecomendacaoDTO recommendForClient(Long clienteId) {
        Cliente c = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        String texto;
        switch (c.getPerfilInvestidor()) {
            case CONSERVADOR -> {
                texto = "Carteira conservadora: 80% renda fixa (Tesouro Selic, CDBs), 20% fundos DI. Explicação: ...";
            }
            case MODERADO -> {
                texto = "Carteira moderada: 50% renda fixa, 30% fundos multimercado, 20% ações. Explicação: ...";
            }
            case AGRESSIVO -> {
                texto = "Carteira agressiva: 60% ações, 20% ETFs, 20% fundos de ações. Explicação: ...";
            }
            default -> texto = "Carteira padrão: 100% renda fixa até avaliação completa.";
        }

        var rec = new br.com.fiap.Sprint4_SOA_XP.modelo.Recomendacao();
        rec.setClienteId(clienteId);
        rec.setTexto(texto);
        rec = recomendacaoRepository.save(rec);
        return new RecomendacaoDTO(rec.getId(), rec.getClienteId(), rec.getTexto());
    }
}
