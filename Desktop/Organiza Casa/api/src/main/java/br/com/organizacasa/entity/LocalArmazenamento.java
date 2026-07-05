package br.com.organizacasa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "local_armazenamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalArmazenamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do local é obrigatório")
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 255)
    private String descricao;

    private String comodo;

    private String armario;

    private String prateleira;

    private String caixa;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
    private List<Item> itens = new ArrayList<>();
}