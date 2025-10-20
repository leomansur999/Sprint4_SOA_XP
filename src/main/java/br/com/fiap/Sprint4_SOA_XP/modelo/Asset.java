package br.com.fiap.Sprint4_SOA_XP.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "assets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    private String classe; // renda fixa, renda variável, fundos, imobil.
    private String descricao;
    private String risco; // baixo, medio, alto
    private String liquidez; // alta, media, baixa
}
