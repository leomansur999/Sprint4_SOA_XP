package br.com.fiap.Sprint4_SOA_XP.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recomendacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recomendacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;
    @Lob
    private String texto;
}
