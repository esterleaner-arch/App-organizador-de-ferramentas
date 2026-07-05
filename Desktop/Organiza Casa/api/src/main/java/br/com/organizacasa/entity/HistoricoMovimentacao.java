package br.com.organizacasa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historico_movimentacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoMovimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false, length = 20)
    private String tipoMovimento;

    @Column(nullable = false)
    private Integer quantidadeAnterior;

    @Column(nullable = false)
    private Integer quantidadeNova;

    @Column(length = 300)
    private String observacao;

    @Builder.Default
    private LocalDateTime dataMovimento = LocalDateTime.now();

}
