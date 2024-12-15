package com.eycrojas.gestor_proyectos.dtos;

import lombok.Data;

@Data
public class UsuarioAsignadoDTO {

    private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;

}
