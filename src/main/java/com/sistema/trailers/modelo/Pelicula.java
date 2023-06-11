package com.sistema.trailers.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;


@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private  Integer id;

    @NotBlank
    private String titulo;

    @NotBlank
    private String sinopsis;

    @NotNull
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate fechaEstreno;

    @NotBlank
    private String youtubeTrailerId;

    private String rutaPortada;

    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "genero_pelicula",
            joinColumns = @JoinColumn(name = "id_pelicula"),
            inverseJoinColumns = @JoinColumn(name = "id_genero"))
    private List<Genero> generos;

    @Transient
    private MultipartFile portada;

    //CONSTRUCTOR CON TODOS LOS CAMPOS DE CLASE A EXCEPCIÓN DEL CAMPO id


    public Pelicula(String titulo, String sinopsis, LocalDate fechaEstreno, String youtubeTrailerId, String rutaPortada,
                    List<Genero> generos, MultipartFile portada) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.fechaEstreno = fechaEstreno;
        this.youtubeTrailerId = youtubeTrailerId;
        this.rutaPortada = rutaPortada;
        this.generos = generos;
        this.portada = portada;
    }
}

/*
* La anotación @JoinTable se utiliza en mapeos de relaciones Many-to-Many en JPA (Java Persistence API) para especificar la tabla de unión entre dos entidades.

Vamos a desglosar cada parte de la anotación:

    name = "genero_pelicula": Especifica el nombre de la tabla de unión que se creará en la base de datos. En este caso, la tabla de unión se llamará "genero_pelicula".

    joinColumns = @JoinColumn(name = "id_pelicula"): Indica la columna de la tabla de unión que se relacionará con la entidad actual (la entidad en la que se encuentra esta anotación). En este caso, la columna "id_pelicula" se usará para hacer referencia a la entidad "Pelicula".

    inverseJoinColumns = @JoinColumn(name = "id_genero"): Especifica la columna de la tabla de unión que se relacionará con la otra entidad en la relación Many-to-Many. En este caso, la columna "id_genero" se utilizará para hacer referencia a la entidad "Genero".

En resumen, esta anotación se utiliza para establecer una relación Many-to-Many entre dos entidades ("Pelicula" y "Genero"). La tabla de unión "genero_pelicula" se creará en la base de datos con dos columnas: "id_pelicula" y "id_genero". Estas columnas se utilizarán para establecer las relaciones entre las entidades, permitiendo que una película tenga varios géneros y que un género pueda estar asociado a varias películas.
*
* Tabla: genero_pelicula
+--------------+-------------+
| id_pelicula  | id_genero   |
+--------------+-------------+
|      1       |      1      |
|      1       |      2      |
|      2       |      2      |
|      3       |      1      |
+--------------+-------------+

* */