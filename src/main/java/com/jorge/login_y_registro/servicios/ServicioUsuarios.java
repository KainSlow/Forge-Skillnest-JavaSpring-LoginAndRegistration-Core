package com.jorge.login_y_registro.servicios;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.jorge.login_y_registro.modelos.Usuario;
import com.jorge.login_y_registro.repositorios.RepositorioUsuarios;

@Service
public class ServicioUsuarios {

    private final RepositorioUsuarios repositorioUsuarios;

    public ServicioUsuarios(RepositorioUsuarios repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return this.repositorioUsuarios.findAll();
    }

    public Usuario encontrarPorCorreo(String correo) {
        return this.repositorioUsuarios.findByCorreo(correo).orElse(null);
    }

    public Usuario encontrarPorUsuario(String usuario) {
        return this.repositorioUsuarios.findByNombreUsuario(usuario).orElse(null);
    }

    public Usuario agregarUsuario(Usuario usuario) {
        usuario.setClave(BCrypt.hashpw(usuario.getClave(), BCrypt.gensalt()));
        return this.repositorioUsuarios.save(usuario);
    }

}
