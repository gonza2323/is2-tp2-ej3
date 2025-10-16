package ar.edu.uncuyo.carrito.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Articulo extends BaseEntity<Long> {

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Double precio;

    @OneToOne(optional = false)
    @JoinColumn(nullable = false)
    private Imagen imagen;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Proveedor proveedor;
}
