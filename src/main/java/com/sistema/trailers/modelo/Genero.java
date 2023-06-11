package com.sistema.trailers.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor //CONTRUCTOR PREDETERMINADO
@AllArgsConstructor //CONSTRUCTOR CON TODOS LOS CAMPOS DE CLASE
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero")
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String titulo;

    public Genero(Integer id) {
        this.id = id;
    }

    public Genero(String titulo) {
        this.titulo = titulo;
    }
}
