package com.sistema.trailers.controladores;

import com.sistema.trailers.Servicio.AlmacenServicioImpl;
import com.sistema.trailers.modelo.Genero;
import com.sistema.trailers.modelo.Pelicula;
import com.sistema.trailers.repositorios.GeneroRepositorio;
import com.sistema.trailers.repositorios.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    @Autowired
    private GeneroRepositorio generoRepositorio;

    @Autowired
    private AlmacenServicioImpl servicio;

    @GetMapping("")
    public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "titulo", size = 5)Pageable pageable){
        Page<Pelicula> peliculas = peliculaRepositorio.findAll(pageable);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/index");
        modelAndView.addObject("peliculas", peliculas);

        return modelAndView;
    }

    @GetMapping("/peliculas/nuevo")
    public ModelAndView mostrarFormularioDeNuevaPelicula(){
        List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/nueva-pelicula");
        modelAndView.addObject("pelicula", new Pelicula());
        modelAndView.addObject("generos", generos);

        return modelAndView;

/*        return new ModelAndView("admin/nueva-pelicula")
                .addObject("pelicula", new Pelicula())
                .addObject("generos", generos);
*/
    }

    @PostMapping("/peliculas/nuevo")
    public ModelAndView registrarPelicula(@Validated Pelicula pelicula, BindingResult bindingResult){
        if(bindingResult.hasErrors() || pelicula.getPortada().isEmpty()){
            if(pelicula.getPortada().isEmpty()){
                bindingResult.rejectValue("portada", "MultipartNotEmpty");
            }

            List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/nueva-pelicula");
            modelAndView.addObject("pelicula", pelicula);
            modelAndView.addObject("generos", generos);

            return modelAndView;
        }

        String rutaPortada = servicio.almacenarArchivo(pelicula.getPortada());

        pelicula.setRutaPortada(rutaPortada);

        peliculaRepositorio.save(pelicula);

        return new ModelAndView("redirect:/admin");

    }
    @GetMapping("/peliculas/{id}/editar")
    public ModelAndView mostrarFormularioDeEditarPelicula(@PathVariable Integer id){
        Pelicula pelicula = peliculaRepositorio.getOne(id);
        List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/editar-pelicula");
        modelAndView.addObject("pelicula", pelicula);
        modelAndView.addObject("generos", generos);

        return modelAndView;
    }

    @PostMapping("/peliculas/{id}/editar")
    public ModelAndView actualizarPelicula(@PathVariable Integer id,
                                           @Validated Pelicula pelicula,
                                           BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/editar-pelicula");
            modelAndView.addObject("pelicula", pelicula);
            modelAndView.addObject("generos", generos);

            return modelAndView;
        }
        Pelicula peliculaDB = peliculaRepositorio.getOne(id);
        peliculaDB.setTitulo(pelicula.getTitulo());
        peliculaDB.setSinopsis(pelicula.getSinopsis());
        peliculaDB.setFechaEstreno(pelicula.getFechaEstreno());
        peliculaDB.setYoutubeTrailerId(pelicula.getYoutubeTrailerId());
        peliculaDB.setGeneros(pelicula.getGeneros());

        if(!pelicula.getPortada().isEmpty()){
            servicio.eliminarArchivo(peliculaDB.getRutaPortada());
            String rutaPortada = servicio.almacenarArchivo(pelicula.getPortada());
            peliculaDB.setRutaPortada(rutaPortada);
        }

        peliculaRepositorio.save(peliculaDB);

        return new ModelAndView("redirect:/admin");
    }

    @PostMapping("peliculas/{id}/eliminar")
    public String eliminarPelicula(@PathVariable Integer id){
        Pelicula pelicula = peliculaRepositorio.getOne(id);
        peliculaRepositorio.delete(pelicula);
        servicio.eliminarArchivo(pelicula.getRutaPortada());

        return "redirect:/admin";
    }
}
