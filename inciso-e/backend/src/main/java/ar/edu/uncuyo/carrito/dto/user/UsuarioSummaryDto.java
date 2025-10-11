package ar.edu.uncuyo.carrito.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioSummaryDto {
    private Long id;
    private String nombre;
}
