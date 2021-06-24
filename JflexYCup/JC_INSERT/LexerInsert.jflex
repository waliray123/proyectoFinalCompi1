package analizadores;
import java_cup.runtime.*; 
import analizadores.symTI.*;
import java.util.List;
import java.util.ArrayList;
import objetos.ErrorCom;

%%

//Configuracion JFLEX
%public
%class LexerInsert
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
variableScrip = "C_VARIABLESCRIP";
finvariableScrip = "C_FINVARIABLESCRIP";

//Palabras Reservadas CLC


//Reglas Lexicas 
comentario1 = \!\!.*
comentario2 = \<\!\-\-(\s*|.*?)*\-\-\>
palabra = [a-zA-ZÀ-ÿ0-9\u00f1\u00d1]
idEt = [{palabra}_$-]+
referencia = https?:\/\/[a-zA-Z0-9\-]+(.[a-zA-Z0-9\-.]+)+[/#?]?
decim = [0-9]+\.[0-9]+
valp = "\""([{palabra} \:\#\(\)\n]+|{idEt}|{referencia})+"\""
caracter = \'(.)*\'
palabras = ({palabra}|\?)({palabra}|decim|[ \r\t\b\f\n]+|\?|\:|\.|\¿)**

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
    "<=" {return new Symbol(symTI.MENIG,yyline+1,yycolumn+1, yytext());}
    ">=" {return new Symbol(symTI.MAYIG,yyline+1,yycolumn+1, yytext());}
    "<" {return new Symbol(symTI.MEN,yyline+1,yycolumn+1, yytext());}
    ">" {return new Symbol(symTI.MAY,yyline+1,yycolumn+1, yytext());}
    "[" {return new Symbol(symTI.CORCHCUADI,yyline+1,yycolumn+1, yytext());}
    "]" {return new Symbol(symTI.CORCHCUADD,yyline+1,yycolumn+1, yytext());}     
    "/" {return new Symbol(symTI.BARRA,yyline+1,yycolumn+1, yytext());}
    //Operadores CLC
    "==" {return new Symbol(symTI.DOBIGUAL,yyline+1,yycolumn+1, yytext());}
    "!=" {return new Symbol(symTI.DIFER,yyline+1,yycolumn+1, yytext());}
    "=" {return new Symbol(symTI.IGUAL,yyline+1,yycolumn+1, yytext());}   
    "\|\|" {return new Symbol(symTI.OR,yyline+1,yycolumn+1, yytext());}   
    "\&\&" {return new Symbol(symTI.AND,yyline+1,yycolumn+1, yytext());}   
    "!" {return new Symbol(symTI.EXCL,yyline+1,yycolumn+1, yytext());}
    "+" {return new Symbol(symTI.SUMA,yyline+1,yycolumn+1, yytext());} 
    "-" {return new Symbol(symTI.RESTA,yyline+1,yycolumn+1, yytext());} 
    "*" {return new Symbol(symTI.MULT,yyline+1,yycolumn+1, yytext());}     
    "(" {return new Symbol(symTI.PARI,yyline+1,yycolumn+1, yytext());}
    ")" {return new Symbol(symTI.PARD,yyline+1,yycolumn+1, yytext());}
    ";" {return new Symbol(symTI.PUNTCOMA,yyline+1,yycolumn+1, yytext());}
    "{" {return new Symbol(symTI.CORCHI,yyline+1,yycolumn+1, yytext());}
    "}" {return new Symbol(symTI.CORCHD,yyline+1,yycolumn+1, yytext());}
    "," {return new Symbol(symTI.COMA,yyline+1,yycolumn+1, yytext());}
    ":" {return new Symbol(symTI.DOSPUNT,yyline+1,yycolumn+1, yytext());}

    {href} {return new Symbol(symTI.HREF,yyline+1,yycolumn+1, yytext());}
    {back} {return new Symbol(symTI.BACK,yyline+1,yycolumn+1, yytext());}
    {color} {return new Symbol(symTI.COLOR,yyline+1,yycolumn+1, yytext());}
    {fsize} {return new Symbol(symTI.FSIZE,yyline+1,yycolumn+1, yytext());}
    {ffamily} {return new Symbol(symTI.FFAMILY,yyline+1,yycolumn+1, yytext());}
    {textAlign} {return new Symbol(symTI.TEXTALIGN,yyline+1,yycolumn+1, yytext());}
    {type} {return new Symbol(symTI.TYPE,yyline+1,yycolumn+1, yytext());}
    {idEtiqueta} {return new Symbol(symTI.IDETIQUETA,yyline+1,yycolumn+1, yytext());}
    {name} {return new Symbol(symTI.NAME,yyline+1,yycolumn+1, yytext());}
    {cols} {return new Symbol(symTI.COLS,yyline+1,yycolumn+1, yytext());}
    {rows} {return new Symbol(symTI.ROWS,yyline+1,yycolumn+1, yytext());}
    {class} {return new Symbol(symTI.CLASS,yyline+1,yycolumn+1, yytext());}
    {src} {return new Symbol(symTI.SRC,yyline+1,yycolumn+1, yytext());}
    {width} {return new Symbol(symTI.WIDTH,yyline+1,yycolumn+1, yytext());}
    {height} {return new Symbol(symTI.HEIGHT,yyline+1,yycolumn+1, yytext());}
    {alt} {return new Symbol(symTI.ALT,yyline+1,yycolumn+1, yytext());}
    {onclick} {return new Symbol(symTI.ONCLICK,yyline+1,yycolumn+1, yytext());}
    {variableScrip} {return new Symbol(symTI.VARIABLESCRIP,yyline+1,yycolumn+1, yytext());} 
    {gcic} {return new Symbol(symTI.GCIC,yyline+1,yycolumn+1, yytext());}
    {head} {return new Symbol(symTI.HEAD,yyline+1,yycolumn+1, yytext());}
    {title} {return new Symbol(symTI.TITLE,yyline+1,yycolumn+1, yytext());}
    {link} {return new Symbol(symTI.LINK,yyline+1,yycolumn+1, yytext());}
    {body} {return new Symbol(symTI.BODY,yyline+1,yycolumn+1, yytext());}
    {spam} {return new Symbol(symTI.SPAM,yyline+1,yycolumn+1, yytext());}
    {input} {return new Symbol(symTI.INPUT,yyline+1,yycolumn+1, yytext());}
    {textArea} {return new Symbol(symTI.TEXTAREA,yyline+1,yycolumn+1, yytext());}
    {select} {return new Symbol(symTI.SELECT,yyline+1,yycolumn+1, yytext());}
    {option} {return new Symbol(symTI.OPTION,yyline+1,yycolumn+1, yytext());}
    {div} {return new Symbol(symTI.DIV,yyline+1,yycolumn+1, yytext());}
    {img} {return new Symbol(symTI.IMG,yyline+1,yycolumn+1, yytext());}
    {br} {return new Symbol(symTI.BR,yyline+1,yycolumn+1, yytext());}
    {button} {return new Symbol(symTI.BUTTON,yyline+1,yycolumn+1, yytext());}
    {h1} {return new Symbol(symTI.H1,yyline+1,yycolumn+1, yytext());}
    {par} {return new Symbol(symTI.PAR,yyline+1,yycolumn+1, yytext());}
    {scripting} {return new Symbol(symTI.SCRIPTING,yyline+1,yycolumn+1, yytext());}        
    {finvariableScrip} {return new Symbol(symTI.FINVARIABLESCRIP,yyline+1,yycolumn+1, yytext());}    
    {comentario1} {}
    {comentario2} {}    
    //{palabra} {errorPrueba(yytext(),"Es palabra");return new Symbol(symTI.PALABRA,yyline+1,yycolumn+1, yytext());}
    //{palabras} {errorPrueba(yytext(),"Es palabras 1");return new Symbol(symTI.PALABRAS,yyline+1,yycolumn+1, yytext());}
    //{palabras2} {errorPrueba(yytext(),"Es palabras 2");return new Symbol(symTI.PALABRAS,yyline+1,yycolumn+1, yytext());}
    //{caracter} {return new Symbol(symTI.CARACTER,yyline+1,yycolumn+1, yytext());}
    {valp} {return new Symbol(symTI.VALP,yyline+1,yycolumn+1, yytext());}
    {decim} {return new Symbol(symTI.DECIM,yyline+1,yycolumn+1, yytext());}        
    {idEt} {return new Symbol(symTI.IDET,yyline+1,yycolumn+1, yytext());}
    {palabras} {return new Symbol(symTI.PALABRAS,yyline+1,yycolumn+1, yytext());}
    {blancos} {}


    

}

/* Error por cualquier otro simbolo*/
[^]
		{ error(yytext()); new Symbol(symTI.error,yyline,yycolumn, yytext());}
