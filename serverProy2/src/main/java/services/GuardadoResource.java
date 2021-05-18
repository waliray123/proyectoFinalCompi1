/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import controladores.GuardarDatos;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author user-ubunto
 */
@Path("guardado")
public class GuardadoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GuardadoResource
     */
    public GuardadoResource() {
    }

    /**
     * Retrieves representation of an instance of services.GuardadoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GuardadoResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/prueba")
    public String obtenerPrueba() {
        return "la entrada es: ";
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/prueba2")
    public String obtenerDB(@QueryParam("db") String id) {
        //return "El dbForm es: " + dbForm;
        GuardarDatos guardadoDatos = new GuardarDatos();
        String datos = guardadoDatos.getAllData();
//        guardadoDatos.guardarDatos("");
        return datos;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/prueba3")
    public String obtenerUtilizacion(@QueryParam("db") String id) {
        //return "El dbForm es: " + dbForm;
        GuardarDatos guardadoDatos = new GuardarDatos();
        String datos = guardadoDatos.getUtilizacion();
//        guardadoDatos.guardarDatos("");
        return datos;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/prueba4")
    public String obtenerLimpieza(@QueryParam("db") String id) {
        //return "El dbForm es: " + dbForm;
        GuardarDatos guardadoDatos = new GuardarDatos();
        guardadoDatos.limpiar();
//        guardadoDatos.guardarDatos("");
        return "limpiado";
    }
            
    @POST
    @Path("/cargarForm")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String cargarForm(@FormParam("strForm") String strForm) {        
//        String respuesta = cargarNuevoForm(strForm);
        guardarCaptcha(strForm);
        return "guardadoCompleto";
    }
        
    
    private void guardarCaptcha(String strC){
        GuardarDatos guardadoDatos = new GuardarDatos();
        String datos = guardadoDatos.getAllData();
        datos += "\n"+strC;
        String util = guardadoDatos.getUtilizacion();
        guardadoDatos.guardarDatos(datos,util);
    }
    
    @POST
    @Path("/cargarUtil")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String cargarUtilizacion(@FormParam("strForm") String strForm) {        
//        String respuesta = cargarNuevoForm(strForm);
        guardarUtil(strForm);
        return "guardadoUtilCompleto";
    }
    
    private void guardarUtil(String util){
        GuardarDatos guardadoDatos = new GuardarDatos();
        String datos = guardadoDatos.getAllData();                       
        guardadoDatos.guardarDatos(datos,util);
    }
}
