/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.util.List;
import objetos.ErrorCom;
import objetos.Etiqueta;
import objetos.Instruccion;
import objetos.Instrucciones.DeclAsign;
import objetos.ParametroEtiqueta;
import objetos.Proceso;

/**
 *
 * @author user-ubunto
 */
public class ControlSemantico {

    private List<Etiqueta> etiquetasTotal;
    private List<ErrorCom> errores;
    private String backgrounds[] = {"black", "olive", "teal", "red", "blue", "maroon", "navy", "gray", "lime",
        "fuchsia", "green", "white", "green", "purple", "silver", "yellow", "aqua"};
    private String fFamily[] = {"Courier", "Verdana", "Arial", "Geneva", "sans-serif"};
    private String textAlign[] = {"left", "right", "center", "justify"};
    private String type[] = {"text", "number", "radio", "checkbox"};

    public ControlSemantico(List<Etiqueta> etiquetasTotal, List<ErrorCom> errores) {
        this.etiquetasTotal = etiquetasTotal;
        this.errores = errores;
    }

    public void validar() {
        validarEtiquetas(this.etiquetasTotal);
    }

    private void validarEtiquetas(List<Etiqueta> etiquetas) {
        for (Etiqueta etiqueta : etiquetas) {
            validarEtiqueta(etiqueta);
            if (etiqueta.getEtiquetas().isEmpty() == false) {
                validarEtiquetas(etiqueta.getEtiquetas());
            }
        }
    }

    private void validarEtiqueta(Etiqueta etiqueta) {
        String tipo = etiqueta.getTipo();
        if (etiqueta.getParametrosEt().isEmpty() == false) {
            validarParametros(etiqueta.getParametrosEt(), tipo);
        }
        if (tipo.equals("SCRIPTING")) {
            validarScript(etiqueta);
        }
    }

    private void validarParametros(List<ParametroEtiqueta> parametros, String tipoEtiqueta) {
        for (ParametroEtiqueta parametro : parametros) {
            String tipoPar = parametro.getTipo();
            String valor = parametro.getVal().replace("\"", "");
            switch (tipoPar) {
                case "FSIZE":
                    validarFSize(valor);
                    break;
                case "BACK":
                    validarBack(valor);
                    break;
                case "COLOR":
                    validarBack(valor);
                    break;
                case "FFAMILY":
                    validarFFamily(valor);
                    break;
                case "TEXTALIGN":
                    validarTextAlign(valor);
                    break;
                case "TYPE":
                    validarType(valor);
                    break;
                case "IDETIQUETA":
                    validarId(valor);
                    break;
                case "COLS":
                    validarRowsCols(valor);
                    break;
                case "ROWS":
                    validarRowsCols(valor);
                    break;
                case "CLASS":
                    validarClass(valor);
                    break;
                case "WIDTH":
                    validarWidthHeight(valor);
                    break;
                case "HEIGHT":
                    validarWidthHeight(valor);
                    break;
                default:
                    break;
            }
        }
    }

    private boolean validarFSize(String valor) {
        if (valor.contains("px")) {
            return true;
        }
        insertarError("Parametro FontSize no identificado", valor, "ingresar algun tamanio correcto");
        return false;
    }

    private boolean validarWidthHeight(String valor) {
        if (valor.contains("px")) {
            return true;
        } else if (valor.contains("%")) {
            return false;
        }
        insertarError("Parametro pixeles o porcentaje no identificado", valor, "ingresar valor correcto");
        return false;
    }

    private boolean validarClass(String valor) {
        switch (valor) {
            case "row":
                return true;
            case "column":
                return true;
            default:
                insertarError("Parametro Class no identificado", valor, "ingresar alguna clase correcta");
                return false;
        }
    }

    private boolean validarRowsCols(String valor) {
        try {
            int val = Integer.parseInt(valor);
            return true;
        } catch (Exception e) {
            insertarError("Parametro no identificado", valor, "ingresar algun numero correcto");
            return false;
        }
    }

    private boolean validarId(String valor) {
        if (valor.contains("$")) {
            return true;
        } else if (valor.contains("_")) {
            return true;
        } else if (valor.contains("-")) {
            return true;
        }
        insertarError("Parametro ID no identificado", valor, "ingresar algun ID correcto");
        return false;
    }

    private boolean validarType(String valor) {
        for (int i = 0; i < type.length; i++) {
            String t = type[i];
            if (t.equals(valor)) {
                return true;
            }
        }
        insertarError("Parametro Type no identificado", valor, "ingresar algun tipo correcto");
        return false;
    }

    private boolean validarTextAlign(String valor) {
        for (int i = 0; i < textAlign.length; i++) {
            String typeText = textAlign[i];
            if (typeText.equals(valor)) {
                return true;
            }
        }
        insertarError("Parametro TextAlign no identificado", valor, "ingresar alguna alineacino correcta");
        return false;
    }

    private boolean validarFFamily(String valor) {
        for (int i = 0; i < fFamily.length; i++) {
            String font = fFamily[i];
            if (font.equals(valor)) {
                return true;
            }
        }
        insertarError("Parametro FontFamily no identificado", valor, "ingresar algun tipo de fuente correcto");
        return false;
    }

    private boolean validarBack(String valor) {
        char primerChar = valor.charAt(0);
        if (primerChar == '#') {
            return true;
        }
        for (int i = 0; i < backgrounds.length; i++) {
            String background = backgrounds[i];
            if (background.equals(valor)) {
                return true;
            }
        }
        insertarError("Parametro de color no identificado", valor, "ingresar algun color o hexadecimal correcto");
        return false;
    }

    private void validarScript(Etiqueta etiqueta) {
        List<Proceso> procesos = etiqueta.getProcesos();
        for (Proceso proceso : procesos) {
            List<Instruccion> instrucciones = proceso.getInstrucciones();
            for (Instruccion instruccion : instrucciones) {
                validarInstruccion(instruccion);
            }
        }
    }

    private void validarInstruccion(Instruccion instruccion) {
        String tipo = instruccion.getTipo();
        //TODO: terminar validacion de cada instruccion        
        switch (tipo) {
            case "declaracion":
                validarDeclaracion(instruccion);
                break;
            case "asignacion":
//                insertarAsignacion(instruccion);
                break;
//            case "INSIF":
//                insertarIf(instruccion);
//                break;
//            case "INSELSE":
//                insertarElse(instruccion);
//                break;
//            case "INSELSEIF":
//                insertarElseIf(instruccion);
//                break;
//            case "REPEAT":
//                insertarFor(instruccion);
//                break;
//            case "WHILE":
//                insertarWhile(instruccion);
//                break;
//            case "INSERT":
//                insertarInsert(instruccion);
//                break;
//            case "ALERTINFO":
//                insertarAlert(instruccion);
//                break;
//            case "REDIRECT":
//                insertarRedirect();
//                break;
//            case "EXIT":
//                gStr += "return;\n";
//                break;
            default:
//                throw new AssertionError();
        }
    }

    private void validarDeclaracion(Instruccion instruccion) {
        DeclAsign declaracion = (DeclAsign) instruccion;
        List<String> valores = declaracion.getValores();
        String tipo = declaracion.getTipoVar();
        boolean hayDivision = false;
        for (String valor : valores) {
            if (tipo.equals("STRING")||tipo.equals("CHAR")) {
                if (valor.equals("-") || valor.equals("*") || valor.equals("/")) {
                    insertarError("Error de signo, No puede ingresar " + valor + " en un "+tipo, valor, "quitar signo");
                } else if (isNumeric(valor)) {
                    insertarError("No puede insertar numeros en  un "+ tipo, valor, "quitar numero");
                }
                if (tipo.equals("STRING")) {
                    if (valor.equals("true") || valor.equals("false")) {
                        insertarError("No puede insertar valores booleanos en un "+ tipo, valor, "quitar booleano");
                    }
                }
            }else if (tipo.equals("BOOLEAN")){
                if (valor.charAt(0) == '\"'|| valor.charAt(valor.length()-1) == '\"') {
                    insertarError("No puede insertar string en un " + tipo, valor, "quitar string");
                }
            }

            if (hayDivision) {
                if (valor.equals("(")) {

                } else if (valor.equals("0")) {
                    //insertarError
                    insertarError("Division Entre 0", "0", "quitar division entre 0");
                    hayDivision = false;
                } else {
                    hayDivision = false;
                }
            }
            if (valor.equals("/")) {
                hayDivision = true;
            }
        }
    }

    private boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private void insertarError(String desc, String lex, String posSol) {
        ErrorCom error = new ErrorCom("Semantico", desc, "", "", lex, posSol);
        this.errores.add(error);
    }

    public List<Etiqueta> getEtiquetasTotal() {
        return etiquetasTotal;
    }

    public void setEtiquetasTotal(List<Etiqueta> etiquetasTotal) {
        this.etiquetasTotal = etiquetasTotal;
    }

    public List<ErrorCom> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorCom> errores) {
        this.errores = errores;
    }

}
