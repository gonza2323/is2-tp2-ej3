package ar.edu.uncuyo.ej2b.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Localidad extends Base{
    @Column(nullable = false)
    private String denominacion;
}
