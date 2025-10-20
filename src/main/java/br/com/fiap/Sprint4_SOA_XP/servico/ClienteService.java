package br.com.fiap.Sprint4_SOA_XP.servico;

import br.com.fiap.Sprint4_SOA_XP.dto.ClienteDTO;
import br.com.fiap.Sprint4_SOA_XP.modelo.Cliente;

import java.util.List;

public interface ClienteService {
    ClienteDTO createCliente(Cliente cliente);
    ClienteDTO getById(Long id);
    List<ClienteDTO> listAll();
    ClienteDTO update(Long id, Cliente cliente);
    void delete(Long id);
}
