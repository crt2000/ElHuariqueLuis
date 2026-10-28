package com.huarique.app.service;

import com.huarique.app.model.Reserva;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private List<Reserva> reservas = new ArrayList<>();
    private int siguienteId = 1;

    public ReservaService() {
        registrar(new Reserva(1, "Carlos Mendoza", "975112233", "Castilla (Av. Progreso 1213)", "2026-09-28", "13:30", 4, "Confirmada"));
        registrar(new Reserva(2, "Carlos Mendoza", "975112233", "Castilla (Av. Progreso 1213)", "2026-09-29", "20:00", 2, "Pendiente"));
        registrar(new Reserva(3, "Ana Torres", "981445566", "Piura Centro", "2026-09-28", "14:00", 3, "Confirmada"));
        registrar(new Reserva(4, "Luis Morales", "966778899", "Castilla (Av. Progreso 1213)", "2026-09-30", "19:00", 5, "Pendiente"));
        registrar(new Reserva(5, "María Castillo", "955223344", "Piura Centro", "2026-09-29", "12:30", 2, "Cancelada"));
    }

    public void registrar(Reserva r) {
        r.setId(siguienteId++);
        if (r.getEstado() == null || r.getEstado().trim().isEmpty()) {
            r.setEstado("Pendiente");
        }
        reservas.add(r);
    }

    public List<Reserva> listarTodas() {
        return reservas;
    }

    public Reserva buscarPorId(int id) {
        for (Reserva r : reservas) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public void cambiarEstado(int id, String nuevoEstado) {
        Reserva r = buscarPorId(id);
        if (r != null) {
            r.setEstado(nuevoEstado);
        }
    }

    public void eliminar(int id) {
        reservas.removeIf(r -> r.getId() == id);
    }

    public List<Map.Entry<String, Long>> clientesFrecuentes() {
        Map<String, Long> conteo = reservas.stream()
                .collect(Collectors.groupingBy(Reserva::getNombre, Collectors.counting()));

        return conteo.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());
    }
}

