package com.eycrojas.gestor_proyectos.services;

import com.eycrojas.gestor_proyectos.entities.Proyecto;
import com.eycrojas.gestor_proyectos.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface ProyectoService {

    Proyecto saveProyecto(Proyecto proyecto);

    Proyecto updateProyecto(Proyecto proyecto);

    List<Proyecto> getProyectos();

    Optional<Proyecto> getProyectoById(Long id);

    void deleteProyecto(Long id);

    List<Proyecto> getProyectosByUsuario(Usuario usuario);

}
