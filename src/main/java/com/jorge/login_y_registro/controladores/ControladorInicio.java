package com.jorge.login_y_registro.controladores;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jorge.login_y_registro.modelos.LoginUsuario;
import com.jorge.login_y_registro.modelos.Usuario;
import com.jorge.login_y_registro.servicios.ServicioUsuarios;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorInicio {

    private final ServicioUsuarios servicioUsuarios;

    public ControladorInicio(ServicioUsuarios servicioUsuarios) {
        this.servicioUsuarios = servicioUsuarios;
    }

    @GetMapping("/")
    public String formularioInicio(@ModelAttribute("usuario") LoginUsuario usuario,
            @ModelAttribute("usuarioNuevo") Usuario usuarioNuevo) {
        return "index.jsp";
    }

    @GetMapping("/inicio")
    public String muestraInicio() {
        return "inicio.jsp";
    }

    @PostMapping("/procesa/registro")
    public String procesaRegistro(
            @ModelAttribute("usuario") LoginUsuario usuario,
            BindingResult validaciones,
            @Valid @ModelAttribute("usuarioNuevo") Usuario usuarioNuevo,
            BindingResult validacionesUsuarioNuevo) {

        if (!usuarioNuevo.getClave().equals(usuarioNuevo.getConfirmacionClave())) {
            validacionesUsuarioNuevo.rejectValue("confirmacionClave", "claveNoCoincide",
                    "Las contraseñas no coinciden");
        }

        if (validacionesUsuarioNuevo.hasErrors()) {
            return "index.jsp";
        }

        try {
            this.servicioUsuarios.agregarUsuario(usuarioNuevo);

        } catch (DataIntegrityViolationException e) {
            validacionesUsuarioNuevo.rejectValue("nombreUsuario", "usuarioDuplicado",
                    "Este nombre de usuario ya está en uso.");
            return "index.jsp";
        }

        return "redirect:/inicio";
    }

    @PostMapping("/procesa/login")
    public String procesaIngreso(@Valid @ModelAttribute("usuario") LoginUsuario usuario,
            BindingResult validaciones,
            @ModelAttribute("usuarioNuevo") Usuario usuarioNuevo,
            BindingResult validacionesUsuarioNuevo) {

        Usuario match = this.servicioUsuarios.encontrarPorUsuario(usuario.getUsuarioLogin());

        if (match == null) {
            validaciones.rejectValue("usuarioClave", "usuarioNoEncontrado",
                    "Nombre de usuario o contraseña incorrectas");
        } else {
            if (!BCrypt.checkpw(usuario.getUsuarioClave(), match.getClave())) {
                validaciones.rejectValue("usuarioClave", "claveNoCoincide",
                        "Nombre de usuario o contraseña incorrectas");
            }
        }

        if (validaciones.hasErrors()) {
            return "index.jsp";
        }

        return "redirect:/inicio";
    }

}
