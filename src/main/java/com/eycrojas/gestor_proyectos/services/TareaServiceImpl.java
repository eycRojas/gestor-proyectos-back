package com.eycrojas.gestor_proyectos.services;

import com.eycrojas.gestor_proyectos.dtos.TareaDTO;
import com.eycrojas.gestor_proyectos.entities.Tarea;
import com.eycrojas.gestor_proyectos.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TareaServiceImpl implements TareaService {

    @Autowired
    TareaRepository tareaRepository;

    @Override
    public Tarea saveTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public Tarea updateTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    public List<Tarea> getTareas() {
        return tareaRepository.findAll();
    }

    @Override
    public Optional<Tarea> getTareaById(Long id) {
        return tareaRepository.findById(id);
    }

    @Override
    public void deleteTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    public TareaDTO convertToDTO(Tarea tarea) {

        TareaDTO tareaDTO = new TareaDTO();
        tareaDTO.setId(tarea.getId());
        tareaDTO.setNombre(tarea.getNombre());
        tareaDTO.setDescripcion(tarea.getDescripcion());
        tareaDTO.setEstado(tarea.getEstado());
        tareaDTO.setProyectoId(tarea.getProyecto().getId());
        tareaDTO.setNombreProyecto(tarea.getProyecto().getNombre());
        tareaDTO.setUsuarioId(tarea.getUsuario().getId());
        tareaDTO.setNombreUsuario(tarea.getUsuario().getNombre() + " " + tarea.getUsuario().getApellidoPaterno() + " " + tarea.getUsuario().getApellidoMaterno());

        return tareaDTO;
    }
}
