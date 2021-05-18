/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user-ubunto
 */
public class Etiqueta {
    private String tipo;
    private List<ParametroEtiqueta> parametrosEt;
    private List<Etiqueta> etiquetas;  
    private String contenido;
    private Etiqueta etiquetaPadre;
    private List<Instruccion> instrucciones;
    private List<Proceso> procesos;
    private Proceso ultProceso;

    public Etiqueta() {
        this.parametrosEt = new ArrayList<>();
        this.etiquetas = new ArrayList<>();
        this.instrucciones = new ArrayList<>();
        this.procesos = new ArrayList<>();
        this.etiquetaPadre = null;
        this.contenido = "";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<ParametroEtiqueta> getParametrosEt() {
        return parametrosEt;
    }

    public void setParametrosEt(List<ParametroEtiqueta> parametrosEt) {
        this.parametrosEt = parametrosEt;
    }        
    
    public void insertarParametro(ParametroEtiqueta par){
        this.parametrosEt.add(par);
    }
    
    public void insertarEtiqueta(Etiqueta eti){
        this.etiquetas.add(eti);
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Etiqueta getEtiquetaPadre() {
        return etiquetaPadre;
    }

    public void setEtiquetaPadre(Etiqueta etiquetaPadre) {
        this.etiquetaPadre = etiquetaPadre;
    }

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public List<Proceso> getProcesos() {
        return procesos;
    }

    public void setProcesos(List<Proceso> procesos) {
        this.procesos = procesos;
    }        

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }
    
//    public void insertarInstruccion(Instruccion instruccion){
//        this.instrucciones.add(instruccion);
//    }
    
    public void insertarInstruccion(Instruccion instruccion){        
        this.ultProceso.insertarInstruccion(instruccion);
    }
    
    public void insertarProceso(Proceso pros){        
        this.procesos.add(pros);
        this.ultProceso = pros;
    }
}
