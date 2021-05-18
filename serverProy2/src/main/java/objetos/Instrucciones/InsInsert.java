/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.Instrucciones;

import java.util.ArrayList;
import java.util.List;
import objetos.Instruccion;

/**
 *
 * @author user-ubunto
 */
public class InsInsert extends Instruccion{
    
    private List<String> tokens;
    
    public InsInsert(String tipo) {
        super(tipo);
        this.tokens = new ArrayList<>();
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }
    
    public void insertarToken(String tok){
        this.tokens.add(tok);
    }
    
}
