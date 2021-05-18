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
public class DeclAsign extends Instruccion{    
    private String tipoVar;
    private String modo;
    private List<String> variables;
    private List<String> valores;

    public DeclAsign(String tipo) {
        super(tipo);
        this.variables = new ArrayList<>();
        this.valores = new ArrayList<>();
    }        

    public String getTipoVar() {
        return tipoVar;
    }

    public void setTipoVar(String tipo) {
        this.tipoVar = tipo;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public List<String> getVariables() {
        return variables;
    }

    public void setVariables(List<String> variables) {
        this.variables = variables;
    }

    public List<String> getValores() {
        return valores;
    }

    public void setValores(List<String> valores) {
        this.valores = valores;
    }
    
    public void insertarValor(String val){
        this.valores.add(val);
    }
    
    public void insertarVariable(String var){
        this.variables.add(var);
    }
    
}
