/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import objetos.Datos;

/**
 *
 * @author user-ubunto
 */
public class GuardarDatos {
    
    private Datos datos;        
    private String strDatos;

    public GuardarDatos() {
        this.datos = new Datos();
        this.strDatos = "";
    }
    
    public void limpiar(){
        datos = new Datos();
        datos.setDatos("");
        datos.setUtilizacion("");
        crearArchivo();
    }
    
    private void crearArchivo() {
        try {
            File file = new File("Datos3.dat");
            FileOutputStream fos;

            fos = new FileOutputStream(file);

            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(datos);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getAllData() {
        String dbDatos = "";        
        try {
            File file = new File("Datos3.dat");
            if (!file.exists()) {
                crearArchivo();
            } else {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                datos = (Datos) ois.readObject();                
                ois.close();
                dbDatos = datos.getDatos();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }        
        return dbDatos;
    }
    
    public String getUtilizacion(){
        String dbDatos = "";        
        try {
            File file = new File("Datos3.dat");
            if (!file.exists()) {
                crearArchivo();
            } else {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                datos = (Datos) ois.readObject();                
                ois.close();
                dbDatos = datos.getUtilizacion();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }        
        return dbDatos;
    }
            
    public void guardarDatos(String datos,String utilizacion){
        this.datos.setDatos(datos);
        this.datos.setUtilizacion(utilizacion);
        crearArchivo();
    }

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }

    public String getStrDatos() {
        return strDatos;
    }

    public void setStrDatos(String strDatos) {
        this.strDatos = strDatos;
    }
    
    
}
