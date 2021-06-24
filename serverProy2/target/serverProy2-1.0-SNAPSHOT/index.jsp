<%-- 
    Document   : index
    Created on : 22/04/2021, 09:02:11
    Author     : user-ubunto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>                
        <link rel="stylesheet"  href="/serverProy2/css/bootstrap.css"> 
        <script src="/serverProy2/js/codemirror.js"></script>        
        <title>JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
        <form role="form" id="entrada-form" class="container" method="POST" action="SLCompilar" accept-charset="UTF-8"> 
            <div class="container border border-secondary rounded">                
                <textarea id="codeeditor" name="codeeditor" rows="27" cols="100" class="form-control" onkeyup="getLineNumberAndColumnIndex(this);" onmouseup="this.onkeyup();" ></textarea>                             
            </div>  
            <br>
            <div class="row">
                <div class="form-group col-md-12">
                    <button type="submit" class="btn btn-secondary" value="compilar">Compilar</button>
                    <button type="submit" class="btn btn-secondary" id="comp2"  value="debug">Debug</button>
                    <input type="reset" class="btn btn-secondary" value="Nuevo" id="nuevo"/>
                    <button type="button" value="Guardar" id="Guardar" class="btn btn-secondary">Guardar</button>
                    <input type="file" id="archivoTexto" class="btn btn-secondary">
                    <input type="hidden" name="comp" id="comp" value="compilar">
                    <p id="mensajes"></p>
                </div>                
            </div>           
            <span style="float:right;" id = "columnaEdit">0</span>
            <span style="float:right;">Columna: </span>                
            <span style="float:right;" id = "filaEdit">0</span>
            <span style="float:right;">Fila: </span>
            <script>
                function getLineNumberAndColumnIndex(textarea) {
                    var textLines = textarea.value.substr(0, textarea.selectionStart).split("\n");
                    var currentLineNumber = textLines.length;
                    var currentColumnIndex = textLines[textLines.length - 1].length;
                    const spanFila = document.getElementById("filaEdit");
                    const spanColumna = document.getElementById("columnaEdit");
                    spanFila.textContent = currentLineNumber;
                    spanColumna.textContent = currentColumnIndex;
                }
                function abrirArchivo(evento) {
                    let archivo = evento.target.files[0];

                    if (archivo) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var textArea = document.getElementById("codeeditor");
                            textArea.value = e.target.result;
                        };
                        reader.readAsText(archivo);
                    } else {
                        document.getElementById('mensajes').innerText = 'No se ha seleccionado un archivo.';
                    }
                }

                function saveTextAsFile() {
                    var textToWrite = document.getElementById('codeeditor').value;
                    var textFileAsBlob = new Blob([textToWrite], {type: 'text/plain'});
                    var fileNameToSaveAs = "file.txt"; //filename.extension

                    var downloadLink = document.createElement("a");
                    downloadLink.download = fileNameToSaveAs;
                    downloadLink.innerHTML = "Download File";
                    if (window.webkitURL != null) {
                        // Chrome allows the link to be clicked without actually adding it to the DOM.
                        downloadLink.href = window.webkitURL.createObjectURL(textFileAsBlob);
                    } else {
                        // Firefox requires the link to be added to the DOM before it can be clicked.
                        downloadLink.href = window.URL.createObjectURL(textFileAsBlob);
                        downloadLink.onclick = destroyClickedElement;
                        downloadLink.style.display = "none";
                        document.body.appendChild(downloadLink);
                    }

                    downloadLink.click();
                }
                function destroyClickedElement(event) {
                    // remove the link from the DOM
                    document.body.removeChild(event.target);
                }

                function limpiar() {
                    document.getElementById('codeeditor').value = "";
                }

                function setDebug() {
                    document.getElementById('comp').value = "debug";
                }

                window.addEventListener('load', () => {
                    document.getElementById('archivoTexto').addEventListener('change', abrirArchivo);
                    document.getElementById('nuevo').addEventListener('change', limpiar);
                    document.getElementById('Guardar').addEventListener('click', saveTextAsFile);
                    document.getElementById('comp2').addEventListener('click', setDebug);
                });

//                    var editor = CodeMirror.fromTextArea(document.getElementById("codeeditor"), {
//                        //value: "#test {\n\tposition: absolute;\n\twidth: auto;\n\theight: 50px;\n}", 
////                        onChange : getLineNumberAndColumnIndex(this),
//                        tabSize: 4,
//                        lineNumbers: true,
//                        firstLineNumber: 1,
//                        theme: "base16-light"
//                    });
//                    editor.setSize(1075, 650);

            </script>  
        </form>

    </body>
</html>
