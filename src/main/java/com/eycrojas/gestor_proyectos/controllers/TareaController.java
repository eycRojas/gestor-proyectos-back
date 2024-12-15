package com.eycrojas.gestor_proyectos.controllers;

import com.eycrojas.gestor_proyectos.dtos.TareaDTO;
import com.eycrojas.gestor_proyectos.entities.Proyecto;
import com.eycrojas.gestor_proyectos.entities.Tarea;
import com.eycrojas.gestor_proyectos.entities.Usuario;
import com.eycrojas.gestor_proyectos.services.ProyectoServiceImpl;
import com.eycrojas.gestor_proyectos.services.TareaServiceImpl;
import com.eycrojas.gestor_proyectos.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarea")
@CrossOrigin(origins = "https://gestor-proyectos-front.web.app/")
public class TareaController {

    @Autowired
    TareaServiceImpl tareaServiceImpl;

    @Autowired
    ProyectoServiceImpl proyectoServiceImpl;

    @Autowired
    UsuarioServiceImpl usuarioServiceImpl;

    @PostMapping("/{proyectoId}/{usuarioId}")
    public ResponseEntity<TareaDTO> saveTarea(@PathVariable Long proyectoId, @PathVariable Long usuarioId, @RequestBody Tarea tarea) {
        try {
            Proyecto proyecto = proyectoServiceImpl.getProyectoById(proyectoId).orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + proyectoId));
            Usuario usuario = usuarioServiceImpl.getUsuarioById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

            tarea.setProyecto(proyecto);
            tarea.setUsuario(usuario);

            Tarea savedTarea = tareaServiceImpl.saveTarea(tarea);

            TareaDTO tareaDTO = tareaServiceImpl.convertToDTO(savedTarea);

            return new ResponseEntity<>(tareaDTO, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{proyectoId}/{usuarioId}")
    public ResponseEntity<TareaDTO> updateTarea(@PathVariable long proyectoId, @PathVariable long usuarioId, @RequestBody Tarea tarea) {
        try {

            Proyecto proyecto = proyectoServiceImpl.getProyectoById(proyectoId).orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + proyectoId));
            Usuario usuario = usuarioServiceImpl.getUsuarioById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

            tarea.setProyecto(proyecto);
            tarea.setUsuario(usuario);

            Tarea savedTarea = tareaServiceImpl.updateTarea(tarea);

            TareaDTO tareaDTO = tareaServiceImpl.convertToDTO(savedTarea);

            return new ResponseEntity<>(tareaDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<TareaDTO>> getAllTareas() {

        List<Tarea> tareas = tareaServiceImpl.getTareas();
        List<TareaDTO> tareasDTO = tareas.stream().map(tareaServiceImpl::convertToDTO).toList();

        return new ResponseEntity<>(tareasDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaDTO> getTareaById(@PathVariable Long id) {

        Optional<Tarea> tarea = tareaServiceImpl.getTareaById(id);

        if (tarea.isPresent()) {
            TareaDTO tareaDTO = tareaServiceImpl.convertToDTO(tarea.get());
            return new ResponseEntity<>(tareaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarea(@PathVariable Long id) {
        Optional<Tarea> tarea = tareaServiceImpl.getTareaById(id);
        if (tarea.isPresent()) {
            tareaServiceImpl.deleteTarea(tarea.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
