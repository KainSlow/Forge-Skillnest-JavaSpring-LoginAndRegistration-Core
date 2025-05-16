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
<body class="panel bg col pad gap acenter">
    <h1>Bienvenid@ de vuelta  a la aplicación de Login y Registro</h1>

    <form:form action="/procesa/logout" method="POST">
        <button class="btn" type="submit">Cerrar sesión</button>
    </form:form>

    <h2>${id}</h2>
    <h3>
    ${nombreUsuario}
    </h3>
    <h4>
    ${nombre} ${apellido}
    </h4>
    <h4>${fechaNacimiento}</h4>
    <h4>${correo}</h4>

</body>
</html>