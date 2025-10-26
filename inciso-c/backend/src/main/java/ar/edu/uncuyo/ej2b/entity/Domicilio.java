package ar.edu.uncuyo.ej2b.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Domicilio extends Base{


    @Column(nullable = false)
    private String calle;

    @Column(nullable = false)
    private Integer numeracion;

    @JoinColumn(nullable = false)
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER, optional = false)
    private Localidad localidad;
}
