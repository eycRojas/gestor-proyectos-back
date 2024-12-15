package com.eycrojas.gestor_proyectos.services;

import com.eycrojas.gestor_proyectos.entities.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaService {

    Tarea saveTarea(Tarea tarea);

    Tarea updateTarea(Tarea tarea);

    List<Tarea> getTareas();

    Optional<Tarea> getTareaById(Long id);

    void deleteTarea(Long id);

}
