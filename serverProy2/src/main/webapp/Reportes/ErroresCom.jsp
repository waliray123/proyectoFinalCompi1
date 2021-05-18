<%-- 
    Document   : ErroresCom
    Created on : 24/04/2021, 17:30:11
    Author     : user-ubunto
--%>

<%@page import="objetos.ErrorCom"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  href="/serverProy2/css/bootstrap.css">
        <title>Errores de Compilacion</title>
    </head>
    <body>
        <%List<ErrorCom> errores = (List<ErrorCom>) request.getAttribute("erroresCom");%>
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
                            <h1 class="muted">Reporte de Errores de Compilacion</h1>
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
                    <th scope="col">Tipo de Error</th>
                    <th scope="col">Linea</th>
                    <th scope="col">Columna</th>
                    <th scope="col">Descripcion</th>
                    <th scope="col">Lexema</th>
                    <th scope="col">Posible solucion</th>
                </tr>
            </thead>
            <tbody>
                <!--For para ingresar los datos-->       
                <%for (ErrorCom error : errores) {
                    String tipo = error.getTipo();
                    String lin = error.getLin();
                    String col = error.getCol();
                    String desc = error.getDesc();
                    String lex = error.getLex();
                    String posSol = error.getPosSol();
                %>                  
                <tr>
                    <th scope="row"><%=tipo%></th>
                    <td><%=lin%></td>
                    <td><%=col%></td>
                    <td><%=desc%></td>
                    <td><%=lex%></td>
                    <td><%=posSol%></td>
                </tr>                  
                <%  }
                %>
            </tbody>
        </table>
    </body>
</html>
