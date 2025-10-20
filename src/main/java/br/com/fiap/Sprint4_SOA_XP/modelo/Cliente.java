package br.com.fiap.Sprint4_SOA_XP.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "clientes", uniqueConstraints = {@UniqueConstraint(columnNames = "cpf")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean ativo = true;

    private String nome;
    private String cpf;
    private String nacionalidade;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;

    private Double rendaMensal;
    private Double patrimonioLiquido;
    private String profissao;
    private String experienciaInvestimentos;

    @Enumerated(EnumType.STRING)
    private PerfilInvestidor perfilInvestidor;

    @Enumerated(EnumType.STRING)
    private ObjetivoInvestimento objetivoInvestimento;

    @ElementCollection
    @CollectionTable(name = "cliente_preferencia_investimentos", joinColumns = @JoinColumn(name = "cliente_id"))
    @Column(name = "preferencia")
    private List<String> preferenciaInvestimentos;

    private String banco;
    private String agencia;
    private String numeroConta;
    private String tipoConta;
    private String titularConta;

    @Embedded
    private Endereco endereco;
}
