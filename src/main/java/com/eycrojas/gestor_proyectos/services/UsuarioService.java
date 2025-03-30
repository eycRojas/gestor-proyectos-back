package com.eycrojas.gestor_proyectos.services;

import com.eycrojas.gestor_proyectos.dtos.UsuarioDTO;
import com.eycrojas.gestor_proyectos.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario saveUsuario(Usuario usuario);

    Usuario updateUsuario(Usuario usuario);

    List<Usuario> getUsuarios();

    Optional<Usuario> getUsuarioById(Long id);

    void deleteUsuario(Long id);

    Optional<Usuario> getUsuarioByEmail(String email);

    UsuarioDTO convertToDTO(Usuario usuario);

}
