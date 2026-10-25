package com.huarique.app.repository;

import com.huarique.app.entity.Plato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatoRepository extends JpaRepository<Plato, Long> {
    boolean existsByCategoriaId(Long categoriaId);
    List<Plato> findByCategoriaId(Long categoriaId);
    List<Plato> findByNombreContainingIgnoreCase(String texto);
}
