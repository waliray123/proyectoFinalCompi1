/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;

import java.util.List;
import objetos.Captcha;

/**
 *
 * @author user-ubunto
 */
public class GenStrGuardado {

    private String entrada;
    private String nombre;
    private String id;
    private String gStr;
    private String uStr;

    public GenStrGuardado(String entrada, String nombre, String id) {
        this.entrada = entrada;
        this.nombre = nombre;
        this.id = id;
        gStr = "";
        uStr = "";
        generarStr();
    }

    public void generarStr() {
        gStr += "<C_DATOSFORM>\n";
        gStr += "<C_IDCAP>\"" + id.replace("\"", "") + "\"</C_IDCAP>\n";
        /*"	<C_NOMBRECAP>"+nombre+"</C_NOMBRECAP>\n";
"	<C_CANTUSOS>\"0\"</C_CANTUSOS>\n" +
"	<C_CANTACIERTOS>\"0\"</C_CANTACIERTOS>\n" +
"	<C_CANTFALLOS>\"0\"</C_CANTFALLOS>\n" +
"	<C_ULTIMAFECHA>\"2021-01-01\"<C_ULTIMAFECHA>\n";
         */
        gStr += entrada;
        gStr += "\n</C_DATOSFORM>\n";
    }

    public void generarUtil(List<Captcha> captchas) {
        for (Captcha captcha : captchas) {
            uStr += "<C_DATOSFORM>\n";
            uStr += "<C_IDCAP>\"" + captcha.getIdCaptcha().replace("\"", "") + "\"</C_IDCAP>\n"
                    + "	<C_NOMBRECAP>\"" + captcha.getNombreCaptcha().replace("\"", "") + "\"</C_NOMBRECAP>\n"
                    + "	<C_CANTUSOS>\""+captcha.getCantUsos().replace("\"", "")+"\"</C_CANTUSOS>\n"
                    + "	<C_CANTACIERTOS>\""+captcha.getCantAciertos().replace("\"", "")+"\"</C_CANTACIERTOS>\n"
                    + "	<C_CANTFALLOS>\""+captcha.getCantFallos().replace("\"", "")+"\"</C_CANTFALLOS>\n"
                    + "	<C_ULTIMAFECHA>\""+captcha.getUltFecha().replace("\"", "")+"\"<C_ULTIMAFECHA>\n";
            uStr += "\n</C_DATOSFORM>\n";
        }
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getgStr() {
        return gStr;
    }

    public void setgStr(String gStr) {
        this.gStr = gStr;
    }

    public String getuStr() {
        return uStr;
    }

    public void setuStr(String uStr) {
        this.uStr = uStr;
    }
    

}
