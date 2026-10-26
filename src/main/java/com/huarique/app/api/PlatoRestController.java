package com.huarique.app.api;

import com.huarique.app.dto.PlatoPatchDTO;
import com.huarique.app.entity.Plato;
import com.huarique.app.service.PlatoApiService;
import com.huarique.app.validator.PlatoValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platos")
public class PlatoRestController {

    private final PlatoApiService platoService;
    private final PlatoValidator platoValidator;

    public PlatoRestController(PlatoApiService platoService, PlatoValidator platoValidator) {
        this.platoService = platoService;
        this.platoValidator = platoValidator;
    }

    // Se aplica solo al parámetro de tipo Plato (el nombre del binder es "plato")
    @InitBinder("plato")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(platoValidator);
    }

    @GetMapping
    public List<Plato> listar() {
        return platoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plato> buscar(@PathVariable Long id) {
        return platoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<Plato> buscarPorNombre(@RequestParam String nombre) {
        return platoService.buscarPorNombre(nombre);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<Plato> listarPorCategoria(@PathVariable Long categoriaId) {
        return platoService.listarPorCategoria(categoriaId);
    }

    @PostMapping
    public ResponseEntity<Plato> crear(@Valid @RequestBody Plato plato) {
        return ResponseEntity.status(HttpStatus.CREATED).body(platoService.guardar(plato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plato> reemplazar(@PathVariable Long id, @Valid @RequestBody Plato plato) {
        return platoService.actualizarCompleto(id, plato)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Plato> actualizarParcial(@PathVariable Long id, @Valid @RequestBody PlatoPatchDTO cambio) {
        return platoService.actualizarParcial(id, cambio.getPrecio(), cambio.getEtiqueta())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return platoService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}