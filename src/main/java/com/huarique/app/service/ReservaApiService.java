package com.huarique.app.service;

import com.huarique.app.dto.ClienteFrecuente;
import com.huarique.app.entity.Reserva;
import com.huarique.app.entity.Sede;
import com.huarique.app.repository.ReservaRepository;
import com.huarique.app.repository.SedeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaApiService {

    private final ReservaRepository reservaRepository;
    private final SedeRepository sedeRepository;

    public ReservaApiService(ReservaRepository reservaRepository, SedeRepository sedeRepository) {
        this.reservaRepository = reservaRepository;
        this.sedeRepository = sedeRepository;
    }

    public List<Reserva> listar() {
        return reservaRepository.findAll(Sort.by("fecha", "hora"));
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva guardar(Reserva reserva) {
        reserva.setId(null);
        reserva.setSede(resolverSede(reserva.getSede()));
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> actualizar(Long id, Reserva datos) {
        return reservaRepository.findById(id).map(r -> {
            r.setNombre(datos.getNombre());
            r.setTelefono(datos.getTelefono());
            r.setSede(resolverSede(datos.getSede()));
            r.setFecha(datos.getFecha());
            r.setHora(datos.getHora());
            r.setPersonas(datos.getPersonas());
            return reservaRepository.save(r);
        });
    }

    public boolean eliminar(Long id) {
        if (!reservaRepository.existsById(id)) {
            return false;
        }
        reservaRepository.deleteById(id);
        return true;
    }

    public List<ClienteFrecuente> clientesFrecuentes() {
        return reservaRepository.clientesFrecuentes();
    }

    private Sede resolverSede(Sede sede) {
        if (sede == null || sede.getId() == null) {
            throw new IllegalArgumentException("Debe indicar una sede");
        }
        return sedeRepository.findById(sede.getId())
                .orElseThrow(() -> new IllegalArgumentException("La sede con id " + sede.getId() + " no existe"));
    }
}
