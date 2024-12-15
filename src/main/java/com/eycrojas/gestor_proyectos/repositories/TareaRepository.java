package com.eycrojas.gestor_proyectos.repositories;

import com.eycrojas.gestor_proyectos.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
