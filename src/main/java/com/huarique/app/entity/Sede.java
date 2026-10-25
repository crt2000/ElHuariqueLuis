package com.huarique.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sede")
public class Sede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la sede es obligatorio")
    @Size(max = 60, message = "El nombre admite máximo 60 caracteres")
    @Column(nullable = false, unique = true, length = 60)
    private String nombre;

    @Size(max = 120, message = "La dirección admite máximo 120 caracteres")
    @Column(length = 120)
    private String direccion;

    public Sede() {
    }

    public Sede(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
}
