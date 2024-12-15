package com.eycrojas.gestor_proyectos.dtos;

import lombok.Data;

@Data
public class ProyectoAsignadoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long creadorId;
    private String nombreCompletoCreador;

}
