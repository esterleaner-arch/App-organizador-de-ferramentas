package br.com.organizacasa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obrigatório")
    @Column(nullable = false)
    private String nome;

    private String icone;

    private String cor;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "categoria",
            cascade = CascadeType.ALL)
    private List<Item> itens = new ArrayList<>();

}