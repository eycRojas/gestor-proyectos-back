package com.eycrojas.gestor_proyectos.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TareaDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String estado;

    private Long proyectoId;
    private String nombreProyecto;

    private Long usuarioId;
    private String nombreUsuario;

}
