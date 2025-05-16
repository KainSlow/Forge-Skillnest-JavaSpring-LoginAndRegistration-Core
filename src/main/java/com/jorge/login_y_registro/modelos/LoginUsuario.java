package com.jorge.login_y_registro.modelos;

import jakarta.validation.constraints.Size;

public class LoginUsuario {

    @Size(min = 3, max = 15, message = "El nombre de usuario debe contener entre 3 y 15 caracteres")
    private String usuarioLogin;

    private String usuarioClave;

    public LoginUsuario() {
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getUsuarioClave() {
        return usuarioClave;
    }

    public void setUsuarioClave(String usuarioClave) {
        this.usuarioClave = usuarioClave;
    }
}
