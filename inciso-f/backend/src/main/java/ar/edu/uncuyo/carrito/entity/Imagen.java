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
public class Imagen extends BaseEntity<Long> {

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String mime;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Byte[] contenido;
}
