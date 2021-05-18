package analizadores;
import java_cup.runtime.*; 
import analizadores.symT.*;
import java.util.List;
import java.util.ArrayList;
import objetos.ErrorCom;

%%

//Configuracion JFLEX
%public
%class LexerGuardado
%standalone
%line
%column
%cup

//Expresiones Regulares


//id = \"("_"|"$"|"-")[a-z0-9A-Z_$]+\"

//Parametros de Etiquetas
href = "href"
back = "background"
color = "color"
fsize = "font-size"
ffamily = "font-family"
textAlign = "text-align"
type = "type"
idEtiqueta = "id"
name = "name"
cols = "cols"
rows = "rows"
class = "class"
src = "src"
width = "width"
height = "height"
alt = "alt"
onclick = "onclick"

%ignorecase
//Etiquetas GCIC


gcic = "C_GCIC"
head = "C_HEAD"
title = "C_TITLE"
link = "C_LINK"
body = "C_BODY"
spam = "C_SPAM"
input = "C_INPUT"
textArea = "C_TEXTAREA"
select = "C_SELECT"
option = "C_OPTION"
div = "C_DIV"
img = "C_IMG"
br = "C_BR"
button = "C_BUTTON"
h1 = "C_H1"
par = "C_P"
scripting = "C_SCRIPTING"
datosForm = "C_DATOSFORM"
idCap = "C_IDCAP"
nombCap = "C_NOMBRECAP"

//Palabras Reservadas CLC


//Reglas Lexicas 
comentario1 = "!!"[a-zA-Z0-9 ]*
comentario2 = "<!--"[a-zA-Z0-9 \r\t\b\f\n]*"-->"
palabra = [a-zA-ZÀ-ÿ0-9\u00f1\u00d1]
idEt = [{palabra}_$-]+
referencia = https?:\/\/[a-zA-Z0-9\-]+(.[a-zA-Z0-9\-]+)+[/#?]?
decim = [0-9]+.[0-9]+
valp = "\""([{palabra} #\(\)\n]+|{idEt}|{referencia})+"\""
caracter = "\'"({palabra}|{idEt}|[ \<\>\-\[\]\=\/])+"\'"
proceso = PROCESS_{palabra}+
palabras = ({palabra})({palabra}|[ ])*
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
    //{iniSols} {return new Symbol(sym.INISOLS,yyline+1,yycolumn+1, yytext());}
    //":" {return new Symbol(sym.DOSPUNT,yyline+1,yycolumn+1, yytext());} 
    "<=" {return new Symbol(symT.MENIG,yyline+1,yycolumn+1, yytext());} 
    ">=" {return new Symbol(symT.MAYIG,yyline+1,yycolumn+1, yytext());} 
    "<" {return new Symbol(symT.MEN,yyline+1,yycolumn+1, yytext());} 
    ">" {return new Symbol(symT.MAY,yyline+1,yycolumn+1, yytext());} 
    "[" {return new Symbol(symT.CORCHCUADI,yyline+1,yycolumn+1, yytext());}
    "]" {return new Symbol(symT.CORCHCUADD,yyline+1,yycolumn+1, yytext());}     
    "/" {return new Symbol(symT.BARRA,yyline+1,yycolumn+1, yytext());}
    //Operadores CLC
    "==" {return new Symbol(symT.DOBIGUAL,yyline+1,yycolumn+1, yytext());}
    "!=" {return new Symbol(symT.DIFER,yyline+1,yycolumn+1, yytext());}
    "=" {return new Symbol(symT.IGUAL,yyline+1,yycolumn+1, yytext());}   
    "\|\|" {return new Symbol(symT.OR,yyline+1,yycolumn+1, yytext());}   
    "\&\&" {return new Symbol(symT.AND,yyline+1,yycolumn+1, yytext());}   
    "!" {return new Symbol(symT.EXCL,yyline+1,yycolumn+1, yytext());}
    "+" {return new Symbol(symT.SUMA,yyline+1,yycolumn+1, yytext());} 
    "-" {return new Symbol(symT.RESTA,yyline+1,yycolumn+1, yytext());} 
    "*" {return new Symbol(symT.MULT,yyline+1,yycolumn+1, yytext());}     
    "(" {return new Symbol(symT.PARI,yyline+1,yycolumn+1, yytext());}
    ")" {return new Symbol(symT.PARD,yyline+1,yycolumn+1, yytext());}
    ";" {return new Symbol(symT.PUNTCOMA,yyline+1,yycolumn+1, yytext());}
    "{" {return new Symbol(symT.CORCHI,yyline+1,yycolumn+1, yytext());}
    "}" {return new Symbol(symT.CORCHD,yyline+1,yycolumn+1, yytext());}
    "," {return new Symbol(symT.COMA,yyline+1,yycolumn+1, yytext());}
    ":" {return new Symbol(symT.DOSPUNT,yyline+1,yycolumn+1, yytext());}

    {href} {return new Symbol(symT.HREF,yyline+1,yycolumn+1, yytext());}
    {back} {return new Symbol(symT.BACK,yyline+1,yycolumn+1, yytext());}
    {color} {return new Symbol(symT.COLOR,yyline+1,yycolumn+1, yytext());}
    {fsize} {return new Symbol(symT.FSIZE,yyline+1,yycolumn+1, yytext());}
    {ffamily} {return new Symbol(symT.FFAMILY,yyline+1,yycolumn+1, yytext());}
    {textAlign} {return new Symbol(symT.TEXTALIGN,yyline+1,yycolumn+1, yytext());}
    {type} {return new Symbol(symT.TYPE,yyline+1,yycolumn+1, yytext());}
    {idEtiqueta} {return new Symbol(symT.IDETIQUETA,yyline+1,yycolumn+1, yytext());}
    {name} {return new Symbol(symT.NAME,yyline+1,yycolumn+1, yytext());}
    {cols} {return new Symbol(symT.COLS,yyline+1,yycolumn+1, yytext());}
    {rows} {return new Symbol(symT.ROWS,yyline+1,yycolumn+1, yytext());}
    {class} {return new Symbol(symT.CLASS,yyline+1,yycolumn+1, yytext());}
    {src} {return new Symbol(symT.SRC,yyline+1,yycolumn+1, yytext());}
    {width} {return new Symbol(symT.WIDTH,yyline+1,yycolumn+1, yytext());}
    {height} {return new Symbol(symT.HEIGHT,yyline+1,yycolumn+1, yytext());}
    {alt} {return new Symbol(symT.ALT,yyline+1,yycolumn+1, yytext());}
    {onclick} {return new Symbol(symT.ONCLICK,yyline+1,yycolumn+1, yytext());}
    {gcic} {return new Symbol(symT.GCIC,yyline+1,yycolumn+1, yytext());}
    {head} {return new Symbol(symT.HEAD,yyline+1,yycolumn+1, yytext());}
    {title} {return new Symbol(symT.TITLE,yyline+1,yycolumn+1, yytext());}
    {link} {return new Symbol(symT.LINK,yyline+1,yycolumn+1, yytext());}
    {body} {return new Symbol(symT.BODY,yyline+1,yycolumn+1, yytext());}
    {spam} {return new Symbol(symT.SPAM,yyline+1,yycolumn+1, yytext());}
    {input} {return new Symbol(symT.INPUT,yyline+1,yycolumn+1, yytext());}
    {textArea} {return new Symbol(symT.TEXTAREA,yyline+1,yycolumn+1, yytext());}
    {select} {return new Symbol(symT.SELECT,yyline+1,yycolumn+1, yytext());}
    {option} {return new Symbol(symT.OPTION,yyline+1,yycolumn+1, yytext());}
    {div} {return new Symbol(symT.DIV,yyline+1,yycolumn+1, yytext());}
    {img} {return new Symbol(symT.IMG,yyline+1,yycolumn+1, yytext());}
    {br} {return new Symbol(symT.BR,yyline+1,yycolumn+1, yytext());}
    {button} {return new Symbol(symT.BUTTON,yyline+1,yycolumn+1, yytext());}
    {h1} {return new Symbol(symT.H1,yyline+1,yycolumn+1, yytext());}
    {par} {return new Symbol(symT.PAR,yyline+1,yycolumn+1, yytext());}
    {scripting} {return new Symbol(symT.SCRIPTING,yyline+1,yycolumn+1, yytext());}
    {datosForm} {errorPrueba(yytext(),"Es datosForm");return new Symbol(symT.DATOS_FORM,yyline+1,yycolumn+1, yytext());}
    {idCap} {return new Symbol(symT.ID_CAP,yyline+1,yycolumn+1, yytext());}
    {nombCap} {return new Symbol(symT.NOMBRE_CAP,yyline+1,yycolumn+1, yytext());}

    //Palabras reservadas CLC
    {blancos}*"integer"{blancos}* {return new Symbol(symT.INTEGER,yyline+1,yycolumn+1, yytext());}    
    {blancos}*"decimal"{blancos}* {return new Symbol(symT.DECIMAL,yyline+1,yycolumn+1, yytext());}
    {blancos}*"boolean"{blancos}* {return new Symbol(symT.BOOLEAN,yyline+1,yycolumn+1, yytext());}
    {blancos}*"char"{blancos}* {return new Symbol(symT.CHAR,yyline+1,yycolumn+1, yytext());}
    {blancos}*"string"{blancos}* {return new Symbol(symT.STRING,yyline+1,yycolumn+1, yytext());}
    {blancos}*"ASC"{blancos}* {return new Symbol(symT.ASC,yyline+1,yycolumn+1, yytext());}
    {blancos}*"DESC"{blancos}* {return new Symbol(symT.DESC,yyline+1,yycolumn+1, yytext());}
    {blancos}*"LETPAR_NUM"{blancos}* {return new Symbol(symT.LETPAR,yyline+1,yycolumn+1, yytext());}
    {blancos}*"LETIMPAR_NUM"{blancos}* {return new Symbol(symT.LETIMPAR,yyline+1,yycolumn+1, yytext());}
    {blancos}*"REVERSE"{blancos}* {return new Symbol(symT.REVERSE,yyline+1,yycolumn+1, yytext());}
    {blancos}*"CARACTER_ALEATORIO"{blancos}* {return new Symbol(symT.CARALEATORIO,yyline+1,yycolumn+1, yytext());}
    {blancos}*"NUM_ALEATORIO"{blancos}* {return new Symbol(symT.NUMALEA,yyline+1,yycolumn+1, yytext());}
    {blancos}*"ALERT_INFO"{blancos}* {return new Symbol(symT.ALERTINFO,yyline+1,yycolumn+1, yytext());}
    {blancos}*"EXIT"{blancos}* {return new Symbol(symT.EXIT,yyline+1,yycolumn+1, yytext());}
    {blancos}*"IF"{blancos}* {return new Symbol(symT.IF,yyline+1,yycolumn+1, yytext());}
    {blancos}*"THENWHILE"{blancos}* {return new Symbol(symT.THENWHILE,yyline+1,yycolumn+1, yytext());}
    {blancos}*"THEN"{blancos}* {return new Symbol(symT.THEN,yyline+1,yycolumn+1, yytext());}
    {blancos}*"INIT"{blancos}* {return new Symbol(symT.INITC,yyline+1,yycolumn+1, yytext());}
    {blancos}*"END"{blancos}* {return new Symbol(symT.END,yyline+1,yycolumn+1, yytext());}
    {blancos}*"ELSE IF"{blancos}* {return new Symbol(symT.ELSEIF,yyline+1,yycolumn+1, yytext());}
    {blancos}*"ELSE"{blancos}* {return new Symbol(symT.ELSE,yyline+1,yycolumn+1, yytext());}
    {blancos}*"REPEAT"{blancos}* {return new Symbol(symT.REPEAT,yyline+1,yycolumn+1, yytext());}
    {blancos}*"HUNTIL"{blancos}* {return new Symbol(symT.HUNTIL,yyline+1,yycolumn+1, yytext());}
    {blancos}*"INSERT"{blancos}* {return new Symbol(symT.INSERT,yyline+1,yycolumn+1, yytext());}
    {blancos}*"WHILE"{blancos}* {return new Symbol(symT.WHILE,yyline+1,yycolumn+1, yytext());}   
    {blancos}*"ON_LOAD"{blancos}* {return new Symbol(symT.ONLOAD,yyline+1,yycolumn+1, yytext());}    
    {blancos}*"true"{blancos}* {return new Symbol(symT.TRUE,yyline+1,yycolumn+1, yytext());}    
    {blancos}*"false"{blancos}* {return new Symbol(symT.FALSE,yyline+1,yycolumn+1, yytext());}    
    {blancos}*"@global"{blancos}* {return new Symbol(symT.GLOBAL,yyline+1,yycolumn+1, yytext());}
    {blancos}*"getElemenById"{blancos}* {return new Symbol(symT.GETELEMENT,yyline+1,yycolumn+1, yytext());}
    {proceso} {return new Symbol(symT.PROCESO,yyline+1,yycolumn+1, yytext());}
    {comentario1} {}
    {comentario2} {}    
    //{palabra} {errorPrueba(yytext(),"Es palabra");return new Symbol(symT.PALABRA,yyline+1,yycolumn+1, yytext());}
    //{palabras} {errorPrueba(yytext(),"Es palabras 1");return new Symbol(symT.PALABRAS,yyline+1,yycolumn+1, yytext());}
    //{palabras2} {errorPrueba(yytext(),"Es palabras 2");return new Symbol(symT.PALABRAS,yyline+1,yycolumn+1, yytext());}
    {caracter} {return new Symbol(symT.CARACTER,yyline+1,yycolumn+1, yytext());}
    {valp} {return new Symbol(symT.VALP,yyline+1,yycolumn+1, yytext());}
    {decim} {return new Symbol(symT.DECIM,yyline+1,yycolumn+1, yytext());}        
    {idEt} {return new Symbol(symT.IDET,yyline+1,yycolumn+1, yytext());}
    {palabras} {return new Symbol(symT.PALABRAS,yyline+1,yycolumn+1, yytext());}
    {blancos} {}


    

}

/* Error por cualquier otro simbolo*/
[^]
		{ error(yytext()); new Symbol(symT.error,yyline,yycolumn, yytext());}
