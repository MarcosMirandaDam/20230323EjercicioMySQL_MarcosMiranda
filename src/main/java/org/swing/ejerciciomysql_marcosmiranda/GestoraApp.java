package org.swing.ejerciciomysql_marcosmiranda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase que desarrolla los métodos a utilizar en la clase principal APP
 *
 * @author Marcos Miranda
 */
public class GestoraApp {

    /**
     * realizamos un método privado para ejecutar la conexion a la bbdd MySQL
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection conexionBD() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String cadenaConexion = "jdbc:mysql://localhost:999/BibliotecaMarcosMiranad?user=root&password=IESJJCM2023";
        Connection conexionBD = DriverManager.getConnection(cadenaConexion);

        return conexionBD;

    }

    /**
     * metodo que muestra el ResultSet que ejecute
     *
     * @param resultSet
     * @throws java.sql.SQLException
     */
    public void mostrarResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();                   // cogemos los metaDatos
        int numeroColumnasMostrar = metaData.getColumnCount();                  //numero de columnas de esos metadatos

        while (resultSet.next()) {
            for (int i = 1; i <= numeroColumnasMostrar; i++) {
                String valorColumna = resultSet.getString(i);
                System.out.println(valorColumna + " | ");
            }
            System.out.println("");

        }
    }

    /**
     * metodo que devuelve los libros de la biblioteca, ordenados o no.. segun
     * decida el usuario
     *
     * @param ordenadosPorNombre
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ResultSet retornarLibros(Boolean ordenadosPorNombre) throws ClassNotFoundException, SQLException {
        Connection conexionBd = this.conexionBD();                    //instanciamos la conexion a la bbdd
        Statement statement = conexionBd.createStatement();           //instancia retorna sentencia normal
        Statement statementOrdenados = conexionBd.createStatement();  //instancia retorna sentencia ordenados por nombre

        ResultSet resultado;
        if (ordenadosPorNombre) {
            resultado = statement.executeQuery("SELECT * FROM libro");

        } else {
            resultado = statementOrdenados.executeQuery("SELECT * FROM libro ORDER BY titulo ASC");

        }
        return resultado;

    }
    
    public boolean insertarLibroEnBiblioteca(ArrayList<String> valoresLibro) throws ClassNotFoundException, SQLException{
        boolean insertado=false;
        Connection conexionBd = this.conexionBD();                    //instanciamos la conexion a la bbdd
        Statement statement = conexionBd.createStatement();           //instancia retorna sentencia normal
        
        statement.executeUpdate(
        "INSERT into libro (isbn, titulo, autor, numeroPaginas, fechaPublicacion, prestadoSiNo, fechaDevolucion)" 
                + " VALUES ("+Integer.valueOf(valoresLibro.get(0))+", '"+valoresLibro.get(1)+"', '"+valoresLibro.get(2)+"', "
                        + ""+Integer.valueOf(valoresLibro.get(3))+", '"+valoresLibro.get(4)+"', "+valoresLibro.get(5)+
                        ", '"+valoresLibro.get(6)+"')");
        statement.close();
        insertado=true;
        
        return insertado;
    }
    
}
