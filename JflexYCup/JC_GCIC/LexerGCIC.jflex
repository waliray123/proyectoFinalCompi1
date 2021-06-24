package analizadores;
import java_cup.runtime.*; 
import analizadores.symG.*;
import java.util.List;
import java.util.ArrayList;
import objetos.ErrorCom;

%%

//Configuracion JFLEX
%public
%class LexerGCIC
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

//Palabras Reservadas CLC


//Reglas Lexicas 
comentario1 = \!\!.*
comentario2 = \<\!\-\-(\s*|.*?)*\-\-\>
palabra = [a-zA-ZÁ-ÿ0-9\u00f1\u00d1\á\é\í\ó\ú\Á\É\Í\Ó\Ú]
idEt = [{palabra}_$-]+
referencia = https?:\/\/[a-zA-Z0-9\-]+(.[a-zA-Z0-9\-.]+)+[/#?]?
decim = [0-9]+\.[0-9]+
//valp = "\""([{palabra} #\(\)\n]+|{idEt}|{referencia})+"\""
valp = "\""([{palabra} \:\#\(\)\n]+|{idEt}|{referencia})+"\""
caracter = \'(.)*\'
proceso = PROCESS_{palabra}+
palabras = ({palabra})({palabra}|decim|[ \r\t\b\f\n]+|\?|\:|\.|\¿)*
//palabras = ([a-zA-ZÀ-ÿ0-9\u00f1\u00d1]|[ \r\t\b\f\n]|\(|\)|\*|\:|\,|\-|\@|\<|\>|\_)*
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
    "<=" {return new Symbol(symG.MENIG,yyline+1,yycolumn+1, yytext());} 
    ">=" {return new Symbol(symG.MAYIG,yyline+1,yycolumn+1, yytext());} 
    "<" {return new Symbol(symG.MEN,yyline+1,yycolumn+1, yytext());} 
    ">" {return new Symbol(symG.MAY,yyline+1,yycolumn+1, yytext());} 
    "[" {return new Symbol(symG.CORCHCUADI,yyline+1,yycolumn+1, yytext());}
    "]" {return new Symbol(symG.CORCHCUADD,yyline+1,yycolumn+1, yytext());}     
    "/" {return new Symbol(symG.BARRA,yyline+1,yycolumn+1, yytext());}
    //Operadores CLC
    "==" {return new Symbol(symG.DOBIGUAL,yyline+1,yycolumn+1, yytext());}
    "!=" {return new Symbol(symG.DIFER,yyline+1,yycolumn+1, yytext());}
    "=" {return new Symbol(symG.IGUAL,yyline+1,yycolumn+1, yytext());}   
    "\|\|" {return new Symbol(symG.OR,yyline+1,yycolumn+1, yytext());}   
    "\&\&" {return new Symbol(symG.AND,yyline+1,yycolumn+1, yytext());}   
    "!" {return new Symbol(symG.EXCL,yyline+1,yycolumn+1, yytext());}
    "+" {return new Symbol(symG.SUMA,yyline+1,yycolumn+1, yytext());} 
    "-" {return new Symbol(symG.RESTA,yyline+1,yycolumn+1, yytext());} 
    "*" {return new Symbol(symG.MULT,yyline+1,yycolumn+1, yytext());}     
    "(" {return new Symbol(symG.PARI,yyline+1,yycolumn+1, yytext());}
    ")" {return new Symbol(symG.PARD,yyline+1,yycolumn+1, yytext());}
    ";" {return new Symbol(symG.PUNTCOMA,yyline+1,yycolumn+1, yytext());}
    "{" {return new Symbol(symG.CORCHI,yyline+1,yycolumn+1, yytext());}
    "}" {return new Symbol(symG.CORCHD,yyline+1,yycolumn+1, yytext());}
    "," {return new Symbol(symG.COMA,yyline+1,yycolumn+1, yytext());}
    ":" {return new Symbol(symG.DOSPUNT,yyline+1,yycolumn+1, yytext());}

    {href} {return new Symbol(symG.HREF,yyline+1,yycolumn+1, yytext());}
    {back} {return new Symbol(symG.BACK,yyline+1,yycolumn+1, yytext());}
    {color} {return new Symbol(symG.COLOR,yyline+1,yycolumn+1, yytext());}
    {fsize} {return new Symbol(symG.FSIZE,yyline+1,yycolumn+1, yytext());}
    {ffamily} {return new Symbol(symG.FFAMILY,yyline+1,yycolumn+1, yytext());}
    {textAlign} {return new Symbol(symG.TEXTALIGN,yyline+1,yycolumn+1, yytext());}
    {type} {return new Symbol(symG.TYPE,yyline+1,yycolumn+1, yytext());}
    {idEtiqueta} {return new Symbol(symG.IDETIQUETA,yyline+1,yycolumn+1, yytext());}
    {name} {return new Symbol(symG.NAME,yyline+1,yycolumn+1, yytext());}
    {cols} {return new Symbol(symG.COLS,yyline+1,yycolumn+1, yytext());}
    {rows} {return new Symbol(symG.ROWS,yyline+1,yycolumn+1, yytext());}
    {class} {return new Symbol(symG.CLASS,yyline+1,yycolumn+1, yytext());}
    {src} {return new Symbol(symG.SRC,yyline+1,yycolumn+1, yytext());}
    {width} {return new Symbol(symG.WIDTH,yyline+1,yycolumn+1, yytext());}
    {height} {return new Symbol(symG.HEIGHT,yyline+1,yycolumn+1, yytext());}
    {alt} {return new Symbol(symG.ALT,yyline+1,yycolumn+1, yytext());}
    {onclick} {return new Symbol(symG.ONCLICK,yyline+1,yycolumn+1, yytext());}
    {gcic} {return new Symbol(symG.GCIC,yyline+1,yycolumn+1, yytext());}
    {head} {return new Symbol(symG.HEAD,yyline+1,yycolumn+1, yytext());}
    {title} {return new Symbol(symG.TITLE,yyline+1,yycolumn+1, yytext());}
    {link} {return new Symbol(symG.LINK,yyline+1,yycolumn+1, yytext());}
    {body} {return new Symbol(symG.BODY,yyline+1,yycolumn+1, yytext());}
    {spam} {return new Symbol(symG.SPAM,yyline+1,yycolumn+1, yytext());}
    {input} {return new Symbol(symG.INPUT,yyline+1,yycolumn+1, yytext());}
    {textArea} {return new Symbol(symG.TEXTAREA,yyline+1,yycolumn+1, yytext());}
    {select} {return new Symbol(symG.SELECT,yyline+1,yycolumn+1, yytext());}
    {option} {return new Symbol(symG.OPTION,yyline+1,yycolumn+1, yytext());}
    {div} {return new Symbol(symG.DIV,yyline+1,yycolumn+1, yytext());}
    {img} {return new Symbol(symG.IMG,yyline+1,yycolumn+1, yytext());}
    {br} {return new Symbol(symG.BR,yyline+1,yycolumn+1, yytext());}
    {button} {return new Symbol(symG.BUTTON,yyline+1,yycolumn+1, yytext());}
    {h1} {return new Symbol(symG.H1,yyline+1,yycolumn+1, yytext());}
    {par} {return new Symbol(symG.PAR,yyline+1,yycolumn+1, yytext());}
    {scripting} {return new Symbol(symG.SCRIPTING,yyline+1,yycolumn+1, yytext());}

    //Palabras reservadas CLC
    {blancos}*"integer"{blancos}* {return new Symbol(symG.INTEGER,yyline+1,yycolumn+1, yytext());}    
    {blancos}*"decimal"{blancos}* {return new Symbol(symG.DECIMAL,yyline+1,yycolumn+1, yytext());}
    {blancos}*"boolean"{blancos}* {return new Symbol(symG.BOOLEAN,yyline+1,yycolumn+1, yytext());}
    {blancos}*"char"{blancos}* {return new Symbol(symG.CHAR,yyline+1,yycolumn+1, yytext());}
    {blancos}*"string"{blancos}* {return new Symbol(symG.STRING,yyline+1,yycolumn+1, yytext());}
    {blancos}*"ASC"{blancos}* {return new Symbol(symG.ASC,yyline+1,yycolumn+1, yytext());}
    {blancos}*"DESC"{blancos}* {return new Symbol(symG.DESC,yyline+1,yycolumn+1, yytext());}
    {blancos}*"LETPAR_NUM"{blancos}* {return new Symbol(symG.LETPAR,yyline+1,yycolumn+1, yytext());}
    {blancos}*"LETIMPAR_NUM"{blancos}* {return new Symbol(symG.LETIMPAR,yyline+1,yycolumn+1, yytext());}
    {blancos}*"REVERSE"{blancos}* {return new Symbol(symG.REVERSE,yyline+1,yycolumn+1, yytext());}
    {blancos}*"CARACTER_ALEATORIO"{blancos}* {return new Symbol(symG.CARALEATORIO,yyline+1,yycolumn+1, yytext());}
    {blancos}*"NUM_ALEATORIO"{blancos}* {return new Symbol(symG.NUMALEA,yyline+1,yycolumn+1, yytext());}
    {blancos}*"ALERT_INFO"{blancos}* {return new Symbol(symG.ALERTINFO,yyline+1,yycolumn+1, yytext());}
    {blancos}*"EXIT"{blancos}* {return new Symbol(symG.EXIT,yyline+1,yycolumn+1, yytext());}
    {blancos}*"IF"{blancos}* {return new Symbol(symG.IF,yyline+1,yycolumn+1, yytext());}
    {blancos}*"THENWHILE"{blancos}* {return new Symbol(symG.THENWHILE,yyline+1,yycolumn+1, yytext());}
    {blancos}*"THEN"{blancos}* {return new Symbol(symG.THEN,yyline+1,yycolumn+1, yytext());}
    {blancos}*"INIT"{blancos}* {return new Symbol(symG.INITC,yyline+1,yycolumn+1, yytext());}
    {blancos}*"END"{blancos}* {return new Symbol(symG.END,yyline+1,yycolumn+1, yytext());}
    {blancos}*"ELSE IF"{blancos}* {return new Symbol(symG.ELSEIF,yyline+1,yycolumn+1, yytext());}
    {blancos}*"ELSE"{blancos}* {return new Symbol(symG.ELSE,yyline+1,yycolumn+1, yytext());}
    {blancos}*"REPEAT"{blancos}* {return new Symbol(symG.REPEAT,yyline+1,yycolumn+1, yytext());}
    {blancos}*"HUNTIL"{blancos}* {return new Symbol(symG.HUNTIL,yyline+1,yycolumn+1, yytext());}
    {blancos}*"INSERT"{blancos}* {return new Symbol(symG.INSERT,yyline+1,yycolumn+1, yytext());}
    {blancos}*"WHILE"{blancos}* {return new Symbol(symG.WHILE,yyline+1,yycolumn+1, yytext());}   
    {blancos}*"ON_LOAD"{blancos}* {return new Symbol(symG.ONLOAD,yyline+1,yycolumn+1, yytext());}    
    {blancos}*"REDIRECT"{blancos}* {return new Symbol(symG.REDIRECT,yyline+1,yycolumn+1, yytext());}    
    {blancos}*"true"{blancos}* {return new Symbol(symG.TRUE,yyline+1,yycolumn+1, yytext());}    
    {blancos}*"false"{blancos}* {return new Symbol(symG.FALSE,yyline+1,yycolumn+1, yytext());}    
    {blancos}*"@global"{blancos}* {return new Symbol(symG.GLOBAL,yyline+1,yycolumn+1, yytext());}
    {blancos}*"getElemenById"{blancos}* {return new Symbol(symG.GETELEMENT,yyline+1,yycolumn+1, yytext());}
    {proceso} {return new Symbol(symG.PROCESO,yyline+1,yycolumn+1, yytext());}
    {comentario1} {}
    {comentario2} {}    
    //{palabra} {errorPrueba(yytext(),"Es palabra");return new Symbol(symG.PALABRA,yyline+1,yycolumn+1, yytext());}
    //{palabras} {errorPrueba(yytext(),"Es palabras 1");return new Symbol(symG.PALABRAS,yyline+1,yycolumn+1, yytext());}
    //{palabras2} {errorPrueba(yytext(),"Es palabras 2");return new Symbol(symG.PALABRAS,yyline+1,yycolumn+1, yytext());}
    {caracter} {return new Symbol(symG.CARACTER,yyline+1,yycolumn+1, yytext());}
    {valp} {return new Symbol(symG.VALP,yyline+1,yycolumn+1, yytext());}
    {decim} {return new Symbol(symG.DECIM,yyline+1,yycolumn+1, yytext());}
    //{idEt} {errorPrueba(yytext(),"Es idEt");return new Symbol(symG.PALABRA,yyline+1,yycolumn+1, yytext());}
    //{referencia} {errorPrueba(yytext(),"Es referencia");return new Symbol(symG.PALABRAS,yyline+1,yycolumn+1, yytext());}
    {idEt} {return new Symbol(symG.IDET,yyline+1,yycolumn+1, yytext());}
    {palabras} {return new Symbol(symG.PALABRAS,yyline+1,yycolumn+1, yytext());}
    {blancos} {}


    

}

/* Error por cualquier otro simbolo*/
[^]
		{ error(yytext()); new Symbol(symG.error,yyline,yycolumn, yytext());}
