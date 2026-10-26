package com.huarique.app.config;

import com.huarique.app.entity.Categoria;
import com.huarique.app.entity.Plato;
import com.huarique.app.entity.Reserva;
import com.huarique.app.entity.Sede;
import com.huarique.app.repository.CategoriaRepository;
import com.huarique.app.repository.PlatoRepository;
import com.huarique.app.repository.ReservaRepository;
import com.huarique.app.repository.SedeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final SedeRepository sedeRepository;
    private final PlatoRepository platoRepository;
    private final ReservaRepository reservaRepository;

    public DataLoader(CategoriaRepository categoriaRepository, SedeRepository sedeRepository,
                      PlatoRepository platoRepository, ReservaRepository reservaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.sedeRepository = sedeRepository;
        this.platoRepository = platoRepository;
        this.reservaRepository = reservaRepository;
    }

    @Override
    public void run(String... args) {
        if (categoriaRepository.count() == 0) {
            Categoria entradas = categoriaRepository.save(new Categoria("Entradas"));
            Categoria ceviches = categoriaRepository.save(new Categoria("Ceviches"));
            Categoria parrillas = categoriaRepository.save(new Categoria("Parrillas"));
            Categoria combos = categoriaRepository.save(new Categoria("Combos"));
            Categoria bebidas = categoriaRepository.save(new Categoria("Bebidas"));

            platoRepository.save(new Plato("Ceviche Mixto", "Pescado y mariscos frescos en leche de tigre", 38.0, "img/ceviche-mixto.jpg", "Popular", "bg-primary", ceviches));
            platoRepository.save(new Plato("Chicharrón Mixto", "Pescado y mariscos apanados y fritos", 35.0, "img/chicharron-mixto.jpg", "Popular", "bg-primary", entradas));
            platoRepository.save(new Plato("Parrilla Mixta", "Selección de carnes a la parrilla", 55.0, "img/parrilla-mixta.jpg", "Parrilla", "bg-danger", parrillas));
            platoRepository.save(new Plato("Leche de Tigre", "Clásico jugo de cocción del ceviche", 18.0, "img/leche-de-tigre.jpg", "Entrada", "bg-info text-dark", entradas));
            platoRepository.save(new Plato("Combo Chihuan 1", "Combo de entrada y fondo", 60.0, "img/combo-chihuan-1.jpg", "Oferta", "bg-warning text-dark", combos));
            platoRepository.save(new Plato("Anticuchos", "Brochetas de corazón a la parrilla", 20.0, "img/anticuchos.jpg", "Parrilla", "bg-danger", parrillas));
            platoRepository.save(new Plato("Combo Familiar 1", "Combo para 4 personas", 90.0, "img/combo-familiar-1.jpg", "Familiar", "bg-success", combos));
            platoRepository.save(new Plato("Chicha Morada", "Bebida tradicional peruana", 10.0, "img/chicha-morada.jpg", "Bebida", "bg-info text-dark", bebidas));
            platoRepository.save(new Plato("Papa Rellena", "Papa rellena con guiso de carne", 12.0, "img/papa-rellena.jpg", "Entrada", "bg-secondary", entradas));
        }

        if (sedeRepository.count() == 0) {
            sedeRepository.save(new Sede("Sede Principal", "Por definir"));
            sedeRepository.save(new Sede("Sede Secundaria", "Por definir"));
        }

        if (reservaRepository.count() == 0) {
            List<Sede> sedes = sedeRepository.findAll();
            Sede sede = sedes.get(0);
            LocalDate base = LocalDate.now().plusDays(3);

            reservaRepository.save(new Reserva("Luis Ramírez", "987654321", sede, base, LocalTime.of(13, 0), 4));
            reservaRepository.save(new Reserva("María Torres", "976543210", sede, base, LocalTime.of(14, 30), 2));
            reservaRepository.save(new Reserva("Luis Ramírez", "987654321", sede, base.plusDays(1), LocalTime.of(20, 0), 6));
            reservaRepository.save(new Reserva("Carlos Vega", "965432109", sede, base.plusDays(2), LocalTime.of(19, 0), 3));
            reservaRepository.save(new Reserva("María Torres", "976543210", sede, base.plusDays(4), LocalTime.of(13, 30), 5));
            reservaRepository.save(new Reserva("Luis Ramírez", "987654321", sede, base.plusDays(5), LocalTime.of(21, 0), 2));
        }
    }
}