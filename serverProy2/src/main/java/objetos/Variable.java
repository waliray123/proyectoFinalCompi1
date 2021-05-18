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
public class Variable {
    private String identificador;
    private String tipo;
    private String valor;
    private String modo;
    private String procedimiento;

    public Variable() {
    }

    public Variable(String identificador, String tipo, String valor, String modo, String procedimiento) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.valor = valor;
        this.modo = modo;
        this.procedimiento = procedimiento;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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
    
    
}
