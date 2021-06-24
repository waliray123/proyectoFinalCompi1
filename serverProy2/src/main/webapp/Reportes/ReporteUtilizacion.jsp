<%-- 
    Document   : ReporteUtilizacion
    Created on : 24/04/2021, 16:42:11
    Author     : user-ubunto
--%>

<%@page import="objetos.Captcha"%>
<%@page import="java.util.List"%>
<%@page import="controladores.ControlCargarCaptchas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        ControlCargarCaptchas control = new ControlCargarCaptchas();
        List<Captcha> captchas = control.getUtilizacion();
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  href="/serverProy2/css/bootstrap.css">
        <title>Reporte de Utilizacion</title>
    </head>
    <body>    
        <nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark fixed-top">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">GCIC</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" href="/serverProy2/index.jsp">Generador</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/serverProy2/Reportes/ReporteUtilizacion.jsp">Reporte Utilizacion</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="/serverProy2/Reportes/ListadoCaptchas.jsp">Listado Captchas</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!--Nombre de pagina-->
        <br><br><br>
        <div class="container">
            <div class="head">
                <div class="row-fluid">
                    <div class="span12">
                        <div class="span6">
                            <h1 class="muted">Reporte de Utilizacion de Captchas</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <!--Inicio de tabla-->
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th scope="col">Nombre del Captcha</th>
                    <th scope="col">Cantidad Veces Usado</th>
                    <th scope="col">Cantidad Aciertos</th>
                    <th scope="col">Cantidad Fallos</th>
                    <th scope="col">Ultima fecha Utilizacion</th>
                </tr>
            </thead>
            <tbody>
                <!--For para ingresar los datos-->   
                <%for (Captcha cap : captchas) {
                    String nombre = cap.getNombreCaptcha().replace("\"", "");
                    String cantU = cap.getCantUsos().replace("\"", "");
                    String cantA = cap.getCantAciertos().replace("\"", "");
                    String cantF = cap.getCantFallos().replace("\"", "");                    
                    String fecha = cap.getUltFecha().replace("\"", "");
                    Integer cantidad = Integer.valueOf(cantU)-Integer.valueOf(cantA);
                    cantF = String.valueOf(cantidad);
                %>                                        
                <tr>
                    <th scope="row"><%=nombre%></th>
                    <td><%=cantU%></td>
                    <td><%=cantA%></td>
                    <td><%=cantF%></td>
                    <td><%=fecha%></td>                    
                </tr>       
                <%    }                
                %>
            </tbody>
        </table>
    </body>
</html>
