package com.eycrojas.gestor_proyectos.controllers;

import com.eycrojas.gestor_proyectos.dtos.UsuarioDTO;
import com.eycrojas.gestor_proyectos.entities.Usuario;
import com.eycrojas.gestor_proyectos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario savedUsuario = usuarioService.saveUsuario(usuario);
            return new ResponseEntity<>(savedUsuario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario savedUsuario = usuarioService.updateUsuario(usuario);
            return new ResponseEntity<>(savedUsuario, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {

        List<Usuario> usuarios = usuarioService.getUsuarios();
        List<UsuarioDTO> usuarioDTOS = usuarios.stream().map(usuarioService::convertToDTO).toList();

        return new ResponseEntity<>(usuarioDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {

        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);

        if (usuario.isPresent()) {
            UsuarioDTO usuarioDTO = usuarioService.convertToDTO(usuario.get());
            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioDTO> getUsuarioByEmail(@PathVariable String email) {
        Optional<Usuario> usuario = usuarioService.getUsuarioByEmail(email);
        if (usuario.isPresent()) {
            UsuarioDTO usuarioDTO = usuarioService.convertToDTO(usuario.get());
            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        if (usuario.isPresent()) {
            usuarioService.deleteUsuario(usuario.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
