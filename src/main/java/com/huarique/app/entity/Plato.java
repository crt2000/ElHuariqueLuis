package com.huarique.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "plato")
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 60, message = "El nombre debe tener entre 3 y 60 caracteres")
    @Column(nullable = false, length = 60)
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 200, message = "La descripción admite máximo 200 caracteres")
    @Column(nullable = false, length = 200)
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.50", message = "El precio mínimo es S/ 0.50")
    @DecimalMax(value = "999.99", message = "El precio máximo es S/ 999.99")
    @Column(nullable = false)
    private Double precio;

    @NotBlank(message = "La ruta de la imagen es obligatoria")
    @Size(max = 150, message = "La ruta de la imagen admite máximo 150 caracteres")
    @Column(nullable = false, length = 150)
    private String imagenUrl;

    @NotBlank(message = "La etiqueta es obligatoria")
    @Size(max = 20, message = "La etiqueta admite máximo 20 caracteres")
    @Column(nullable = false, length = 20)
    private String etiqueta;

    @Size(max = 40, message = "El color admite máximo 40 caracteres")
    @Column(length = 40)
    private String colorEtiqueta = "bg-primary";

    @NotNull(message = "Debe elegir una categoría")
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Plato() {
    }

    public Plato(String nombre, String descripcion, Double precio, String imagenUrl,
                 String etiqueta, String colorEtiqueta, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenUrl = imagenUrl;
        this.etiqueta = etiqueta;
        this.colorEtiqueta = colorEtiqueta;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    public String getEtiqueta() { return etiqueta; }
    public void setEtiqueta(String etiqueta) { this.etiqueta = etiqueta; }

    public String getColorEtiqueta() { return colorEtiqueta; }
    public void setColorEtiqueta(String colorEtiqueta) { this.colorEtiqueta = colorEtiqueta; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
