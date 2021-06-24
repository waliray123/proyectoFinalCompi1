/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.List;
import objetos.Captcha;
import objetos.Etiqueta;
import objetos.ParametroEtiqueta;

/**
 *
 * @author user-ubunto
 */
public class ControlRedirect {

    private Captcha captcha;

    public ControlRedirect(Captcha captcha) {
        this.captcha = captcha;
    }

    public String getPaginaRedirect() {
        List<Etiqueta> etiquetas = captcha.getEtiquetas();
        for (Etiqueta etiqueta : etiquetas) {
            String tipo = etiqueta.getTipo();
            if (tipo.equals("HEAD")) {
                List<Etiqueta> etiquetasH = etiqueta.getEtiquetas();
                for (Etiqueta etiquetaH : etiquetasH) {
                    String tipo2 = etiquetaH.getTipo();
                    if (tipo2.equals("LINK")) {
                        List<ParametroEtiqueta> parametros = etiquetaH.getParametrosEt();
                        for (ParametroEtiqueta parametro : parametros) {
                            if (parametro.getTipo().equals("HREF")) {
                                return parametro.getVal().replace("\"", "");
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
