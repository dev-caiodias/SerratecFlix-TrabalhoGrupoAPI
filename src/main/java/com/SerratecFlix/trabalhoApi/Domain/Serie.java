package com.SerratecFlix.trabalhoApi.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Serie {

    @Id
    private Long id;

    private String titulo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}