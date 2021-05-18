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
public class Ciclo extends Instruccion{
    
    private Instruccion insPadre;
    private List<String> condicionValores;
    private List<Instruccion> instrucciones;
    private Instruccion asigDecl;
    private List<String> expresionNum;

    public Ciclo(String tipo) {
        super(tipo);
        this.instrucciones = new ArrayList<>();
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

    public Instruccion getAsigDecl() {
        return asigDecl;
    }

    public void setAsigDecl(Instruccion asigDecl) {
        this.asigDecl = asigDecl;
    }

    public List<String> getExpresionNum() {
        return expresionNum;
    }

    public void setExpresionNum(List<String> expresionNum) {
        this.expresionNum = expresionNum;
    }    
    
}
