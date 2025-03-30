package com.eycrojas.gestor_proyectos.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ProyectoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long creadorId;
    private String nombreCompletoCreador;

    private List<TareaDTO> tareas;

    private List<UsuarioAsignadoDTO> usuariosAsignados;


}
