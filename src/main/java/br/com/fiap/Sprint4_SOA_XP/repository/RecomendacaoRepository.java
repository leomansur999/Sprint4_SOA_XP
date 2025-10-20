package br.com.fiap.Sprint4_SOA_XP.repository;

import br.com.fiap.Sprint4_SOA_XP.modelo.Recomendacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecomendacaoRepository extends JpaRepository<Recomendacao, Long> {
    List<Recomendacao> findByClienteId(Long clienteId);
}
