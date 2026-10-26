package com.huarique.app.validator;

import com.huarique.app.entity.Plato;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlatoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Plato.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Plato plato = (Plato) target;

        // @NotNull cubre "categoria": null; aquí se cubre "categoria": {} (sin id)
        if (plato.getCategoria() != null && plato.getCategoria().getId() == null) {
            errors.rejectValue("categoria", "categoria.sin.id", "Debe indicar el id de la categoría");
        }
    }
}