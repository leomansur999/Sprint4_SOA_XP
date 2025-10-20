package br.com.fiap.Sprint4_SOA_XP.dto;

import br.com.fiap.Sprint4_SOA_XP.modelo.Endereco;
import br.com.fiap.Sprint4_SOA_XP.modelo.ObjetivoInvestimento;
import br.com.fiap.Sprint4_SOA_XP.modelo.PerfilInvestidor;

import java.time.LocalDate;
import java.util.List;

public record ClienteDTO(
        Long id,
        String nome,
        String cpf,
        String nacionalidade,
        LocalDate dataNascimento,
        String telefone,
        String email,
        Double rendaMensal,
        Double patrimonioLiquido,
        String profissao,
        String experienciaInvestimentos,
        PerfilInvestidor perfilInvestidor,
        ObjetivoInvestimento objetivoInvestimento,
        List<String> preferenciaInvestimentos,
        String banco,
        String agencia,
        String numeroConta,
        String tipoConta,
        String titularConta,
        Endereco endereco
) {
    public ClienteDTO(br.com.fiap.Sprint4_SOA_XP.modelo.Cliente c) {
        this(c.getId(), c.getNome(), c.getCpf(), c.getNacionalidade(),
                c.getDataNascimento(), c.getTelefone(), c.getEmail(),
                c.getRendaMensal(), c.getPatrimonioLiquido(), c.getProfissao(),
                c.getExperienciaInvestimentos(), c.getPerfilInvestidor(),
                c.getObjetivoInvestimento(), c.getPreferenciaInvestimentos(),
                c.getBanco(), c.getAgencia(), c.getNumeroConta(), c.getTipoConta(),
                c.getTitularConta(), c.getEndereco());
    }
}
