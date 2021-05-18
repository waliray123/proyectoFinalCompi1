/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

/**
 *
 * Clase que guardar los errores lexicos o sintacticos
 */
public class ErrorCom {

    private String tipo;
    private String desc;
    private String lin;
    private String col;
    private String lex;
    private String posSol;

    public ErrorCom(String tipo, String desc, String lin, String col, String lex, String posSol) {
        this.tipo = tipo;
        this.desc = desc;
        this.lin = lin;
        this.col = col;
        this.lex = lex;
        this.posSol = posSol;
    }   

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLin() {
        return lin;
    }

    public void setLin(String lin) {
        this.lin = lin;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public String getLex() {
        return lex;
    }

    public void setLex(String lex) {
        this.lex = lex;
    }

    public String getPosSol() {
        return posSol;
    }

    public void setPosSol(String posSol) {
        this.posSol = posSol;
    }
    
    
}
