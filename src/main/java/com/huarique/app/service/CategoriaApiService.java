package com.huarique.app.service;

import com.huarique.app.entity.Categoria;
import com.huarique.app.repository.CategoriaRepository;
import com.huarique.app.repository.PlatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaApiService {

    private final CategoriaRepository categoriaRepository;
    private final PlatoRepository platoRepository;

    public CategoriaApiService(CategoriaRepository categoriaRepository, PlatoRepository platoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.platoRepository = platoRepository;
    }

    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria guardar(Categoria categoria) {
        validarNombreUnico(categoria.getNombre(), null);
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> actualizar(Long id, Categoria datos) {
        return categoriaRepository.findById(id).map(c -> {
            validarNombreUnico(datos.getNombre(), id);
            c.setNombre(datos.getNombre());
            return categoriaRepository.save(c);
        });
    }

    public boolean eliminar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            return false;
        }
        if (platoRepository.existsByCategoriaId(id)) {
            throw new IllegalStateException("No se puede eliminar: la categoría tiene platos asociados");
        }
        categoriaRepository.deleteById(id);
        return true;
    }

    private void validarNombreUnico(String nombre, Long idActual) {
        categoriaRepository.findByNombreIgnoreCase(nombre).ifPresent(existente -> {
            if (idActual == null || !existente.getId().equals(idActual)) {
                throw new IllegalArgumentException("Ya existe una categoría con ese nombre");
            }
        });
    }
}
