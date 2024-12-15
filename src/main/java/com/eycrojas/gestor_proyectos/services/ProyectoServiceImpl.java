package com.eycrojas.gestor_proyectos.services;

import com.eycrojas.gestor_proyectos.dtos.ProyectoAsignadoDTO;
import com.eycrojas.gestor_proyectos.dtos.ProyectoDTO;
import com.eycrojas.gestor_proyectos.dtos.TareaDTO;
import com.eycrojas.gestor_proyectos.dtos.UsuarioAsignadoDTO;
import com.eycrojas.gestor_proyectos.entities.Proyecto;
import com.eycrojas.gestor_proyectos.entities.Usuario;
import com.eycrojas.gestor_proyectos.repositories.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProyectoServiceImpl implements ProyectoService{

    @Autowired
    ProyectoRepository proyectoRepository;

    @Autowired
    TareaServiceImpl tareaServiceImpl;

    @Autowired
    UsuarioServiceImpl usuarioServiceImpl;

    @Override
    public Proyecto saveProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public Proyecto updateProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    @Override
    public List<Proyecto> getProyectos(){
        return proyectoRepository.findAll();
    }

    @Override
    public Optional<Proyecto> getProyectoById(Long id){
        return proyectoRepository.findById(id);
    }

    @Override
    public void deleteProyecto(Long id){
        proyectoRepository.deleteById(id);
    }

    @Override
    public List<Proyecto> getProyectosByUsuario(Usuario usuario){
        return proyectoRepository.findByCreador(usuario);
    }

    //Convertir Proyecto a ProyectoDTO
    public ProyectoDTO convertToDTO(Proyecto proyecto){
        ProyectoDTO proyectoDTO = new ProyectoDTO();
        proyectoDTO.setId(proyecto.getId());
        proyectoDTO.setNombre(proyecto.getNombre());
        proyectoDTO.setDescripcion(proyecto.getDescripcion());
        proyectoDTO.setCreadorId(proyecto.getCreador().getId());
        proyectoDTO.setNombreCompletoCreador(proyecto.getCreador().getNombre() + " " + proyecto.getCreador().getApellidoPaterno() + " " + proyecto.getCreador().getApellidoMaterno());

        List<TareaDTO> tareasAsignadasDTO = proyecto.getTareas().stream().map(tareaServiceImpl::convertToDTO).toList();

        List<UsuarioAsignadoDTO> usuariosAsignados = proyecto.getUsuariosAsignados().stream().map(usuarioServiceImpl::conertUsuarioAsignadoToDTO).toList();

        proyectoDTO.setTareas(tareasAsignadasDTO);

        proyectoDTO.setUsuariosAsignados(usuariosAsignados);

        return proyectoDTO;
    }

    public ProyectoAsignadoDTO convertProyectoAsignadoToDTO(Proyecto proyecto){
        ProyectoAsignadoDTO proyectoAsignadoDTO = new ProyectoAsignadoDTO();
        proyectoAsignadoDTO.setId(proyecto.getId());
        proyectoAsignadoDTO.setNombre(proyecto.getNombre());
        proyectoAsignadoDTO.setDescripcion(proyecto.getDescripcion());
        proyectoAsignadoDTO.setCreadorId(proyecto.getCreador().getId());
        proyectoAsignadoDTO.setNombreCompletoCreador(proyecto.getCreador().getNombre() + " " + proyecto.getCreador().getApellidoPaterno() + " " + proyecto.getCreador().getApellidoMaterno());

        return proyectoAsignadoDTO;
    }

}
