package com.SerratecFlix.trabalhoApi.Domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
}

//Classe temporaria pra testes