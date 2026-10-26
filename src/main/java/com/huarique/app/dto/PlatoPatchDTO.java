package com.huarique.app.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;

public class PlatoPatchDTO {

    @DecimalMin(value = "0.50", message = "El precio mínimo es S/ 0.50")
    @DecimalMax(value = "999.99", message = "El precio máximo es S/ 999.99")
    private Double precio;

    @Size(max = 20, message = "La etiqueta admite máximo 20 caracteres")
    private String etiqueta;

    public PlatoPatchDTO() {
    }

    public PlatoPatchDTO(Double precio, String etiqueta) {
        this.precio = precio;
        this.etiqueta = etiqueta;
    }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getEtiqueta() { return etiqueta; }
    public void setEtiqueta(String etiqueta) { this.etiqueta = etiqueta; }
}
