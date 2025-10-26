package ar.edu.uncuyo.ej2b.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persona extends Base {
    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private Integer dni;

    @JoinColumn(nullable = false)
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false)
    private Domicilio domicilio;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.PERSIST)
    private List<Libro> libros;
}
