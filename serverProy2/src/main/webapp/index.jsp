<%-- 
    Document   : index
    Created on : 22/04/2021, 09:02:11
    Author     : user-ubunto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  href="/serverProy2/css/bootstrap.css">   
        <link rel="stylesheet"  href="/serverProy2/css/codemirror.css">   
        <link rel="stylesheet"  href="/serverProy2/css/base16-light.css">   
        <script src="/serverProy2/js/codemirror.js"></script>
        <title>JSP Page</title>
    </head>
    <%%>
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
        <br><br><br>

        <!--Nombre de la aplicacion -->
        <div class="container">
            <div class="head">
                <div class="row-fluid">
                    <div class="span12">
                        <div class="span6">
                            <h1 class="muted">Generador de Captchas Ingenieria CUNOC</h1>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Entrada de GCIC -->
        <form role="form" id="entrada-form" class="container" method="POST" action="SLCompilar"> 
            <div class="container border border-secondary rounded">                
                <textarea id="codeeditor" name="codeeditor"></textarea>
                <script>
                    var editor = CodeMirror.fromTextArea(document.getElementById("codeeditor"), {
                        //value: "#test {\n\tposition: absolute;\n\twidth: auto;\n\theight: 50px;\n}",                
                        tabSize: 4,
                        lineNumbers: true,
                        firstLineNumber: 1,
                        theme: "base16-light"
                    });  
                    editor.setSize(1075, 650);
                </script>                
            </div>
            <br>
            <div class="row">
                <div class="form-group col-md-12">
                    <button type="submit" class="btn btn-secondary">Compilar</button>
                </div>
            </div>
        </form>          
    </body>
</html>
