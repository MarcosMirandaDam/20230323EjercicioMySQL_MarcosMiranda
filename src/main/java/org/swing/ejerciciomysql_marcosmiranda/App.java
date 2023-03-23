package org.swing.ejerciciomysql_marcosmiranda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Marcos Miranda
 */
public class App {

    public static void main(String[] args) throws ClassNotFoundException {

        GestoraApp siri = new GestoraApp();

        try {
            
            //1. Retorna todos los libros, (devuelve un ResultSet que contiene todos los datos de los libros),  
            //   también los puede devolver ordenados por nombre si el usuario lo desea. 
            
            ResultSet libros=siri.retornarLibros(Boolean.FALSE);
            siri.mostrarResultSet(libros);
            
            //2. Insertar un libro
            ArrayList<String> libro1=new ArrayList<>();
            libro1.add("10501671");
            libro1.add("La Celestina");
            libro1.add("Fernando de Rojas");
            libro1.add("874");
            libro1.add("2023-03-23");
            libro1.add("1");
            libro1.add("2023-03-23");
            
            if(siri.insertarLibroEnBiblioteca(libro1)){
                System.out.println("Libro insertado en la base de datos");
            } else{
                System.out.println("No se ha insertado el libro. Pruebe de nuevo");
            }
            
           
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}
