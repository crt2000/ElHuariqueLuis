package com.huarique.app.service;

import com.huarique.app.entity.Categoria;
import com.huarique.app.entity.Plato;
import com.huarique.app.repository.CategoriaRepository;
import com.huarique.app.repository.PlatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatoApiService {

    private final PlatoRepository platoRepository;
    private final CategoriaRepository categoriaRepository;

    public PlatoApiService(PlatoRepository platoRepository, CategoriaRepository categoriaRepository) {
        this.platoRepository = platoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Plato> listar() {
        return platoRepository.findAll();
    }

    public Optional<Plato> buscarPorId(Long id) {
        return platoRepository.findById(id);
    }

    public List<Plato> buscarPorNombre(String texto) {
        return platoRepository.findByNombreContainingIgnoreCase(texto);
    }

    public List<Plato> listarPorCategoria(Long categoriaId) {
        return platoRepository.findByCategoriaId(categoriaId);
    }

    public Plato guardar(Plato plato) {
        plato.setId(null);
        plato.setCategoria(resolverCategoria(plato.getCategoria()));
        corregirColor(plato);
        return platoRepository.save(plato);
    }

    // Reemplazo total (PUT)
    public Optional<Plato> actualizarCompleto(Long id, Plato datos) {
        return platoRepository.findById(id).map(p -> {
            p.setNombre(datos.getNombre());
            p.setDescripcion(datos.getDescripcion());
            p.setPrecio(datos.getPrecio());
            p.setImagenUrl(datos.getImagenUrl());
            p.setEtiqueta(datos.getEtiqueta());
            p.setColorEtiqueta(datos.getColorEtiqueta());
            p.setCategoria(resolverCategoria(datos.getCategoria()));
            corregirColor(p);
            return platoRepository.save(p);
        });
    }

    public Optional<Plato> actualizarParcial(Long id, Double precio, String etiqueta) {
        return platoRepository.findById(id).map(p -> {
            if (precio != null) {
                p.setPrecio(precio);
            }
            if (etiqueta != null && !etiqueta.trim().isEmpty()) {
                p.setEtiqueta(etiqueta);
            }
            return platoRepository.save(p);
        });
    }

    public boolean eliminar(Long id) {
        if (!platoRepository.existsById(id)) {
            return false;
        }
        platoRepository.deleteById(id);
        return true;
    }

    private Categoria resolverCategoria(Categoria categoria) {
        if (categoria == null || categoria.getId() == null) {
            throw new IllegalArgumentException("Debe indicar una categoría");
        }
        return categoriaRepository.findById(categoria.getId())
                .orElseThrow(() -> new IllegalArgumentException("La categoría con id " + categoria.getId() + " no existe"));
    }

    private void corregirColor(Plato plato) {
        if (plato.getColorEtiqueta() == null || plato.getColorEtiqueta().trim().isEmpty()) {
            plato.setColorEtiqueta("bg-primary");
        }
    }
}
