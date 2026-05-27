package com.SerratecFlix.trabalhoApi.Domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Categoria")
    private Long id;

    @NotBlank(message = "Este campo deve ser preenchido.") 
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false, unique = true)
    private String nome;

    @Size(max = 100)
    @Column(name = "descricao", length = 100)
    private String descricao;

    @ManyToMany(mappedBy = "categorias")
    @JsonIgnore
    private List<Filme> filmes;

    @ManyToMany(mappedBy = "categorias")
    @JsonIgnore
    private List<Serie> series;

}
