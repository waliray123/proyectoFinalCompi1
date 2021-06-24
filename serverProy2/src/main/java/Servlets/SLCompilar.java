/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objetos.ErrorCom;
import analizadores.LexerGCIC;
import analizadores.LexerUtil;
import analizadores.ParserGCIC;
import analizadores.ParserUtil;
import controladores.ControlSemantico;
import controladores.ControlTablaSimbolos;
import generadores.GenStrGuardado;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import objetos.Captcha;
import objetos.Etiqueta;
import objetos.ParametroEtiqueta;
import objetos.Simbolo;

/**
 *
 * @author user-ubunto
 */
@WebServlet(name = "SLCompilar", urlPatterns = {"/SLCompilar"})
public class SLCompilar extends HttpServlet {

    private List<ErrorCom> erroresCom;
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/serverProy2/webresources";
    private String strDbOComp;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/serverProy2/Reportes/ErroresCom.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);
        RequestDispatcher rd;
        String entrada = request.getParameter("codeeditor");
        entrada = entrada.replace("Ã¡", "á");
        entrada = entrada.replace("Ã©", "é");        
        entrada = entrada.replace("Ã³", "ó");
        entrada = entrada.replace("Ãº", "ú");
        entrada = entrada.replace("Ã­", "í");
        strDbOComp = request.getParameter("comp");        
        String goTo = compilar(entrada, request);
        rd = request.getRequestDispatcher(goTo);
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String compilar(String entrada, HttpServletRequest request) {
//        System.out.println(limpiar("a"));
//        limpiar("");
        this.erroresCom = new ArrayList<>();
        List<Etiqueta> etiquetas = new ArrayList<>();
        List<Simbolo> simbolos = new ArrayList<>();
        String strReturn = "";        
        StringReader reader = new StringReader(entrada);
        LexerGCIC lexico = new LexerGCIC(reader);
        ParserGCIC parser = new ParserGCIC(lexico);
        try {
            parser.parse();
            this.erroresCom = parser.getErroresCom();
            etiquetas = parser.getEtiquetasTotal();
            if (this.erroresCom.isEmpty() == true) {
                //TODO: No hay errores 
                ControlSemantico control = new ControlSemantico(etiquetas, this.erroresCom);
                control.validar();
                ControlTablaSimbolos controlTS = new ControlTablaSimbolos(etiquetas);
                controlTS.generarTabla();
                simbolos = controlTS.getSimbolos();
                this.erroresCom = control.getErrores();
            } else {
                simbolos = null;
            }
        } catch (Exception ex) {
            System.out.println("Error irrecuperable");
            System.out.println("Causa: " + ex.getCause());
            System.out.println("Causa2: " + ex.toString());
            System.out.println(ex);
            ex.printStackTrace();
        }
        if (strDbOComp.equals("debug")) {
            strReturn = "/Reportes/ReporteDebug.jsp";
            request.setAttribute("simbolos", simbolos);
        }
        if (erroresCom.isEmpty()) {
//            No hay errores se pueden cargar los datos 
            String strGuardado = generarGuardadoDatos(etiquetas, entrada);

            String strUtil = obtenerUtil("a");
            List<Captcha> captchas = new ArrayList<>();
            StringReader reader2 = new StringReader(strUtil);
            LexerUtil lexico2 = new LexerUtil(reader2);
            ParserUtil parser2 = new ParserUtil(lexico2);
            try {
                parser2.parse();
                captchas = parser2.getCaptchas();
            } catch (Exception ex) {
                System.out.println("Error irrecuperable");
                System.out.println("Causa: " + ex.getCause());
//            System.out.println("Causa2: " + ex.toString());
                System.out.println(ex);
                ex.printStackTrace();
            }
            String id = "";
            String nombre = "";
            List<ParametroEtiqueta> parametros = etiquetas.get(0).getParametrosEt();
            for (ParametroEtiqueta parametro : parametros) {
                String tipo = parametro.getTipo();
                if (tipo.equals("IDETIQUETA")) {
                    id = parametro.getVal();
                } else if (tipo.equals("NAME")) {
                    nombre = parametro.getVal();
                }
            }
            Captcha cap1 = new Captcha();
            cap1.setIdCaptcha(id);
            cap1.setNombreCaptcha(nombre);
            cap1.setCantUsos("0");
            cap1.setCantAciertos("0");
            cap1.setCantFallos("0");
            cap1.setUltFecha("01-01-2021");
            captchas.add(cap1);

            String prueba = insertarDatos(strGuardado);
            String utilStr = generarUtil(etiquetas, entrada, captchas);
            String prueba2 = insertarUtil(utilStr);
            if (strDbOComp.equals("debug")) {
                request.setAttribute("erroresCom", this.erroresCom);
            }     
            System.out.println(prueba);
            System.out.println(prueba2);
        } else {
            //Hay errores mostrar errores en un JF
            request.setAttribute("erroresCom", this.erroresCom);
            if (strDbOComp.equals("debug")) {
                strReturn = "/Reportes/ReporteDebug.jsp";
            } else {
                strReturn = "/Reportes/ErroresCom.jsp";
            }

        }
        System.out.println(obtenerDB("aaa"));
        System.out.println(obtenerUtil("aaa"));
//        System.out.println(pruebaGuardado);
        return strReturn;
    }

    private String generarGuardadoDatos(List<Etiqueta> etiquetas, String entrada) {
        String id = "";
        String nombre = "";
        List<ParametroEtiqueta> parametros = etiquetas.get(0).getParametrosEt();
        for (ParametroEtiqueta parametro : parametros) {
            String tipo = parametro.getTipo();
            if (tipo.equals("IDETIQUETA")) {
                id = parametro.getVal();
            } else if (tipo.equals("NAME")) {
                nombre = parametro.getVal();
            }
        }
        GenStrGuardado genStrGuardado = new GenStrGuardado(entrada, nombre, id);
        return genStrGuardado.getgStr();
    }

    private String generarUtil(List<Etiqueta> etiquetas, String entrada, List<Captcha> captchas) {
        String id = "";
        String nombre = "";
        List<ParametroEtiqueta> parametros = etiquetas.get(0).getParametrosEt();
        for (ParametroEtiqueta parametro : parametros) {
            String tipo = parametro.getTipo();
            if (tipo.equals("IDETIQUETA")) {
                id = parametro.getVal();
            } else if (tipo.equals("NAME")) {
                nombre = parametro.getVal();
            }
        }
        GenStrGuardado genStrGuardado = new GenStrGuardado(entrada, nombre, id);
        genStrGuardado.generarUtil(captchas);
        return genStrGuardado.getuStr();
    }

    private String insertarDatos(String datos) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("guardado");
        return cargarForm(datos);
    }

    public String obtenerDB(String db) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("guardado");
        WebTarget resource = webTarget;
        if (db != null) {
            resource = resource.queryParam("db", db);
        }
        resource = resource.path("prueba2");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    private String obtenerUtil(String db) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("guardado");
        WebTarget resource = webTarget;
        if (db != null) {
            resource = resource.queryParam("db", db);
        }
        resource = resource.path("prueba3");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    private String limpiar(String db) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("guardado");
        WebTarget resource = webTarget;
        if (db != null) {
            resource = resource.queryParam("db", db);
        }
        resource = resource.path("prueba4");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    private String insertarUtil(String datos) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("guardado");
        return cargarUtil(datos);
    }

    public String cargarUtil(String strF) {
        Form form = new Form();
        form.param("strForm", strF);
        String pruebaRetorno = webTarget.path("cargarUtil").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
        return pruebaRetorno;
        //return webTarget.path("prueba").request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form), String.class);
    }

    public String cargarForm(String strF) {
        Form form = new Form();
        form.param("strForm", strF);
        String pruebaRetorno = webTarget.path("cargarForm").request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
        return pruebaRetorno;
        //return webTarget.path("prueba").request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(form), String.class);
    }

}
