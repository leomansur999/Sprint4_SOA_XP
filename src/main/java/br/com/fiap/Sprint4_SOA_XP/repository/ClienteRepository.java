package br.com.fiap.Sprint4_SOA_XP.repository;

import br.com.fiap.Sprint4_SOA_XP.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpf(String cpf);
}
