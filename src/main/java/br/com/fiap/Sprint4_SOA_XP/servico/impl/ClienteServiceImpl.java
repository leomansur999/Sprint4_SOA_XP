package br.com.fiap.Sprint4_SOA_XP.servico.impl;

import br.com.fiap.Sprint4_SOA_XP.dto.ClienteDTO;
import br.com.fiap.Sprint4_SOA_XP.modelo.Cliente;
import br.com.fiap.Sprint4_SOA_XP.repository.ClienteRepository;
import br.com.fiap.Sprint4_SOA_XP.servico.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public ClienteDTO createCliente(Cliente cliente) {
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new RuntimeException("Cliente com CPF já existe");
        }
        Cliente saved = clienteRepository.save(cliente);
        return new ClienteDTO(saved);
    }

    @Override
    public ClienteDTO getById(Long id) {
        Cliente c = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new ClienteDTO(c);
    }

    @Override
    public List<ClienteDTO> listAll() {
        return clienteRepository.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClienteDTO update(Long id, Cliente cliente) {
        Cliente existing = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        // atualiza campos de forma segura (exemplo parcial)
        if (cliente.getNome() != null) existing.setNome(cliente.getNome());
        if (cliente.getEmail() != null) existing.setEmail(cliente.getEmail());
        if (cliente.getTelefone() != null) existing.setTelefone(cliente.getTelefone());
        if (cliente.getRendaMensal() != null) existing.setRendaMensal(cliente.getRendaMensal());
        existing = clienteRepository.save(existing);
        return new ClienteDTO(existing);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Cliente c = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        c.setAtivo(false); // soft delete
        clienteRepository.save(c);
    }
}
