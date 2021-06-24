/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generadores;

import analizadores.LexerGCIC;
import analizadores.LexerInsert;
import analizadores.ParserGCIC;
import analizadores.ParserInsert;
import controladores.ControlSemantico;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import objetos.Captcha;
import objetos.ErrorCom;
import objetos.Etiqueta;
import objetos.Instruccion;
import objetos.Instrucciones.AlertInfo;
import objetos.Instrucciones.Ciclo;
import objetos.Instrucciones.DeclAsign;
import objetos.Instrucciones.InsIf;
import objetos.Instrucciones.InsInsert;
import objetos.ParametroEtiqueta;
import objetos.Proceso;
import objetos.Simbolo;

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
    private boolean hayInsertar;
    private List<String> nombProcIns;
    private String nombProc;
    private String insertarStr;
    private int contProcesos = 0;
    private int contEt = 0;
    private List<String> variablesGlobales;
    private String procesoAct;
    private List<Simbolo> simbolos;

    public GenStrHTMLJS(Captcha captcha) {
        this.captcha = captcha;
        this.gStr = "";
        this.hayInsertar = false;
        this.insertarStr = "";
        this.nombProc = "";
        this.variablesGlobales = new ArrayList<>();
        this.simbolos = new ArrayList<>();
        this.procesoAct = "";
        limpiarListaProcesos();
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

    private void limpiarListaProcesos() {
        nombProcIns = new ArrayList<>();
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

    private void insertarDeclaracion(Etiqueta etiqueta) {
        String tipo = etiqueta.getTipo();
        List<Etiqueta> etiquetas = new ArrayList<>();
        switch (tipo) {
            case "HEAD":
                etiquetas = etiqueta.getEtiquetas();
                for (Etiqueta etiqueta1 : etiquetas) {
                    insertarDeclaracion(etiqueta1);
                }
                break;
            case "BODY":
                etiquetas = etiqueta.getEtiquetas();
                for (Etiqueta etiqueta1 : etiquetas) {
                    insertarDeclaracion(etiqueta1);
                }
                break;
            case "DIV":
                etiquetas = etiqueta.getEtiquetas();
                for (Etiqueta etiqueta1 : etiquetas) {
                    insertarDeclaracion(etiqueta1);
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
//        gStr += "<script>\n";
        List<Proceso> procesos = etiqueta.getProcesos();
        for (Proceso proceso : procesos) {
            this.procesoAct = proceso.getTipo();
            List<Instruccion> instrucciones = proceso.getInstrucciones();
            for (Instruccion instruccione : instrucciones) {
                insertarDecl(instruccione);
            }
        }
    }

    private void insertarDecl(Instruccion instruccion) {
        String tipo = instruccion.getTipo();
        switch (tipo) {
            case "declaracion":
                DeclAsign decl = (DeclAsign) instruccion;
                String modo = decl.getModo();
                if (modo.equals("GLOBAL")) {
                    List<String> variables = decl.getVariables();
                    for (String variable : variables) {
                        gStr += "var ";
                        gStr += variable;
                        List<String> valores = decl.getValores();
                        insertarValores(valores);
                        gStr += ";\n";
                    }
                    String tipoVar = decl.getTipoVar();
                    String modoVar = decl.getModo();
                    for (String variable : variables) {
                        //identificador, valor,tipo,modo,procedimiento
                        gStr += "variablesGlobales.push(new Simbolo(\"" + variable + "\""
                                + "," + variable + ",\"" + tipoVar + "\",\"" + modoVar + "\",\"" + this.procesoAct + "\"));\n";
                        this.variablesGlobales.add(variable);
                    }
                }
                List<String> variables = decl.getVariables();
                String tipoVar = decl.getTipoVar();
                for (String variable : variables) {
                    Simbolo sim = new Simbolo();
                    sim.setIdentificador(variable);
                    sim.setTipo(tipoVar);
                    this.simbolos.add(sim);
                }
                break;
            default:
//                throw new AssertionError();
        }
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
                insertarEspeciales();
                insertarEstilo();
                etiquetas = etiqueta.getEtiquetas();
                this.gStr += "<script>\n";
                insertarObjetoSimbolo();
                for (Etiqueta etiquet : etiquetas) {
                    insertarDeclaracion(etiquet);
                }
                this.gStr += "</script>\n";
                for (Etiqueta etiqueta1 : etiquetas) {
                    insertarEtiqueta(etiqueta1);
                }
                gStr += etiqueta.getContenido();
                limpiar();
                insertarTablaSimbolos();
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

    private void insertarObjetoSimbolo() {
        this.gStr += "  var variablesGlobales = [];\n";
        this.gStr += "  var variablesLocales = [];\n";
        this.gStr += "  function Simbolo(identificador, valor,tipo,modo,procedimiento) {\n"
                + "  this.identificador = identificador;\n"
                + "  this.valor = valor;\n"
                + "  this.tipo = tipo;\n"
                + "  this.modo = modo;\n"
                + "  this.procedimiento = procedimiento;\n"
                + "}\n";
    }

    private void insertarEstilo() {
        gStr += "<style>\n"
                + "table, th, td {\n"
                + "  border: 1px solid black;\n"
                + "  border-collapse: collapse;\n"
                + "}\n"
                + "th, td {\n"
                + "  padding: 5px;\n"
                + "}\n"
                + ".center {\n"
                + "  display: block;\n"
                + "  margin-right: auto;\n"
                + "  margin-left: auto;\n"
                + "}\n"
                + ".right {\n"
                + "  display: block;\n"
                + "  margin-left: auto;\n"
                + "}\n"
                + ".left {\n"
                + "  display: block;\n"
                + "  margin-right: auto;  \n"
                + "}"
                + "</style>";
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
        insertarParametros(parametros, "SPAN");
        gStr += ">";
        gStr += etiqueta.getContenido();
        gStr += "</span>\n";
    }

    private void insertarInput(Etiqueta etiqueta) {
        gStr += "<input";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        insertarParametros(parametros, "INPUT");
        gStr += ">";
//        gStr += etiqueta.getContenido();
//        gStr += "</input>\n";
    }

    private void insertarTextArea(Etiqueta etiqueta) {
        gStr += "<input";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        insertarParametros(parametros, "TEXTAREA");
        gStr += ">\n";
    }

    private void insertarSelect(Etiqueta etiqueta) {
        gStr += "<select";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        insertarParametros(parametros, "SELECT");
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
        insertarParametros(parametros, "DIV");
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
        insertarParametros(parametros, "BUTTON");
        gStr += ">\n";
        gStr += etiqueta.getContenido();
        gStr += "</button>\n";
    }

    private void insertarH1(Etiqueta etiqueta) {
        gStr += "<h1";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        insertarParametros(parametros, "H1");
        gStr += ">\n";
        gStr += etiqueta.getContenido();
        gStr += "</h1>\n";
    }

    private void insertarP(Etiqueta etiqueta) {
        gStr += "<p";
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        insertarParametros(parametros, "P");
        gStr += ">\n";
        gStr += etiqueta.getContenido();
        gStr += "</p>\n";
    }

    private void insertarParametros(List<ParametroEtiqueta> parametros, String tipoEtiqueta) {
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
                case "FFAMILY":
                    if (insertado == false) {
                        gStr += " style=\"font-family: " + parametro.getVal().replace("\"", "") + ";";
                        insertado = true;
                    } else {
                        gStr += " font-family: " + parametro.getVal().replace("\"", "") + ";";
                    }
                    break;
                case "FSIZE":
                    if (insertado == false) {
                        gStr += " style=\"font-size: " + parametro.getVal().replace("\"", "") + ";";
                        insertado = true;
                    } else {
                        gStr += " font-size: " + parametro.getVal().replace("\"", "") + ";";
                    }
                    break;
                case "TEXTALIGN":
                    if (tipoEtiqueta.equals("INPUT") || tipoEtiqueta.equals("SPAN")) {
                        if (insertado == true) {
                            gStr += "\"";
                            cerrado = true;
                        }
                        gStr += " class=\"" + parametro.getVal().replace("\"", "") + "\"";
                    } else {
                        if (insertado == false) {
                            gStr += " style=\"text-align: " + parametro.getVal().replace("\"", "") + ";";
                            insertado = true;
                        } else {
                            gStr += " text-align: " + parametro.getVal().replace("\"", "") + ";";
                        }
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
        revisarSiHayInsertar(etiqueta);
        gStr += "<script>\n";
        List<Proceso> procesos = etiqueta.getProcesos();
        for (Proceso proceso : procesos) {
            this.procesoAct = proceso.getTipo();
            this.nombProc = "div" + proceso.getTipo();
            if (proceso.getTipo().equals("ONLOAD") == false) {
                gStr += "function " + proceso.getTipo() + "(){\n";
                //document.querySelector("#divONLOAD");	
                this.gStr += "for (let i = variablesLocales.length; i > 0; i--) {\n"
                        + "  variablesLocales.pop();\n"
                        + "}\n";
                gStr += "\n var app" + contProcesos + " = document.getElementById(\"" + this.nombProc + "\");\n";
                gStr += "app" + contProcesos + ".textContent = \"\";\n";
                List<Instruccion> instrucciones = proceso.getInstrucciones();
                for (Instruccion instruccione : instrucciones) {
                    insertarInstruccion(instruccione);
                }
                insertarReinicioVariablesGlobales();
                gStr += "}";
                if (this.hayInsertar == true) {
                    this.nombProcIns.add(proceso.getTipo());
                    this.hayInsertar = false;
                }
            } else {
                gStr += "\n var app" + contProcesos + " = document.getElementById(\"" + this.nombProc + "\");\n";
                gStr += "app" + contProcesos + ".textContent = \"\";\n";
                List<Instruccion> instrucciones = proceso.getInstrucciones();
                for (Instruccion instruccione : instrucciones) {
                    insertarInstruccion(instruccione);
                }
            }
            contProcesos++;
        }
        gStr += "\n</script>\n";
    }

    private void revisarSiHayInsertar(Etiqueta etiqueta) {
        boolean estaInsertar = false;
        List<Proceso> procesos = etiqueta.getProcesos();
        for (Proceso proceso : procesos) {
            estaInsertar = true;
//            estaInsertar = false;            
//            List<Instruccion> instrucciones = proceso.getInstrucciones();
//            for (Instruccion instruccione : instrucciones) {
//                String tipo = instruccione.getTipo();
//                if (tipo.equals("INSERT")) {
//                    estaInsertar = true;
//                }
//            }
            if (estaInsertar) {
                this.nombProcIns.add(proceso.getTipo());
            }
        }
        insertarDivsIns();
        limpiarListaProcesos();
    }

    private void insertarDivsIns() {
        for (String nombProcIn : nombProcIns) {
            gStr += "\n<div id=\"div" + nombProcIn + "\">";
            gStr += "</div>\n";
        }
    }

    private void insertarEspeciales() {
        fAsc = true;
        fDesc = true;
        fLetPar = true;
        fLetImpar = true;
        fReverse = true;
        fCarAlea = true;
        fNumAlea = true;
        gStr += "\n<script>\n";
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
                    + "}\n";
        }
        if (this.fNumAlea) {
            gStr += "function  randomNum(){\n"
                    + "    const characters ='0123456789';            \n"
                    + "    var result1 = characters.charAt(Math.floor(Math.random() * characters.length));    \n"
                    + "    return result1;\n"
                    + "}\n";
        }
        if (this.fReverse) {
            gStr += "function reverseG(palabra){\n"
                    + "	var letras = new Array(palabra.length);     \n"
                    + "    for(let i = 0; i<palabra.length;i++){\n"
                    + "    	letras[i] = palabra.charAt(i);\n"
                    + "    }\n"
                    + "    return letras.reverse().join('');    \n"
                    + "}\n";
        }
        if (this.fDesc) {
            gStr += "function desc(palabra){\n"
                    + "	var letras = new Array(palabra.length);     \n"
                    + "    for(let i = 0; i<palabra.length;i++){\n"
                    + "    	letras[i] = palabra.charAt(i);\n"
                    + "    }\n"
                    + "    var cadena = letras.sort().join('');\n"
                    + "    var x = cadena.length;\n"
                    + "	var cadenaInvertida = \"\";\n"
                    + "\n"
                    + "	while (x>=0) {\n"
                    + "  		cadenaInvertida = cadenaInvertida + cadena.charAt(x);\n"
                    + "  		x--;\n"
                    + "	}\n"
                    + "    return cadenaInvertida;    \n"
                    + "}\n";
        }
        if (this.fLetPar) {
            gStr += "function letPar(palabra){\n"
                    + "	var letras = new Array(palabra.length); \n"
                    + "    var cont = 0;\n"
                    + "    for(let i = 0; i<palabra.length;i++){\n"
                    + "    	if(cont == 0){\n"
                    + "        	letras[i] = palabra.charAt(i);\n"
                    + "            cont++;\n"
                    + "        }else {\n"
                    + "        	letras[i] = palabra.charCodeAt(i);\n"
                    + "        	cont--;\n"
                    + "        }\n"
                    + "    	\n"
                    + "    }    \n"
                    + "    return letras.join(\'\');    \n"
                    + "}";
        }

        if (this.fLetImpar) {
            gStr += "function letImPar(palabra){\n"
                    + "	var letras = new Array(palabra.length); \n"
                    + "    var cont = 0;\n"
                    + "    for(let i = 0; i<palabra.length;i++){\n"
                    + "    	if(cont == 1){\n"
                    + "        	letras[i] = palabra.charAt(i);\n"
                    + "            cont--;\n"
                    + "        }else {\n"
                    + "        	letras[i] = palabra.charCodeAt(i);\n"
                    + "        	cont++;\n"
                    + "        }\n"
                    + "    	\n"
                    + "    }    \n"
                    + "    return letras.join(\'\');    \n"
                    + "}";
        }
        gStr += "\n</script>\n";
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
            case "REDIRECT":
                insertarRedirect();
                break;
            case "EXIT":
                gStr += "return;\n";
                break;
            default:
//                throw new AssertionError();
        }
    }

    private void insertarDeclaracion(Instruccion instruccion) {
        DeclAsign decl = (DeclAsign) instruccion;
        String modo = decl.getModo();
        if (modo.equals("GLOBAL")) {

        } else {
            List<String> variables = decl.getVariables();
            for (String variable : variables) {
                gStr += "var ";
                gStr += variable;
                List<String> valores = decl.getValores();
                insertarValores(valores);
                gStr += ";\n";
            }
            String tipoVar = decl.getTipoVar();
            String modoVar = decl.getModo();
            for (String variable : variables) {
                //identificador, valor,tipo,modo,procedimiento
                if (this.procesoAct.equals("ONLOAD")) {
                    gStr += "variablesGlobales.push(new Simbolo(\"" + variable + "\""
                            + "," + variable + ",\"" + tipoVar + "\",\"" + modoVar + "\",\"" + this.procesoAct + "\"));\n";
                } else {
                    gStr += "variablesLocales.push(new Simbolo(\"" + variable + "\""
                            + "," + variable + ",\"" + tipoVar + "\",\"" + modoVar + "\",\"" + this.procesoAct + "\"));\n";
                }

            }
        }
    }

    private void insertarAsignacion(Instruccion instruccion) {
        DeclAsign decl = (DeclAsign) instruccion;
        List<String> variables = decl.getVariables();
        for (String variable : variables) {
            gStr += variable;
            List<String> valores = decl.getValores();
            insertarValores(valores);
            gStr += ";\n";
        }
        String tipoVar = decl.getTipoVar();
        String modoVar = decl.getModo();
        for (String variable : variables) {
            //identificador, valor,tipo,modo,procedimiento
            if (this.procesoAct.equals("ONLOAD")) {
                gStr += "variablesGlobales.push(new Simbolo(\"" + variable + "\""
                        + "," + variable + ",\"" + tipoVar + "\",\"" + modoVar + "\",\"" + this.procesoAct + "\"));\n";
            } else {
                gStr += "variablesLocales.push(new Simbolo(\"" + variable + "\""
                        + "," + variable + ",\"" + tipoVar + "\",\"" + modoVar + "\",\"" + this.procesoAct + "\"));\n";
            }

        }
    }

    private void insertarValores(List<String> valores) {
        int cont = 0;
        for (String valor : valores) {
            if (cont == 0) {
                gStr += " = ";
            }
            if (valor.contains("funcGetElement:")) {
                gStr += " document.getElementById(\"" + valor.replace("funcGetElement:", "").replace("\'", "") + "\").value";
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
                insertarSoloValor(valor, valores, cont);
            }
            cont++;
        }
    }

    private void insertarSoloValor(String valor, List<String> valores, int cont) {
        boolean esVariable = false;
        Simbolo simVar = null;
        for (Simbolo simbolo : this.simbolos) {
            if (simbolo.getIdentificador().equals(valor)) {
                simVar = simbolo;
                esVariable = true;
                break;
            }
        }
        if (esVariable) {
            if (simVar.getTipo().equals("CHAR")) {
                //revisar si esta sumando un entero
                boolean haySumaMult = false;
                boolean hayEnteroDecimal = false;
                boolean soloCodigo = false;
                for (int i = (cont + 1); i < valores.size(); i++) {
                    String valorN = valores.get(i);
                    if (valorN.contains("*") || valorN.contains("+")) {
                        haySumaMult = true;
                    } else {
                        Simbolo simVar2 = null;
                        boolean esVariable2 = false;
                        for (Simbolo simbolo : this.simbolos) {
                            if (simbolo.getIdentificador().equals(valorN)) {
                                simVar2 = simbolo;
                                esVariable2 = true;
                                break;
                            }
                        }
                        if (esVariable2) {
                            if (simVar2.getTipo().equals("INTEGER") || simVar2.getTipo().equals("DECIMAL")) {
                                hayEnteroDecimal = true;
                            }
                        }
                    }
                    if (haySumaMult == true && hayEnteroDecimal == true) {
                        soloCodigo = true;
                        break;
                    }
                }
                if (soloCodigo) {
                    gStr += valor + ".charCodeAt(0)";
                }
            } else {
                gStr += valor;
            }
        } else {
            gStr += valor;
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
//        List<String> valores = rep.getValores();
        List<String> valores = ciclo.getCondicionValores();
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
        gStr += "){\n";
        List<Instruccion> instrucciones = ciclo.getInstrucciones();
        for (Instruccion instruccione : instrucciones) {
            insertarInstruccion(instruccione);
        }
        gStr += "}\n";
    }

    private void insertarWhile(Instruccion instruccion) {
        Ciclo insif = (Ciclo) instruccion;
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
        this.hayInsertar = true;
        this.insertarStr = "<C_GCIC>";
        InsInsert insInsert = (InsInsert) instruccion;
        List<String> tokens = insInsert.getTokens();
        for (String token : tokens) {
            String[] nuevosTokens = token.split(",");
            for (int i = 0; i < nuevosTokens.length; i++) {
                String nuevoToken = nuevosTokens[i];
                if (nuevoToken.contains("\'")) {
                    this.insertarStr += nuevoToken.replace("\'", "");
                } else {
                    this.insertarStr += " <C_FINVARIABLESCRIP>" + nuevoToken + " <C_FINVARIABLESCRIP>";
//                    this.insertarStr += nuevoToken.replace("\'", "");
                }
            }
        }
        this.insertarStr += "</C_GCIC>";
        //Leer etiquetas
        List<Etiqueta> etiquetasInsert = leerEtiquetasInsertar();
        if (etiquetasInsert != null) {
            for (Etiqueta etiqueta : etiquetasInsert) {
                insertarEtiquetaInsert(etiqueta);
            }
        }

    }

    private List<Etiqueta> leerEtiquetasInsertar() {
        List<Etiqueta> etiquetasInsertar = new ArrayList<>();
        List<ErrorCom> erroresCom = new ArrayList<>();
        StringReader reader = new StringReader(this.insertarStr);
        LexerInsert lexico = new LexerInsert(reader);
        ParserInsert parser = new ParserInsert(lexico);
        try {
            parser.parse();
            erroresCom = parser.getErroresCom();
            etiquetasInsertar = parser.getEtiquetasTotal();
//            if (erroresCom.isEmpty() == true) {                
//                ControlSemantico control = new ControlSemantico(etiquetasInsertar, erroresCom);
//                control.validar();
//                erroresCom = control.getErrores();
//            }
        } catch (Exception ex) {
            System.out.println("Error irrecuperable");
            System.out.println("Causa: " + ex.getCause());
            System.out.println("Causa2: " + ex.toString());
            System.out.println(ex);
            ex.printStackTrace();
        }
        //Validar Errores de compilacion
        if (erroresCom.isEmpty()) {
            return etiquetasInsertar;
        }
        return null;
    }

    private void insertarEtiquetaInsert(Etiqueta etiqueta) {
        String tipo = etiqueta.getTipo();
        List<Etiqueta> etiquetas = new ArrayList<>();
        switch (tipo) {
            case "SPAM":
                gStr += "var etiqII" + this.contEt + " = document.createElement(\"span\");\n";
                gStr += "app" + contProcesos + ".appendChild(etiqII" + this.contEt + ");\n";
                insertarParametrosEtiquetaInsert(etiqueta);
                this.contEt++;
                break;
            case "INPUT":
                gStr += "var etiqII" + this.contEt + " = document.createElement(\"input\");\n";
                gStr += "app" + contProcesos + ".appendChild(etiqII" + this.contEt + ");\n";
                insertarParametrosEtiquetaInsert(etiqueta);
                this.contEt++;
                break;
            case "TEXTAREA":
                gStr += "var etiqII" + this.contEt + " = document.createElement(\"textArea\");\n";
                gStr += "app" + contProcesos + ".appendChild(etiqII" + this.contEt + ");\n";
                insertarParametrosEtiquetaInsert(etiqueta);
                this.contEt++;
                break;
            case "SELECT":
                gStr += "var etiqII" + this.contEt + " = document.createElement(\"select\");\n";
                gStr += "app" + contProcesos + ".appendChild(etiqII" + this.contEt + ");\n";
                insertarParametrosEtiquetaInsert(etiqueta);
                this.contEt++;
                break;
            case "OPTION":
                gStr += "var etiqII" + this.contEt + " = document.createElement(\"option\");\n";
                gStr += "app" + contProcesos + ".appendChild(etiqII" + this.contEt + ");\n";
                insertarParametrosEtiquetaInsert(etiqueta);
                this.contEt++;
                break;
            case "DIV":
                gStr += "var etiqII" + this.contEt + " = document.createElement(\"div\");\n";
                gStr += "app" + contProcesos + ".appendChild(etiqII" + this.contEt + ");\n";
                insertarParametrosEtiquetaInsert(etiqueta);
                this.contEt++;
                break;
            case "IMG":
                gStr += "var etiqII" + this.contEt + " = document.createElement(\"img\");\n";
                gStr += "app" + contProcesos + ".appendChild(etiqII" + this.contEt + ");\n";
                insertarParametrosEtiquetaInsert(etiqueta);
                this.contEt++;
                break;
            case "BR":
                gStr += "var etiqII" + this.contEt + " = document.createElement(\"br\");\n";
                gStr += "app" + contProcesos + ".appendChild(etiqII" + this.contEt + ");\n";
                insertarParametrosEtiquetaInsert(etiqueta);
                this.contEt++;
                break;
            case "BUTTON":
                gStr += "var etiqII" + this.contEt + " = document.createElement(\"button\");\n";
                gStr += "app" + contProcesos + ".appendChild(etiqII" + this.contEt + ");\n";
                insertarParametrosEtiquetaInsert(etiqueta);
                this.contEt++;
                break;
            case "H1":
                gStr += "var etiqII" + this.contEt + " = document.createElement(\"h1\");\n";
                gStr += "app" + contProcesos + ".appendChild(etiqII" + this.contEt + ");\n";
                insertarParametrosEtiquetaInsert(etiqueta);
                this.contEt++;
                break;
            case "PAR":
                gStr += "var etiqII" + this.contEt + " = document.createElement(\"p\");\n";
                gStr += "app" + contProcesos + ".appendChild(etiqII" + this.contEt + ");\n";
                insertarParametrosEtiquetaInsert(etiqueta);
                this.contEt++;
                break;
            case "CONTENIDO":
                gStr += "etiqII" + (this.contEt - 1) + ".textContent += \"" + etiqueta.getContenido().replace("\"", "") + "\";\n";
                break;
            case "VARIABLE":
                gStr += "etiqII" + (this.contEt - 1) + ".textContent += " + etiqueta.getContenido().replace("\"", "") + ";\n";
                break;
            default:
//                System.out.println("no match");
        }
    }

    private void insertarParametrosEtiquetaInsert(Etiqueta etiqueta) {
        List<ParametroEtiqueta> parametros = etiqueta.getParametrosEt();
        String tipoEtiqueta = etiqueta.getTipo();
        for (ParametroEtiqueta parametro : parametros) {
            String tipo = parametro.getTipo();
            String valor = parametro.getVal().replace("\"", "");
            switch (tipo) {
                case "COLOR":
                    gStr += "etiqII" + this.contEt + ".style.color = \"" + valor + "\";\n";
                    break;
                case "BACK":
                    gStr += "etiqII" + this.contEt + ".style.backgroundColor = \"" + valor + "\";\n";
                    break;
                case "FFAMILY":
                    gStr += "etiqII" + this.contEt + "style.fontSize = \"" + valor + "\";\n";
                    break;
                case "FSIZE":
                    gStr += "etiqII" + this.contEt + ".style.fontFamily = \"" + valor + "\";\n";
                    break;
                case "TEXTALIGN":
                    if (tipoEtiqueta.equals("SPAM") | tipoEtiqueta.equals("INPUT")) {
                        gStr += "etiqII" + this.contEt + ".style.classList = \"" + valor + "\";\n";
                    }
                    gStr += "etiqII" + this.contEt + ".style.textAlign = \"" + valor + "\";\n";
                    break;
                case "IDETIQUETA":
                    gStr += "etiqII" + this.contEt + ".id = \"" + valor + "\";\n";
                    break;
                case "TYPE":
                    gStr += "etiqII" + this.contEt + ".type = \"" + valor + "\";\n";
                    break;
                case "COLS":
                    gStr += "etiqII" + this.contEt + ".cols = \"" + valor + "\";\n";
                    break;
                case "ROWS":
                    gStr += "etiqII" + this.contEt + ".rows = \"" + valor + "\";\n";
                    break;
                case "CLASS":
                    gStr += "etiqII" + this.contEt + ".classname = \"" + valor + "\";\n";
                    break;
                case "ONCLICK":
                    gStr += "etiqII" + this.contEt + ".setAttribute('onclick', \"" + valor + "\");\n";
                    break;
                default:
//                System.out.println("no match");
            }
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

    private void insertarTablaSimbolos() {
        gStr += "<br><br>\n<input type='button' onclick='mostrarTablaSimbolos();'  class='btn btn-success' value='Tabla de simbolos'>";
        gStr += "\n<div id=\"tablaSimbolos\">";
        gStr += "</div>\n";
        gStr += "\n<script>";
        gStr += "function mostrarTablaSimbolos(){\n"
                + "let divTabla = document.getElementById(\"tablaSimbolos\");\n"
                + "divTabla.textContent = \"\";\n"
                + "let tabla = document.createElement(\"table\");\n"
                + "let thead = document.createElement(\"thead\");\n"
                + "let tbody = document.createElement(\"tbody\");\n"
                + "let trHead = document.createElement(\"tr\");\n"
                + "let thTitul1 = document.createElement(\"th\");\n"
                + "thTitul1.textContent = \"Identificador\";\n"
                + "trHead.appendChild(thTitul1);\n"
                + "let thTitul2 = document.createElement(\"th\");\n"
                + "thTitul2.textContent = \"Valor Actual\";\n"
                + "trHead.appendChild(thTitul2);\n"
                + "let thTitul3 = document.createElement(\"th\");\n"
                + "thTitul3.textContent = \"Tipo\";\n"
                + "trHead.appendChild(thTitul3);\n"
                + "let thTitul4 = document.createElement(\"th\");\n"
                + "thTitul4.textContent = \"Modo\";\n"
                + "trHead.appendChild(thTitul4);\n"
                + "let thTitul5 = document.createElement(\"th\");\n"
                + "thTitul5.textContent = \"Procedimiento\";\n"
                + "trHead.appendChild(thTitul5);\n"
                + "\n"
                + "thead.appendChild(trHead);\n"
                + "divTabla.appendChild(tabla);\n"
                + "tabla.appendChild(thead);\n"
                + "tabla.appendChild(tbody);\n"
                + "\n"
                + "for (var itabla = 0; itabla < variablesGlobales.length; itabla++) {\n"
                + "   const trBod = document.createElement(\"tr\");\n"
                + "   tbody.appendChild(trBod);\n"
                + "   const ident = document.createElement(\"td\");\n"
                + "   ident.textContent = variablesGlobales[itabla].identificador; \n"
                + "   const tdval = document.createElement(\"td\");\n"
                + "   tdval.textContent = variablesGlobales[itabla].valor; \n"
                + "   const tdTipo = document.createElement(\"td\");\n"
                + "   tdTipo.textContent = variablesGlobales[itabla].tipo;\n"
                + "   const tdModo = document.createElement(\"td\");\n"
                + "   tdModo.textContent = variablesGlobales[itabla].modo;\n"
                + "   const tdProc = document.createElement(\"td\");\n"
                + "   tdProc.textContent = variablesGlobales[itabla].procedimiento;\n"
                + "   trBod.appendChild(ident);\n"
                + "   trBod.appendChild(tdval);\n"
                + "   trBod.appendChild(tdTipo);\n"
                + "   trBod.appendChild(tdModo);\n"
                + "   trBod.appendChild(tdProc);"
                + "}\n"
                + "for (var itabla2 = 0; itabla2 < variablesLocales.length; itabla2++) {\n"
                + "   const trBod = document.createElement(\"tr\");\n"
                + "   tbody.appendChild(trBod);\n"
                + "   const ident = document.createElement(\"td\");\n"
                + "   ident.textContent = variablesLocales[itabla2].identificador; \n"
                + "   const tdval = document.createElement(\"td\");\n"
                + "   tdval.textContent = variablesLocales[itabla2].valor; \n"
                + "   const tdTipo = document.createElement(\"td\");\n"
                + "   tdTipo.textContent = variablesLocales[itabla2].tipo;\n"
                + "   const tdModo = document.createElement(\"td\");\n"
                + "   tdModo.textContent = variablesLocales[itabla2].modo;\n"
                + "   const tdProc = document.createElement(\"td\");\n"
                + "   tdProc.textContent = variablesLocales[itabla2].procedimiento;\n"
                + "   trBod.appendChild(ident);\n"
                + "   trBod.appendChild(tdval);\n"
                + "   trBod.appendChild(tdTipo);\n"
                + "   trBod.appendChild(tdModo);\n"
                + "   trBod.appendChild(tdProc);"
                + "}\n"
                + "}";
        gStr += "</script>";
    }

    private void insertarReinicioVariablesGlobales() {
        int cont = 0;
        for (String var : this.variablesGlobales) {
            gStr += "variablesGlobales[" + cont + "].valor = " + var + ";\n";
        }
    }

    private void insertarRedirect() {
        gStr += "window.location=\"http://localhost:8080/serverProy2/SLRedirect?idCaptcha=" + captcha.getIdCaptcha().replace("\"", "") + "\";\n";
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
