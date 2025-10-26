package ar.edu.uncuyo.ej2b.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Autor extends Base{

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    private String biografia;


    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros = new ArrayList<>();
}
