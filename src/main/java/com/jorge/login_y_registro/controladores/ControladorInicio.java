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

import jakarta.servlet.http.HttpSession;
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
    public String muestraInicio(
            HttpSession sesion) {

        if (sesion.getAttribute("id") == null) {
            return "redirect:/";
        }
        return "inicio.jsp";
    }

    @PostMapping("/procesa/registro")
    public String procesaRegistro(
            HttpSession sesion,
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

        sesion.setAttribute("id", usuarioNuevo.getId());
        sesion.setAttribute("nombreUsuario", usuarioNuevo.getNombreUsuario());
        sesion.setAttribute("nombre", usuarioNuevo.getNombre());
        sesion.setAttribute("apellido", usuarioNuevo.getApellido());
        sesion.setAttribute("fechaNacimiento", usuarioNuevo.getFechaNacimiento());
        sesion.setAttribute("correo", usuarioNuevo.getCorreo());

        return "redirect:/inicio";
    }

    @PostMapping("/procesa/login")
    public String procesaIngreso(
            HttpSession sesion,
            @Valid @ModelAttribute("usuario") LoginUsuario usuario,
            BindingResult validaciones,
            @ModelAttribute("usuarioNuevo") Usuario usuarioNuevo,
            BindingResult validacionesUsuarioNuevo) {

        Usuario match = this.servicioUsuarios.encontrarPorUsuario(usuario.getUsuarioLogin());

        if (match == null) {
            validaciones.rejectValue("usuarioClave", "usuarioNoEncontrado",
                    "Nombre de usuario o contraseña incorrectas");
            return "index.jsp";

        } else {
            if (!BCrypt.checkpw(usuario.getUsuarioClave(), match.getClave())) {
                validaciones.rejectValue("usuarioClave", "claveNoCoincide",
                        "Nombre de usuario o contraseña incorrectas");
                return "index.jsp";
            }
        }

        if (validaciones.hasErrors()) {
            return "index.jsp";
        }

        sesion.setAttribute("id", match.getId());
        sesion.setAttribute("nombreUsuario", match.getNombreUsuario());
        sesion.setAttribute("nombre", match.getNombre());
        sesion.setAttribute("apellido", match.getApellido());
        sesion.setAttribute("fechaNacimiento", match.getFechaNacimiento());
        sesion.setAttribute("correo", match.getCorreo());

        return "redirect:/inicio";
    }

    @PostMapping("/procesa/logout")
    public String procesaLogout(HttpSession sesion) {
        sesion.invalidate();
        return "redirect:/";
    }

}
