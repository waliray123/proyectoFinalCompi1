<%-- 
    Document   : ListadoCaptchas
    Created on : 24/04/2021, 17:21:56
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
        <title>Listado Captchas</title>
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
                            <h1 class="muted">Listado de Captchas</h1>
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
                    <th scope="col">Link de Acceso</th>
                </tr>
            </thead>
            <tbody>  
                <%for (Captcha cap : captchas) {
                        String idCaptcha = cap.getIdCaptcha().replace("\"", "");
                        String nombre = cap.getNombreCaptcha();
                        String linkA = "http://localhost:8080/serverProy2/SLCaptcha?idForm=" + idCaptcha;
                %>                                        
                <tr>
                    <th scope="row"><%=nombre%></th>
                    <td>
                        <a href="<%=linkA%>"><%=linkA%></a>         
                    </td> 
                </tr>       
                <%    }
                %>
            </tbody>
        </table>
    </body>
</html>
