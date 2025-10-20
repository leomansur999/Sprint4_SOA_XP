package br.com.fiap.Sprint4_SOA_XP.controller;

import br.com.fiap.Sprint4_SOA_XP.dto.RecomendacaoDTO;
import br.com.fiap.Sprint4_SOA_XP.servico.RecomendacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recomendacoes")
public class RecomendacaoController {

    private final RecomendacaoService recomendacaoService;
    public RecomendacaoController(RecomendacaoService recomendacaoService) {
        this.recomendacaoService = recomendacaoService;
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<RecomendacaoDTO> recomendar(@PathVariable Long id) {
        return ResponseEntity.ok(recomendacaoService.recommendForClient(id));
    }
}
