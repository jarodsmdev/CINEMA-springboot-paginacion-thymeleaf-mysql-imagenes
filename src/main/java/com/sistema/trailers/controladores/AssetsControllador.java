package com.sistema.trailers.controladores;

import com.sistema.trailers.Servicio.AlmacenServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assets")
public class AssetsControllador {

    @Autowired
    private AlmacenServicioImpl almacenServicio;

    @GetMapping("/{filename:.+}")
    public Resource obtenerRecurso(@PathVariable("filename") String filename){
        return almacenServicio.cargarComoRecurso(filename);
    }
}
