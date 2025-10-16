package ar.edu.uncuyo.carrito.dto.imagen;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImagenCreateDto {
    private String nombre;
    private String mime;
    private byte[] contenido;
}
