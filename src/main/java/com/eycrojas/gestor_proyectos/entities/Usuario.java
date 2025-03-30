package com.eycrojas.gestor_proyectos.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellidoPaterno;

    @NotBlank
    private String apellidoMaterno;

    @NotBlank
    private String email;

    @NotBlank
    private String contrasenia;

    @OneToMany(mappedBy = "creador", cascade = CascadeType.ALL)
    private List<Proyecto> proyectosCreados = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tarea> tareas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "participantes_proyecto", //Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "usuario_id"), //clave foránea del usuario
            inverseJoinColumns = @JoinColumn(name = "proyecto_id") //clave foránea del proyecto
    )
    private List<Proyecto> proyectosAsignados = new ArrayList<>();



}
