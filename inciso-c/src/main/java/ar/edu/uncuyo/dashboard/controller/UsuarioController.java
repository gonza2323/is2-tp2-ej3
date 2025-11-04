package ar.edu.uncuyo.dashboard.controller;

import ar.edu.uncuyo.dashboard.dto.UsuarioCreateFormDto;
import ar.edu.uncuyo.dashboard.dto.UsuarioDto;
import ar.edu.uncuyo.dashboard.entity.Usuario;
import ar.edu.uncuyo.dashboard.error.BusinessException;
import ar.edu.uncuyo.dashboard.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioService> {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        super(usuarioService);
        this.usuarioService = usuarioService;
    }

    private final String vistaLista = "/usuario/usuarioLista";
    private final String vistaDetalle = "/usuario/usuarioDetalle";
    private final String vistaAlta = "/usuario/usuarioAlta";
    private final String vistaEdicion = "/usuario/usuarioEdit";
    private final String redirectLista = "/usuarios";


    @GetMapping("")
    public String listarUsuarios(Model model) {
        try {
            List<UsuarioDto> usuarios = usuarioService.listarUsuariosDtos();
            model.addAttribute("usuarios", usuarios);
        } catch (Exception e) {
            model.addAttribute("msgError", "Error al obtener usuarios");
        }
        return vistaLista;
    }

    @GetMapping("/{id}")
    public String detalleUsuario(Model model, @PathVariable Long id) {
        try {
            UsuarioDto usuario = usuarioService.buscarUsuarioDto(id);
            model.addAttribute("usuario", usuario);
            return vistaDetalle;
        } catch (Exception e) {
            model.addAttribute("msgError", "Error al cargar usuario");
            return vistaLista;
        }
    }

    @GetMapping("/alta")
    public String altaUsuario(Model model) {
        model.addAttribute("usuario", new UsuarioCreateFormDto());
        return vistaAlta;
    }

    @GetMapping("/{id}/edit")
    public String modificarUsuario(Model model, @PathVariable Long id) {
        try {
            UsuarioDto usuario = usuarioService.buscarUsuarioDto(id);
            model.addAttribute("usuario", usuario);
            return vistaEdicion;
        } catch (Exception e) {
            model.addAttribute("msgError", "Error al cargar usuario");
            return vistaLista;
        }
    }

    @PostMapping("/alta")
    public String crearUsuario(@Valid @ModelAttribute("usuario") UsuarioCreateFormDto usuarioDto,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return vistaAlta;

        try {
            usuarioService.crearUsuario(usuarioDto);
            return "redirect:" + redirectLista;
        } catch (BusinessException e) {
            model.addAttribute("msgError", e.getMessage());
            return vistaAlta;
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de sistema");
            return vistaAlta;
        }
    }

    @PostMapping("/edit")
    public String actualizarUsuario(@Valid @ModelAttribute("usuario") UsuarioDto usuarioDto,
                                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return vistaEdicion;

        try {
            usuarioService.modificarUsuario(usuarioDto);
            return "redirect:" + redirectLista;
        } catch (BusinessException e) {
            model.addAttribute("msgError", e.getMessage());
            return vistaEdicion;
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de sistema");
            return vistaEdicion;
        }
    }

    @PostMapping("/{id}/baja")
    public String eliminarUsuario(@PathVariable Long id, Model model) {
        try {
            usuarioService.eliminarUsuario(id);
            return "redirect:" + redirectLista;
        } catch (BusinessException e) {
            model.addAttribute("msgError", e.getMessage());
            return vistaLista;
        } catch (Exception e) {
            model.addAttribute("msgError", "Error de sistema");
            return vistaLista;
        }
    }
}
