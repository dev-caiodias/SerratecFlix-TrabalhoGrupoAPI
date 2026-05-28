package com.SerratecFlix.trabalhoApi.Domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_serie")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer temporadas;

    @Column(nullable = false)
    private Integer episodios;

    @Column(name = "data_lancamento", nullable = false)
    private LocalDate dataLancamento;

    @Builder.Default
    @Column(name = "nota_media")
    private Double notaMedia = 0.0;

    @ManyToMany
    @JoinTable(name = "serie_categoria", joinColumns = @JoinColumn(name = "serie_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AvaliacaoSerie> avaliacoes;

    @ManyToMany(mappedBy = "serie")
    @JsonIgnore
    private List<ListaFavoritos> listasFavoritos;
}