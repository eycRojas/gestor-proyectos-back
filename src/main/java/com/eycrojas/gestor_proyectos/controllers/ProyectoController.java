package com.eycrojas.gestor_proyectos.controllers;

import com.eycrojas.gestor_proyectos.dtos.ProyectoDTO;
import com.eycrojas.gestor_proyectos.dtos.UsuarioDTO;
import com.eycrojas.gestor_proyectos.entities.Proyecto;
import com.eycrojas.gestor_proyectos.entities.Usuario;
import com.eycrojas.gestor_proyectos.services.ProyectoServiceImpl;
import com.eycrojas.gestor_proyectos.services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proyecto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProyectoController {

    @Autowired
    ProyectoServiceImpl proyectoServiceImpl;

    @Autowired
    UsuarioServiceImpl usuarioServiceImpl;

    @PostMapping("/{id}")
    public ResponseEntity<ProyectoDTO> saveProyecto(@RequestBody Proyecto proyecto, @PathVariable Long id) {
        try {

            Usuario creador = usuarioServiceImpl.getUsuarioById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
            proyecto.setCreador(creador);
            Proyecto savedProyecto = proyectoServiceImpl.saveProyecto(proyecto);
            ProyectoDTO proyectoDTO = proyectoServiceImpl.convertToDTO(savedProyecto);

            // Agregar el usuario al proyecto
            proyecto.getUsuariosAsignados().add(creador);

            // Agregar el proyecto al usuario
            creador.getProyectosAsignados().add(proyecto);

            // Guardar cambios
            usuarioServiceImpl.updateUsuario(creador);

            return new ResponseEntity<>(proyectoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<ProyectoDTO> updateProyecto(@PathVariable Long usuarioId, @RequestBody Proyecto proyecto) {
        try {

            Usuario creador = usuarioServiceImpl.getUsuarioById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

            proyecto.setCreador(creador);

            Proyecto savedProyecto = proyectoServiceImpl.updateProyecto(proyecto);

            ProyectoDTO proyectoDTO = proyectoServiceImpl.convertToDTO(savedProyecto);
            return new ResponseEntity<>(proyectoDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<ProyectoDTO>> getAllProyectos() {
        List<Proyecto> proyectos = proyectoServiceImpl.getProyectos();
        List<ProyectoDTO> proyectosDTO = proyectos.stream().map(proyectoServiceImpl::convertToDTO).toList();

        return new ResponseEntity<>(proyectosDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoDTO> getProyectoById(@PathVariable Long id) {

        Optional<Proyecto> proyecto = proyectoServiceImpl.getProyectoById(id);

        if (proyecto.isPresent()) {
            ProyectoDTO proyectoDTO = proyectoServiceImpl.convertToDTO(proyecto.get());
            return new ResponseEntity<>(proyectoDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Long id) {

        Optional<Proyecto> proyecto = proyectoServiceImpl.getProyectoById(id);

        if (proyecto.isPresent()) {
            Usuario creador = proyecto.get().getCreador();

            proyecto.get().getUsuariosAsignados().remove(creador);

            creador.getProyectosAsignados().remove(proyecto.get());

            usuarioServiceImpl.updateUsuario(creador);

            proyectoServiceImpl.deleteProyecto(proyecto.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addParticipante/{proyectoId}/{usuarioId}")
    public ResponseEntity<UsuarioDTO> addParticipante(@PathVariable Long proyectoId, @PathVariable Long usuarioId) {
        System.out.println("entra");
        try {
            System.out.println("entra2");
            Proyecto proyecto = proyectoServiceImpl.getProyectoById(proyectoId)
                    .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
            Usuario usuario = usuarioServiceImpl.getUsuarioById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Agregar el usuario al proyecto
            proyecto.getUsuariosAsignados().add(usuario);

            // Agregar el proyecto al usuario
            usuario.getProyectosAsignados().add(proyecto);

            // Guardar cambios
            usuarioServiceImpl.updateUsuario(usuario);

            UsuarioDTO usuarioDTO = usuarioServiceImpl.convertToDTO(usuario);

            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ProyectoDTO>> getProyectosByUsuario(@PathVariable Long usuarioId) {
        // Obtener el usuario por ID
        Usuario usuario = usuarioServiceImpl.getUsuarioById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener todos los proyectos del usuario
        List<Proyecto> proyectos = proyectoServiceImpl.getProyectosByUsuario(usuario);
        List<ProyectoDTO> proyectosDTO = proyectos.stream().map(proyectoServiceImpl::convertToDTO).toList();

        return new ResponseEntity<>(proyectosDTO, HttpStatus.OK);
    }

}
