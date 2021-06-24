/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import objetos.Etiqueta;
import objetos.Instruccion;
import objetos.Instrucciones.DeclAsign;
import objetos.Proceso;
import objetos.Simbolo;

/**
 *
 * @author user-ubunto
 */
public class ControlTablaSimbolos {

    private List<Etiqueta> etiquetas;
    private List<Simbolo> simbolos;
    private int posicionS;

    public ControlTablaSimbolos(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
        this.simbolos = new ArrayList<>();
        this.posicionS = 0;
    }

    public void generarTabla() {
        for (Etiqueta etiqueta : etiquetas) {
            generarT(etiqueta);
        }
    }

    private void generarT(Etiqueta etiqueta) {
        String tipo = etiqueta.getTipo();
        List<Etiqueta> etiquetas = new ArrayList<>();
        switch (tipo) {
            case "HEAD":
                etiquetas = etiqueta.getEtiquetas();
                for (Etiqueta etiqueta1 : etiquetas) {
                    generarT(etiqueta1);
                }
                break;
            case "BODY":
                etiquetas = etiqueta.getEtiquetas();
                for (Etiqueta etiqueta1 : etiquetas) {
                    generarT(etiqueta1);
                }
                break;
            case "DIV":
                etiquetas = etiqueta.getEtiquetas();
                for (Etiqueta etiqueta1 : etiquetas) {
                    generarT(etiqueta1);
                }
                break;
            case "SCRIPTING":
                revisarScript(etiqueta);
                break;
            default:
//                System.out.println("no match");
        }
    }

    private void revisarScript(Etiqueta etiqueta) {
        List<Proceso> procesos = etiqueta.getProcesos();
        for (Proceso proceso : procesos) {
            List<Instruccion> instrucciones = proceso.getInstrucciones();
            for (Instruccion instruccion : instrucciones) {
                String tipo = instruccion.getTipo();
                switch (tipo) {
                    case "declaracion":
                        DeclAsign decl = (DeclAsign) instruccion;
                        String tipoVar = decl.getTipoVar();
                        String modo = decl.getModo();
                        String valor = getValor(tipoVar, decl.getValores());
                        List<String> variables = decl.getVariables();
                        for (String variable : variables) {
                            Simbolo simbolo = new Simbolo();
                            simbolo.setIdentificador(variable);
                            simbolo.setModo(modo);
                            simbolo.setNumEjecucion("0");
                            simbolo.setPosicion(String.valueOf(this.posicionS));
                            simbolo.setProcedimiento(proceso.getTipo());
                            simbolo.setTipo(tipoVar);
                            simbolo.setValor(valor);
                            this.simbolos.add(simbolo);
                            this.posicionS++;
                        }
                        break;
                    case "asignacion":
                        DeclAsign asg = (DeclAsign) instruccion;
                        List<String> variablesAsg = asg.getVariables();
                        for (String var : variablesAsg) {
                            Simbolo sim = null;
                            for (Simbolo simbolo : simbolos) {
                                if (simbolo.getIdentificador().equals(var)) {
                                    sim = simbolo;
                                    break;
                                }
                            }
                            String valorNuevo = getValor(sim.getTipo(), asg.getValores());
                            sim.setValor(valorNuevo);
                        }
                        break;
                    default:
//                System.out.println("no match");
                }
            }
        }
    }

    private String getValor(String tipoVar, List<String> valores) {
        String strRet = "";
        if (tipoVar.equals("STRING")) {
            String valoresStr = "";
            for (String valor : valores) {
                if (valor.charAt(0) == '\"') {
                    valoresStr += valor.replace("\"", "");
                }else{
                    for (Simbolo simbolo : simbolos) {
                    if (simbolo.getIdentificador().equals(valor)) {                        
                        valoresStr += valor;
                        break;
                    }
                }
                }
            }
            strRet = valoresStr;
        } else {
            String valoresStr = "";
            for (String valor : valores) {
                boolean esVar = false;
                String nuevoVal = "";
                for (Simbolo simbolo : simbolos) {
                    if (simbolo.getIdentificador().equals(valor)) {
                        nuevoVal = simbolo.getValor();
                        esVar = true;
                        break;
                    }
                }
                if (esVar) {
                    valoresStr += nuevoVal;
                } else {
                    valoresStr += valor;
                }
            }
            try {
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                strRet = String.valueOf(engine.eval(valoresStr));
            } catch (Exception e) {

            }
        }
        return strRet;
    }

    public List<Simbolo> getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(List<Simbolo> simbolos) {
        this.simbolos = simbolos;
    }

}
