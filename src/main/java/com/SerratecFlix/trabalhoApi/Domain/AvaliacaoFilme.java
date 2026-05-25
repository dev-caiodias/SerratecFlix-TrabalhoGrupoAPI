package com.SerratecFlix.trabalhoApi.Domain;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class AvaliacaoFilme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;
}

//Classe temporaria pra testes