/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;

import java.util.ArrayList;
import java.util.List;
import objetos.Captcha;
import objetos.Etiqueta;
import objetos.Instruccion;
import objetos.Instrucciones.AlertInfo;
import objetos.Instrucciones.Ciclo;
import objetos.Instrucciones.DeclAsign;
import objetos.Instrucciones.InsIf;
import objetos.Instrucciones.InsInsert;
import objetos.ParametroEtiqueta;
import objetos.Proceso;

/**
 *
 * @author user-ubunto
 */
public class GenStrHTMLJS {

    private Captcha captcha;
    private String gStr;
    private boolean fAsc;
    private boolean fDesc;
    private boolean fLetPar;
    private boolean fLetImpar;
    private boolean fReverse;
    private boolean fCarAlea;
    private boolean fNumAlea;

    public GenStrHTMLJS(Captcha captcha) {
        this.captcha = captcha;
        this.gStr = "";
        generarStr();
    }

    private void limpiar() {
        this.fAsc = false;
        this.fDesc = false;
        this.fLetPar = false;
        this.fLetImpar = false;
        this.fReverse = false;
        this.fCarAlea = false;
        this.fNumAlea = false;
    }

    private void generarStr() {
        this.gStr = "<!DOCTYPE html>";
        this.gStr += "<html>";
        List<Etiqueta> etiquetas = captcha.getEtiquetas();
        for (Etiqueta etiqueta : etiquetas) {
            insertarEtiqueta(etiqueta);
        }
        //var contador_fallas = 5;        
        this.gStr += "</html>";
    }

    private void insertarEtiqueta(Etiqueta etiqueta) {
        String tipo = etiqueta.getTipo();
        List<Etiqueta> etiquetas = new ArrayList<>();
        switch (tipo) {
            case "HEAD":
                gStr += "<head>\n";
                etiquetas = etiqueta.getEtiquetas();
                for (Etiqueta etiqueta1 : etiquetas) {
                    insertarEtiqueta(etiqueta1);
                }
                gStr += "</head>\n";
                break;
            case "TITLE":
                gStr += "<title>";
                gStr += etiqueta.getContenido();
                gStr += "</title>\n";
                break;
            case "BODY":
                gStr += "<body";
                insertarBody(etiqueta);
                gStr += ">\n";
                etiquetas = etiqueta.getEtiquetas();
                for (Etiqueta etiqueta1 : etiquetas) {
                    insertarEtiqueta(etiqueta1);
                }
                gStr += etiqueta.getContenido();
                gStr += "</body>\n";
                break;
            case "SPAM":
                insertarSpan(etiqueta);
                break;
            case "INPUT":
                insertarInput(etiqueta);
                break;
            case "TEXTAREA":
                insertarTextArea(etiqueta);
                break;
            case "SELECT":
                insertarSelect(etiqueta);
                break;
            case "OPTION":
                gStr += "<option>";
                gStr += etiqueta.getContenido();
                gStr += "</option>\n";
                break;
            case "DIV":
                insertarDiv(etiqueta);
                break;
            case "IMG":
                insertarImg(etiqueta);
                break;
            case "BR":
                gStr += "<br>\n";
                break;
            case "BUTTON":
                insertarButton(etiqueta);
                break;
            case "H1":
                insertarH1(etiqueta);
                break;
            case "PAR":
                insertarP(etiqueta);
                break;
            case "SCRIPTING":
                insertarScript(etiqueta);
                break;
            default:
//                System.out.println("no match");
        }

    }

    private void insertarBody(Etiqueta etiqueta) {
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        for (ParametroEtiqueta parametro : parametros) {
            String tipo = parametro.getTipo();
            if (tipo.equals("BACK")) {
                gStr += " style=\"background-color: " + parametro.getVal().replace("\"", "") + ";\"";
            }
        }

    }

    private void insertarSpan(Etiqueta etiqueta) {
        gStr += "<span";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        insertarParametros(parametros);
        gStr += ">";
        gStr += etiqueta.getContenido();
        gStr += "</span>\n";
    }

    private void insertarInput(Etiqueta etiqueta) {
        gStr += "<input";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        insertarParametros(parametros);
        gStr += ">";
//        gStr += etiqueta.getContenido();
//        gStr += "</input>\n";
    }

    private void insertarTextArea(Etiqueta etiqueta) {
        gStr += "<input";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        insertarParametros(parametros);
        gStr += ">\n";
    }

    private void insertarSelect(Etiqueta etiqueta) {
        gStr += "<select";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        insertarParametros(parametros);
        gStr += ">\n";
        List<Etiqueta> etiquetas = etiqueta.getEtiquetas();
        for (Etiqueta etiqueta1 : etiquetas) {
            insertarEtiqueta(etiqueta1);
        }
        gStr += "</select>\n";
    }

    private void insertarDiv(Etiqueta etiqueta) {
        gStr += "<div";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        insertarParametros(parametros);
        gStr += ">\n";
        List<Etiqueta> etiquetas = etiqueta.getEtiquetas();
        for (Etiqueta etiqueta1 : etiquetas) {
            insertarEtiqueta(etiqueta1);
        }
        gStr += "</div>\n";
    }

    private void insertarImg(Etiqueta etiqueta) {
        gStr += "<img";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        for (ParametroEtiqueta parametro : parametros) {
            String tipo = parametro.getTipo();
            switch (tipo) {
                case "SRC":
                    gStr += " src=\"" + parametro.getVal().replace("\"", "") + "\"";
                    break;
                case "WIDTH":
                    gStr += "width=\"" + parametro.getVal().replace("\"", "") + "\"";
                    break;
                case "HEIGHT":
                    gStr += "height=\"" + parametro.getVal().replace("\"", "") + "\"";
                    break;
                case "ALT":
                    gStr += "alt=\"" + parametro.getVal().replace("\"", "") + "\"";
                    break;
                case "IDETIQUETA":
                    gStr += "id=\"" + parametro.getVal().replace("\"", "") + "\"";
                    break;
                default:
//                    System.out.println("no match");
            }
        }
        gStr += ">\n";

    }

    private void insertarButton(Etiqueta etiqueta) {
        gStr += "<button";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        insertarParametros(parametros);
        gStr += ">\n";
        gStr += etiqueta.getContenido();
        gStr += "Boton</button>\n";
    }

    private void insertarH1(Etiqueta etiqueta) {
        gStr += "<h1";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        insertarParametros(parametros);
        gStr += ">\n";
        gStr += etiqueta.getContenido();
        gStr += "</h1>\n";
    }

    private void insertarP(Etiqueta etiqueta) {
        gStr += "<p";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        insertarParametros(parametros);
        gStr += ">\n";
        gStr += etiqueta.getContenido();
        gStr += "</p>\n";
    }

    private void insertarParametros(List<ParametroEtiqueta> parametros) {
        boolean insertado = false;
        boolean cerrado = false;
        for (ParametroEtiqueta parametro : parametros) {
            String tipo = parametro.getTipo();
            switch (tipo) {
                case "COLOR":
                    if (insertado == false) {
                        gStr += " style=\"color: " + parametro.getVal().replace("\"", "") + ";";
                        insertado = true;
                    } else {
                        gStr += " color: " + parametro.getVal().replace("\"", "") + ";";
                    }
                    break;
                case "BACK":
                    if (insertado == false) {
                        gStr += " style=\"background-color: " + parametro.getVal().replace("\"", "") + ";";
                        insertado = true;
                    } else {
                        gStr += " background-color: " + parametro.getVal().replace("\"", "") + ";";
                    }
                    break;
                case "FSIZE":
                    if (insertado == false) {
                        gStr += " style=\"font-family: " + parametro.getVal().replace("\"", "") + ";";
                        insertado = true;
                    } else {
                        gStr += " font-family: " + parametro.getVal().replace("\"", "") + ";";
                    }
                    break;
                case "FFAMILY":
                    if (insertado == false) {
                        gStr += " style=\"font-size: " + parametro.getVal().replace("\"", "") + ";";
                        insertado = true;
                    } else {
                        gStr += " font-size: " + parametro.getVal().replace("\"", "") + ";";
                    }
                    break;
                case "TEXTALIGN":
                    if (insertado == false) {
                        gStr += " style=\"text-align: " + parametro.getVal().replace("\"", "") + ";";
                        insertado = true;
                    } else {
                        gStr += " text-align: " + parametro.getVal().replace("\"", "") + ";";
                    }
                    break;
                case "IDETIQUETA":
                    if (insertado == true) {
                        gStr += "\"";
                        cerrado = true;
                    }
                    gStr += " id=\"" + parametro.getVal().replace("\"", "") + "\"";
                    break;
                case "TYPE":
                    if (insertado == true) {
                        gStr += "\"";
                        cerrado = true;
                    }
                    gStr += " type=\"" + parametro.getVal().replace("\"", "") + "\"";
                    break;
                case "COLS":
                    if (insertado == true) {
                        gStr += "\"";
                        cerrado = true;
                    }
                    gStr += " cols=\"" + parametro.getVal().replace("\"", "") + "\"";
                    break;
                case "ROWS":
                    if (insertado == true) {
                        gStr += "\"";
                        cerrado = true;
                    }
                    gStr += " rows=\"" + parametro.getVal().replace("\"", "") + "\"";
                    break;
                case "CLASS":
                    if (insertado == true) {
                        gStr += "\"";
                        cerrado = true;
                    }
                    gStr += " class=\"" + parametro.getVal().replace("\"", "") + "\"";
                    break;                    
                case "ONCLICK":
                    if (insertado == true) {
                        gStr += "\"";
                        cerrado = true;
                    }
                    gStr += " onclick=\"" + parametro.getVal().replace("\"", "") + "\"";
                    break;
                default:
//                System.out.println("no match");
            }
        }
        if (cerrado == false) {
            gStr += "\"";
        }

    }

    private void insertarScript(Etiqueta etiqueta) {
        gStr += "<script>\n";
        List<Proceso> procesos = etiqueta.getProcesos();
        for (Proceso proceso : procesos) {
            if (proceso.getTipo().equals("ONLOAD") == false) {
                gStr += "function " + proceso.getTipo() + "(){\n";
                List<Instruccion> instrucciones = proceso.getInstrucciones();
                for (Instruccion instruccione : instrucciones) {
                    insertarInstruccion(instruccione);
                }
                gStr += "}";
            } else {
                List<Instruccion> instrucciones = proceso.getInstrucciones();
                for (Instruccion instruccione : instrucciones) {
                    insertarInstruccion(instruccione);
                }
            }
        }
        insertarEspeciales();
        limpiar();
        gStr += "\n</script>\n";
    }

    private void insertarEspeciales() {
        if (fAsc) {
            gStr += "function asc(palabra){\n"
                    + "	var letras = new Array(palabra.length);     \n"
                    + "    for(let i = 0; i<palabra.length;i++){\n"
                    + "    	letras[i] = palabra.charAt(i);\n"
                    + "    }\n"
                    + "    return letras.sort().join('');    \n"
                    + "}\n";
        }
        if (this.fCarAlea) {
            gStr += "function  randomStr(){\n"
                    + "    const characters ='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';            \n"
                    + "    var result1 = characters.charAt(Math.floor(Math.random() * characters.length));    \n"
                    + "    return result1;\n"
                    + "}";
        }
        if (this.fNumAlea) {
            gStr += "function  randomNum(){\n"
                    + "    const characters ='0123456789';            \n"
                    + "    var result1 = characters.charAt(Math.floor(Math.random() * characters.length));    \n"
                    + "    return result1;\n"
                    + "}";
        }
        if (this.fReverse) {
            gStr += "function reverseG(palabra){\n"
                    + "	var letras = new Array(palabra.length);     \n"
                    + "    for(let i = 0; i<palabra.length;i++){\n"
                    + "    	letras[i] = palabra.charAt(i);\n"
                    + "    }\n"
                    + "    return letras.reverse().join('');    \n"
                    + "}";
        }
    }

    private void insertarInstruccion(Instruccion instruccion) {
        String tipo = instruccion.getTipo();
        switch (tipo) {
            case "declaracion":
                insertarDeclaracion(instruccion);
                break;
            case "asignacion":
                insertarAsignacion(instruccion);
                break;
            case "INSIF":
                insertarIf(instruccion);
                break;
            case "INSELSE":
                insertarElse(instruccion);
                break;
            case "INSELSEIF":
                insertarElseIf(instruccion);
                break;
            case "REPEAT":
                insertarFor(instruccion);
                break;
            case "WHILE":
                insertarWhile(instruccion);
                break;
            case "INSERT":
                insertarInsert(instruccion);
                break;
            case "ALERTINFO":
                insertarAlert(instruccion);
                break;
            default:
//                throw new AssertionError();
        }
    }

    private void insertarDeclaracion(Instruccion instruccion) {
        DeclAsign decl = (DeclAsign) instruccion;
        gStr += "var ";
        List<String> variables = decl.getVariables();
        int cont = 0;
        for (String variable : variables) {
            if (cont > 0) {
                gStr += ",";
            }
            gStr += variable;
            cont++;
        }
        cont = 0;
        List<String> valores = decl.getValores();
        insertarValores(valores);
//        for (String valor : valores) {
//            if (cont == 0) {
//                gStr += " = ";
//            }
//            gStr += valor;
//        }
        gStr += ";\n";
    }

    private void insertarAsignacion(Instruccion instruccion) {
        DeclAsign decl = (DeclAsign) instruccion;
        List<String> variables = decl.getVariables();
        int cont = 0;
        for (String variable : variables) {
            if (cont > 0) {
                gStr += ",";
            }
            gStr += variable;
            cont++;
        }
        cont = 0;
        List<String> valores = decl.getValores();
        insertarValores(valores);
        gStr += ";\n";
    }

    private void insertarValores(List<String> valores) {
        int cont = 0;
        for (String valor : valores) {
            if (cont == 0) {
                gStr += " = ";
            }
            if (valor.contains("funcGetElement:")) {
                gStr += " document.getElementById(" + valor.replace("funcGetElement:", "").replace("\'", "") + ")";
            } else if (valor.contains("funcAsc:")) {
                String val = valor.replace("funcAsc:", "");
                if (val.contains("\'")) {
                    val = val.replace("\'", "");
                    val = "\"" + val + "\"";
                }
                gStr += " asc(" + val + ")";
                this.fAsc = true;
            } else if (valor.contains("funcDesc:")) {
                String val = valor.replace("funcDesc:", "");
                if (val.contains("\'")) {
                    val = val.replace("\'", "");
                    val = "\"" + val + "\"";
                }
                gStr += " desc(" + val + ")";
                this.fDesc = true;
            } else if (valor.contains("funcLetPar:")) {
                String val = valor.replace("funcLetPar:", "");
                if (val.contains("\'")) {
                    val = val.replace("\'", "");
                    val = "\"" + val + "\"";
                }
                gStr += " letPar(" + val + ")";
                this.fLetPar = true;
            } else if (valor.contains("funcLetImPar:")) {
                String val = valor.replace("funcLetImPar:", "");
                if (val.contains("\'")) {
                    val = val.replace("\'", "");
                    val = "\"" + val + "\"";
                }
                gStr += " letImPar(" + val + ")";
                this.fLetImpar = true;
            } else if (valor.contains("funcReverse:")) {
                String val = valor.replace("funcReverse:", "");
                if (val.contains("\'")) {
                    val = val.replace("\'", "");
                    val = "\"" + val + "\"";
                }
                gStr += " reverseG(" + val + ")";
                this.fReverse = true;
            } else if (valor.contains("funcCarAleatorio")) {
                String val = valor.replace("funcCarAleatorio", "");
                gStr += " randomStr()";
                this.fCarAlea = true;
            } else if (valor.contains("funcNumAleatorio")) {
                String val = valor.replace("funcCarAleatorio", "");
                gStr += " randomNum()";
                this.fNumAlea = true;
            } else {
                gStr += valor;
            }
            cont++;
        }
    }

    private void insertarIf(Instruccion instruccion) {
        InsIf insif = (InsIf) instruccion;
        gStr += "if ( ";
        List<String> condicion = insif.getCondicionValores();
        for (String string : condicion) {
            gStr += string;
        }
        gStr += ") {\n";
        List<Instruccion> instrucciones = insif.getInstrucciones();
        for (Instruccion instruccione : instrucciones) {
            insertarInstruccion(instruccione);
        }
        gStr += "\n}\n";
        List<Instruccion> elses = insif.getElses();
        for (Instruccion el : elses) {
            insertarInstruccion(el);
        }
    }

    private void insertarElse(Instruccion instruccion) {
        InsIf insif = (InsIf) instruccion;
        gStr += "else  {\n";
        List<Instruccion> instrucciones = insif.getInstrucciones();
        for (Instruccion instruccione : instrucciones) {
            insertarInstruccion(instruccione);
        }
        gStr += "\n}\n";
    }

    private void insertarElseIf(Instruccion instruccion) {
        InsIf insif = (InsIf) instruccion;
        gStr += "else if ( ";
        List<String> condicion = insif.getCondicionValores();
        for (String string : condicion) {
            gStr += string;
        }
        gStr += ") {\n";
        List<Instruccion> instrucciones = insif.getInstrucciones();
        for (Instruccion instruccione : instrucciones) {
            insertarInstruccion(instruccione);
        }
        gStr += "\n}\n";
    }

    private void insertarFor(Instruccion instruccion) {
        Ciclo ciclo = (Ciclo) instruccion;
        gStr += "for (";
        DeclAsign rep = (DeclAsign) ciclo.getAsigDecl();
        String tipo = rep.getTipo();
        if (tipo.equals("declaracion")) {
            gStr += "var ";
        }
        String variableIns = "";
        List<String> variables = rep.getVariables();
        int cont = 0;
        for (String variable : variables) {
            if (cont > 0) {
                gStr += ",";
            }
            variableIns = variable;
            gStr += variable;
            cont++;
        }
        cont = 0;
        List<String> valores = rep.getValores();
        for (String valor : valores) {
            if (cont == 0) {
                gStr += " = ";
            }
            gStr += valor;
        }
        gStr += ";";
        gStr += variableIns + "<=";
        List<String> expNum = ciclo.getExpresionNum();
        for (String string : expNum) {
            gStr += string;
        }
        gStr += ";";
        gStr += variableIns + "++";
        gStr += "){";
        List<Instruccion> instrucciones = ciclo.getInstrucciones();
        for (Instruccion instruccione : instrucciones) {
            insertarInstruccion(instruccione);
        }
        gStr += "}";
    }

    private void insertarWhile(Instruccion instruccion) {
        InsIf insif = (InsIf) instruccion;
        gStr += "while ( ";
        List<String> condicion = insif.getCondicionValores();
        for (String string : condicion) {
            gStr += string;
        }
        gStr += ") {\n";
        List<Instruccion> instrucciones = insif.getInstrucciones();
        for (Instruccion instruccione : instrucciones) {
            insertarInstruccion(instruccione);
        }
        gStr += "\n}\n";
    }

    private void insertarInsert(Instruccion instruccion) {
        InsInsert insInsert = (InsInsert) instruccion;
        List<String> tokens = insInsert.getTokens();
        for (String token : tokens) {
            gStr += "document.write(";
            if (token.contains("\'")) {
                gStr += "\"" + token.replace("\'", "") + "\"";
            } else {
                gStr += token;
            }
            gStr += ");\n";
        }
    }

    private void insertarAlert(Instruccion instruccion) {
        AlertInfo insInsert = (AlertInfo) instruccion;
        String tokens = insInsert.getMensaje();
        gStr += "alert(";
        if (tokens.contains("\'")) {
            gStr += "\"" + tokens.replace("\'", "") + "\"";
        } else {
            gStr += tokens;
        }
        gStr += ");\n";
    }

    public Captcha getCaptcha() {
        return captcha;
    }

    public void setCaptcha(Captcha captcha) {
        this.captcha = captcha;
    }

    public String getgStr() {
        return gStr;
    }

    public void setgStr(String gStr) {
        this.gStr = gStr;
    }

}
