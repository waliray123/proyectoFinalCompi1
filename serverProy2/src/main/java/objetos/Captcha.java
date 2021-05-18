/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.List;

/**
 *
 * @author user-ubunto
 */
public class Captcha {
    private String idCaptcha;
    private String nombreCaptcha;
    private String cantUsos;
    private String cantAciertos;
    private String cantFallos;
    private String ultFecha;    
    private List<Etiqueta> etiquetas;

    public String getIdCaptcha() {
        return idCaptcha;
    }

    public void setIdCaptcha(String idCaptcha) {
        this.idCaptcha = idCaptcha;
    }

    public String getNombreCaptcha() {
        return nombreCaptcha;
    }

    public void setNombreCaptcha(String nombreCaptcha) {
        this.nombreCaptcha = nombreCaptcha;
    }

    public String getCantUsos() {
        return cantUsos;
    }

    public void setCantUsos(String cantUsos) {
        this.cantUsos = cantUsos;
    }

    public String getCantAciertos() {
        return cantAciertos;
    }

    public void setCantAciertos(String cantAciertos) {
        this.cantAciertos = cantAciertos;
    }

    public String getCantFallos() {
        return cantFallos;
    }

    public void setCantFallos(String cantFallos) {
        this.cantFallos = cantFallos;
    }

    public String getUltFecha() {
        return ultFecha;
    }

    public void setUltFecha(String ultFecha) {
        this.ultFecha = ultFecha;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }
    
    
    
}
