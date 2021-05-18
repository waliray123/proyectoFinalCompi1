/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.Instrucciones;

import java.util.ArrayList;
import java.util.List;
import objetos.Instruccion;

/**
 *
 * @author user-ubunto
 */
public class InsIf extends Instruccion{
    private Instruccion insPadre;
    private List<String> condicionValores;
    private List<Instruccion> instrucciones;
    private List<Instruccion> elses;
    

    public InsIf(String tipo) {
        super(tipo);        
        this.instrucciones = new ArrayList<>();
        this.condicionValores = new ArrayList<>();
        this.elses = new ArrayList<>();
    }        

    public Instruccion getInsPadre() {
        return insPadre;
    }

    public void setInsPadre(Instruccion insPadre) {
        this.insPadre = insPadre;
    }

    public List<String> getCondicionValores() {
        return condicionValores;
    }

    public void setCondicionValores(List<String> condicionValores) {
        this.condicionValores = condicionValores;
    }

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }
    
    public void insertarInstruccion(Instruccion instruccion){
        this.instrucciones.add(instruccion);
    }
    
    public void insertarElse(Instruccion instruccion){
        this.elses.add(instruccion);
    }

    public List<Instruccion> getElses() {
        return elses;
    }
    
    
}
