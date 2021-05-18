/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.Instrucciones;

import java.util.List;
import objetos.Instruccion;
import objetos.Variable;

/**
 *
 * @author user-ubunto
 */
public class Asignacion extends Instruccion{
    private String variable;
    private List<String> valores;

    public Asignacion(String variable, List<String> valores, String tipo) {
        super(tipo);
    }
    
    

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }   

    public List<String> getValores() {
        return valores;
    }

    public void setValores(List<String> valores) {
        this.valores = valores;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void insertarValor(String val){
        this.valores.add(val);
    }
}
