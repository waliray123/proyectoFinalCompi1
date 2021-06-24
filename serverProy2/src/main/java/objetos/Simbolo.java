/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * @author user-ubunto
 */
public class Simbolo {
    private String posicion;
    private String identificador;
    private String tipo;
    private String modo;
    private String procedimiento;
    private String numEjecucion;
    private String valor;

    public Simbolo(String posicion, String identificador, String tipo, String modo, String procedimiento, String numEjecucion,String valor) {
        this.posicion = posicion;
        this.identificador = identificador;
        this.tipo = tipo;
        this.modo = modo;
        this.procedimiento = procedimiento;
        this.numEjecucion = numEjecucion;
        this.valor = valor;
    }

    public Simbolo() {
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public String getNumEjecucion() {
        return numEjecucion;
    }

    public void setNumEjecucion(String numEjecucion) {
        this.numEjecucion = numEjecucion;
    }
    
    
    
}
