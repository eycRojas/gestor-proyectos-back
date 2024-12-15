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
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario creador;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    private List<Tarea> tareas = new ArrayList<>();

    @ManyToMany(mappedBy = "proyectosAsignados")
    private List<Usuario> usuariosAsignados = new ArrayList<>();

}
