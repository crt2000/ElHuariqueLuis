package com.huarique.app.api;

import com.huarique.app.entity.Sede;
import com.huarique.app.service.SedeApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sedes")
public class SedeRestController {

    private final SedeApiService sedeService;

    public SedeRestController(SedeApiService sedeService) {
        this.sedeService = sedeService;
    }

    @GetMapping
    public List<Sede> listar() {
        return sedeService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sede> buscar(@PathVariable Long id) {
        return sedeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
