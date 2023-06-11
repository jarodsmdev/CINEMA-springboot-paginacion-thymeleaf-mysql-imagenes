package com.sistema.trailers.repositorios;

import com.sistema.trailers.modelo.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer> {
}
