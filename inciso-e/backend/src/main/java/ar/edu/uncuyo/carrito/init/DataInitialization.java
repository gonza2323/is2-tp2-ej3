package ar.edu.uncuyo.carrito.init;

import ar.edu.uncuyo.carrito.dto.proveedor.ProveedorCreateDto;
import ar.edu.uncuyo.carrito.dto.usuario.UsuarioCreateDto;
import ar.edu.uncuyo.carrito.repository.UsuarioRepository;
import ar.edu.uncuyo.carrito.service.ProveedorService;
import ar.edu.uncuyo.carrito.service.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitialization implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    private final ProveedorService proveedorService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        crearDatosIniciales();
    }

    @Transactional
    protected void crearDatosIniciales() throws Exception {
        if (usuarioRepository.existsByNombreAndEliminadoFalse("admin@gmail.com")) {
            System.out.println("Datos iniciales ya creados. Salteando creación de datos iniciales. Para forzar su creación, borrar la base de datos");
            return;
        }

        var auth = new UsernamePasswordAuthenticationToken("system", null);
        SecurityContextHolder.getContext().setAuthentication(auth);

        System.out.println("Creando datos iniciales...");

        // Creación de datos iniciales
        crearUsuarios();
        crearProveedores();

        // Resetear los permisos
        SecurityContextHolder.clearContext();

        System.out.println("Datos iniciales creados.");
    }

    @Transactional
    protected void crearUsuarios() {
        usuarioService.create(UsuarioCreateDto.builder()
                .nombre("admin@gmail.com")
                .clave("1234")
                .claveConfirmacion( "1234")
                .build());

        usuarioService.create(UsuarioCreateDto.builder()
                .nombre("Pepe Argento")
                .clave("1234")
                .claveConfirmacion( "1234")
                .build());

        usuarioService.create(UsuarioCreateDto.builder()
                .nombre("Moni Argento")
                .clave("1234")
                .claveConfirmacion( "1234")
                .build());
    }

    @Transactional
    protected void crearProveedores() {
        for (int i = 0; i < 50; i++) {
            proveedorService.create(ProveedorCreateDto.builder()
                    .nombre("Proveedor " + String.format("%02d", i)).build());
        }
    }
}
