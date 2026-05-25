package com.SerratecFlix.trabalhoApi.Domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.SerratecFlix.trabalhoApi.Domain.enums.ClassificacaoIndicativa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "filme")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descricao;

    @Column(nullable = false)
    private Integer duracao;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "classificacao_indicativa", nullable = false)
    private ClassificacaoIndicativa classificacaoIndicativa;

    @Column(name = "nota_media")
    private Double notaMedia;

    @ManyToMany
    @JoinTable(
        name = "filme_categoria",
        joinColumns = @JoinColumn(name = "filme_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    @Builder.Default
    private List<Categoria> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Builder.Default
    private List<AvaliacaoFilme> avaliacoes = new ArrayList<>();

    @ManyToMany(mappedBy = "filmes")
    @JsonIgnore
    @Builder.Default
    private List<ListaFavoritos> listasFavoritos = new ArrayList<>();
}