package com.eycrojas.gestor_proyectos.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;

    private List<ProyectoDTO> proyectosCreados;

    private List<TareaDTO> tareasAsignadas;

    private List<ProyectoAsignadoDTO> proyectosAsignados;

}
