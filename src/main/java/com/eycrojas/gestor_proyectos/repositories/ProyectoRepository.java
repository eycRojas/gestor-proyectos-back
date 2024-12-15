package com.eycrojas.gestor_proyectos.repositories;

import com.eycrojas.gestor_proyectos.entities.Proyecto;
import com.eycrojas.gestor_proyectos.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    List<Proyecto> findByCreador(Usuario usuario);

}
