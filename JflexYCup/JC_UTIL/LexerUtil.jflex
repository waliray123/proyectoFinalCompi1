package analizadores;
import java_cup.runtime.*; 
import analizadores.symU.*;
import java.util.List;
import java.util.ArrayList;
import objetos.ErrorCom;

%%

//Configuracion JFLEX
%public
%class LexerUtil
%standalone
%line
%column
%cup

//Expresiones Regulares


//id = \"("_"|"$"|"-")[a-z0-9A-Z_$]+\"

//Parametros de Etiquetas

%ignorecase
//Etiquetas GCIC


datosForm = "C_DATOSFORM"
idCap = "C_IDCAP"
nombCap = "C_NOMBRECAP"
cantU = "C_CANTUSOS"
cantA = "C_CANTACIERTOS"
cantF = "C_CANTFALLOS"
ultF = "C_ULTIMAFECHA"

//Palabras Reservadas CLC


//Reglas Lexicas 
palabra = [a-zA-ZÀ-ÿ0-9\u00f1\u00d1]
idEt = [{palabra}_$-]+
referencia = https?:\/\/[a-zA-Z0-9\-]+(.[a-zA-Z0-9\-]+)+[/#?]?
valp = "\""([{palabra} #\(\)\n]+|{idEt}|{referencia})+"\""
//referencia = https?:\/\/[\w\-]+(\.[\w\-]+)+ 

blancos = [ \r\t\b\f\n]+



//Codigo Incrustado
%{
    private List<ErrorCom> erroresCom;

    private void error(String lexeme) {
        erroresCom.add(new ErrorCom("Lexico","Simbolo no existe en el lenguaje",String.valueOf(yyline+1),String.valueOf(yycolumn+1),lexeme,""));
    }    

    private void errorPrueba(String lexeme, String tipo) {
        erroresCom.add(new ErrorCom("PRUEBA",tipo,String.valueOf(yyline+1),String.valueOf(yycolumn+1),lexeme,""));
    }

    public List<ErrorCom> getErroresCom() {
        return erroresCom;
    }


%}

%init{
    erroresCom = new ArrayList<>();
%init}

%%


//Reglas Lexicas
<YYINITIAL> {
    "<" {return new Symbol(symU.MEN,yyline+1,yycolumn+1, yytext());} 
    ">" {return new Symbol(symU.MAY,yyline+1,yycolumn+1, yytext());} 
    "/" {return new Symbol(symU.BARRA,yyline+1,yycolumn+1, yytext());}
    {datosForm} {return new Symbol(symU.DATOS_FORM,yyline+1,yycolumn+1, yytext());}
    {idCap} {return new Symbol(symU.ID_CAP,yyline+1,yycolumn+1, yytext());}
    {nombCap} {return new Symbol(symU.NOMBRE_CAP,yyline+1,yycolumn+1, yytext());}
    {cantU} {return new Symbol(symU.CANT_USOS,yyline+1,yycolumn+1, yytext());}
    {cantA} {return new Symbol(symU.CANT_ACIERTOS,yyline+1,yycolumn+1, yytext());}
    {cantF} {return new Symbol(symU.CANT_FALLOS,yyline+1,yycolumn+1, yytext());}
    {ultF} {return new Symbol(symU.ULTIMA_FECHA,yyline+1,yycolumn+1, yytext());}
    {valp} {return new Symbol(symU.VALP,yyline+1,yycolumn+1, yytext());}
    {blancos} {}


    

}

/* Error por cualquier otro simbolo*/
[^]
		{ error(yytext()); new Symbol(symU.error,yyline,yycolumn, yytext());}
