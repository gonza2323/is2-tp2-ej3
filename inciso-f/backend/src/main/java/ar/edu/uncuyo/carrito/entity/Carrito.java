package ar.edu.uncuyo.carrito.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Carrito extends BaseEntity<Long> {
    private double total;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "carrito_id") // create FK in detalle
    private List<Detalle> detalles = new ArrayList<>();
}
