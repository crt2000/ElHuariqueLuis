package com.huarique.app.validator;

import com.huarique.app.entity.Reserva;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ReservaValidator implements Validator {

    private static final LocalTime APERTURA = LocalTime.of(11, 0);
    private static final LocalTime CIERRE = LocalTime.of(22, 0);

    @Override
    public boolean supports(Class<?> clazz) {
        return Reserva.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Reserva reserva = (Reserva) target;

        if (reserva.getSede() != null && reserva.getSede().getId() == null) {
            errors.rejectValue("sede", "sede.sin.id", "Debe indicar el id de la sede");
        }

        if (reserva.getHora() != null) {
            if (reserva.getHora().isBefore(APERTURA) || reserva.getHora().isAfter(CIERRE)) {
                errors.rejectValue("hora", "hora.fuera.horario", "Atendemos de 11:00 a 22:00");
            } else if (reserva.getFecha() != null
                    && reserva.getFecha().equals(LocalDate.now())
                    && reserva.getHora().isBefore(LocalTime.now())) {
                errors.rejectValue("hora", "hora.pasada", "Esa hora ya pasó");
            }
        }
    }
}