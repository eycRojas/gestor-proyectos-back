package com.eycrojas.gestor_proyectos.services;

import com.eycrojas.gestor_proyectos.dtos.*;
import com.eycrojas.gestor_proyectos.entities.Usuario;
import com.eycrojas.gestor_proyectos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private ProyectoServiceImpl proyectoServiceImpl;

    @Autowired
    private TareaServiceImpl tareaServiceImpl;

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> getUsuarioByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioDTO convertToDTO(Usuario usuario) {

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellidoPaterno(usuario.getApellidoPaterno());
        usuarioDTO.setApellidoMaterno(usuario.getApellidoMaterno());
        usuarioDTO.setEmail(usuario.getEmail());

        List<ProyectoDTO> proyectoCreadosDTO = usuario.getProyectosCreados().stream().map(proyectoServiceImpl::convertToDTO).toList();

        List<TareaDTO> tareasAsignadasDTO = usuario.getTareas().stream().map(tareaServiceImpl::convertToDTO).toList();

        List<ProyectoAsignadoDTO> proyectosAsignados = usuario.getProyectosAsignados().stream().map(proyectoServiceImpl::convertProyectoAsignadoToDTO).toList();

        usuarioDTO.setProyectosCreados(proyectoCreadosDTO);
        usuarioDTO.setTareasAsignadas(tareasAsignadasDTO);
        usuarioDTO.setProyectosAsignados(proyectosAsignados);

        return usuarioDTO;

    }

    public UsuarioAsignadoDTO conertUsuarioAsignadoToDTO(Usuario usuario) {

        UsuarioAsignadoDTO usuarioAsignadoDTO = new UsuarioAsignadoDTO();
        usuarioAsignadoDTO.setId(usuario.getId());
        usuarioAsignadoDTO.setNombre(usuario.getNombre());
        usuarioAsignadoDTO.setApellidoPaterno(usuario.getApellidoPaterno());
        usuarioAsignadoDTO.setApellidoMaterno(usuario.getApellidoMaterno());
        usuarioAsignadoDTO.setEmail(usuario.getEmail());

        return usuarioAsignadoDTO;

    }

}
