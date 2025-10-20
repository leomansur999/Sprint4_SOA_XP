package br.com.fiap.Sprint4_SOA_XP.controller;

import br.com.fiap.Sprint4_SOA_XP.dto.ClienteDTO;
import br.com.fiap.Sprint4_SOA_XP.modelo.Cliente;
import br.com.fiap.Sprint4_SOA_XP.servico.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    public ClienteController(ClienteService clienteService) { this.clienteService = clienteService; }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody Cliente cliente) {
        var dto = clienteService.createCliente(cliente);
        URI uri = URI.create("/clientes/" + dto.id());
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listAll() {
        return ResponseEntity.ok(clienteService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.update(id, cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
