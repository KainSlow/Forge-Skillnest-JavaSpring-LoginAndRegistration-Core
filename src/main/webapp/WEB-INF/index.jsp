<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, java.text.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body class="bg panel col gap pad">
    <div class="panel row gap pad jevenly">
        <div class="panel col pad acenter"> <!-- Login -->
            <h2>Login</h2>
            <form:form class="panel col pad gap" action="/procesa/login" method="POST" modelAttribute="usuario">
            <div class="inputBox panel col">
                    <form:label path = "usuarioLogin">Usuario</form:label>
                    <form:input path = "usuarioLogin"></form:input>
                    <form:errors path = "usuarioLogin"></form:errors>
            </div>

            <div class="inputBox panel col gap"">
                <form:label path = "usuarioClave">Contraseña</form:label>
                <form:input type="password" path = "usuarioClave"></form:input>
                <form:errors path = "usuarioClave"></form:errors>
            </div>
            <button type="submit" class="btn">Ingresar</button>
            </form:form>
        </div>
        <div class="panel col pad acenter"> <!-- Registro -->
            <h2>Registro</h2>
            <form:form class="panel col pad gap" action="/procesa/registro" method="POST" modelAttribute="usuarioNuevo">
                <div class="inputBox panel col gap">
                    <form:label path = "nombreUsuario">Usuario</form:label>
                    <form:input path = "nombreUsuario"></form:input>
                    <form:errors path = "nombreUsuario"></form:errors>
                </div>

                <div class="inputBox panel col gap">
                    <form:label path = "nombre">Nombre</form:label>
                    <form:input path = "nombre"></form:input>
                    <form:errors path = "nombre"></form:errors>
                </div>

                <div class="inputBox panel col gap">
                    <form:label path = "apellido">Apellido</form:label>
                    <form:input path = "apellido"></form:input>
                    <form:errors path = "apellido"></form:errors>
                </div>

                <div class="inputBox panel col gap">
                    <form:label path = "fechaNacimiento">Fecha de Nacimiento</form:label>
                    <form:input type="date" path = "fechaNacimiento"></form:input>
                    <form:errors path = "fechaNacimiento"></form:errors>
                </div>
                
                <div class="inputBox panel col gap">
                    <form:label path = "correo">Correo</form:label>
                    <form:input autocomplete="off" type="email" path = "correo"></form:input>
                    <form:errors path = "correo"></form:errors>
                </div>

                <div class="inputBox panel col gap"">
                    <form:label path = "clave">Contraseña</form:label>
                    <form:input autocomplete="new-password" type="password" path = "clave"></form:input>
                    <form:errors path = "clave"></form:errors>
                </div>

                <div class="inputBox panel col gap"">
                    <form:label path = "confirmacionClave">Confirmar Contraseña</form:label>
                    <form:input autocomplete="new-password" type="password" path = "confirmacionClave"></form:input>
                    <form:errors path = "confirmacionClave"></form:errors>
                </div>
                <button type="submit" class="btn">Registrar</button>
            </form:form>
        </div>
    </div>

</body>
</html>