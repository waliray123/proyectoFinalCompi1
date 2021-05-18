/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.Instrucciones;

import objetos.Instruccion;

/**
 *
 * @author user-ubunto
 */
public class AlertInfo extends Instruccion{
    
    private String mensaje;
    
    public AlertInfo(String tipo) {
        super(tipo);
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }        
    
    
}
