/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import analizadores.LexerGuardado;
import analizadores.LexerUtil;
import analizadores.ParserGuardado;
import analizadores.ParserUtil;
import generadores.GenStrGuardado;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import objetos.Captcha;
import objetos.ErrorCom;
import objetos.Etiqueta;
import objetos.ParametroEtiqueta;

/**
 *
 * @author user-ubunto
 */
public class ControlCargarCaptchas {

    private List<Captcha> captchas;

    private List<ErrorCom> erroresCom;
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/serverProy2/webresources";

    private String obtenerDB(String db) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("guardado");
        WebTarget resource = webTarget;
        if (db != null) {
            resource = resource.queryParam("db", db);
        }
        resource = resource.path("prueba2");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    private String obtenerUtilizacion(String db) {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("guardado");
        WebTarget resource = webTarget;
        if (db != null) {
            resource = resource.queryParam("db", db);
        }
        resource = resource.path("prueba3");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }
    

    public Captcha getCaptchaPorId(String id) {
        String entrada = obtenerDB("a");
        StringReader reader = new StringReader(entrada);
        LexerGuardado lexico = new LexerGuardado(reader);
        ParserGuardado parser = new ParserGuardado(lexico);
        try {
            parser.parse();
            this.erroresCom = parser.getErroresCom();
            this.captchas = parser.getCaptchas();
        } catch (Exception ex) {
            System.out.println("Error irrecuperable");
            System.out.println("Causa: " + ex.getCause());
//            System.out.println("Causa2: " + ex.toString());
            System.out.println(ex);
            ex.printStackTrace();
        }
        for (Captcha captcha : captchas) {
            String idC = captcha.getIdCaptcha().replace("\"", "");
            if (idC.equals(id)) {
                return captcha;
            }
        }
        return null;
    }

    public void insertarUtilizacion(String id) {
        List<Captcha> captchas = getUtilizacion();
        for (Captcha captcha : captchas) {
            String idC = captcha.getIdCaptcha().replace("\"", "");
            if (idC.equals(id)) {
                String usos = captcha.getCantUsos();
                String fecha = getFechaHoy();
                String usos2 = sumarUsos(usos);
                captcha.setCantUsos(usos2);
                captcha.setUltFecha(fecha);
                break;
            }
        }
        String utilStr = generarUtil(captchas);
        insertarUtil(utilStr);
    }
    
    public void insertarAcierto(String id) {
        List<Captcha> captchas = getUtilizacion();
        for (Captcha captcha : captchas) {
            String idC = captcha.getIdCaptcha().replace("\"", "");
            if (idC.equals(id)) {
                String aciertos = captcha.getCantAciertos();                                
                aciertos = sumarUsos(aciertos);
                captcha.setCantAciertos(aciertos);
                break;
            }
        }
        String utilStr = generarUtil(captchas);
        insertarUtil(utilStr);
    }
    
    private String generarUtil(List<Captcha> captchas) {        
        GenStrGuardado genStrGuardado = new GenStrGuardado("", "", "");
        genStrGuardado.generarUtil(captchas);
        return genStrGuardado.getuStr();
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
    }

    private String getFechaHoy() {
        String fecha = "";
        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH));
        String annio = Integer.toString(c.get(Calendar.YEAR));
        fecha = dia+"-"+mes+"-"+annio;
        return fecha;
    }
    
    private String sumarUsos(String uso){
        String usos = "0";
        Integer usoI = Integer.parseInt(uso.replace("\"", ""));
        usoI += 1;
        usos = String.valueOf(usoI);
        return usos;
    }        

    public List<Captcha> getUtilizacion() {
        String entrada = obtenerUtilizacion("a");
        StringReader reader = new StringReader(entrada);
        LexerUtil lexico = new LexerUtil(reader);
        ParserUtil parser = new ParserUtil(lexico);
        try {
            parser.parse();
            this.erroresCom = parser.getErroresCom();
            this.captchas = parser.getCaptchas();
        } catch (Exception ex) {
            System.out.println("Error irrecuperable");
            System.out.println("Causa: " + ex.getCause());
//            System.out.println("Causa2: " + ex.toString());
            System.out.println(ex);
            ex.printStackTrace();
        }
        return captchas;
    }

}
