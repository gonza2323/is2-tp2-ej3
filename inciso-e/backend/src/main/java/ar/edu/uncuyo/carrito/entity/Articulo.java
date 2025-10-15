package ar.edu.uncuyo.carrito.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany
    private List<Imagen> imagenes;
}
