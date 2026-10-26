package com.huarique.app.api;

import com.huarique.app.dto.ClienteFrecuente;
import com.huarique.app.entity.Reserva;
import com.huarique.app.service.ReservaApiService;
import com.huarique.app.validator.ReservaValidator;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaRestController {

    private final ReservaApiService reservaService;
    private final ReservaValidator reservaValidator;

    public ReservaRestController(ReservaApiService reservaService, ReservaValidator reservaValidator) {
        this.reservaService = reservaService;
        this.reservaValidator = reservaValidator;
    }

    // Se aplica solo al parámetro de tipo Reserva (el nombre del binder es "reserva")
    @InitBinder("reserva")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(reservaValidator);
    }

    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscar(@PathVariable Long id) {
        return reservaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/clientes-frecuentes")
    public List<ClienteFrecuente> clientesFrecuentes() {
        return reservaService.clientesFrecuentes();
    }

    @PostMapping
    public ResponseEntity<Reserva> crear(@Valid @RequestBody Reserva reserva) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.guardar(reserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> reemplazar(@PathVariable Long id, @Valid @RequestBody Reserva reserva) {
        return reservaService.actualizar(id, reserva)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return reservaService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}