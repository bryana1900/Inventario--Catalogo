package Bryan.ProductosCongelados;

import Bryan.Config_Propeties.Configuracion;

import java.sql.*;
import java.util.ArrayList;

public class ProductosCongeladosDAO {
    /**
     * Creo funcion para insertar datos en mysql
     */
    public void insertarCongelados(ProductosCongelados RC) throws ClassNotFoundException, SQLException {
        try{
            Configuracion configuracion= new Configuracion();
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "INSERT INTO CONGELADOS (NOMBRE,PAIS,PRECIO,TEMPMAX,TEMPMIN,CODIGO) VALUES(?,?,?,?,?,?)";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,RC.getNombre());
            stmt.setString(2,RC.getPais());
            stmt.setString(3,RC.getPrecio());
            stmt.setString(4,RC.getTempMax());
            stmt.setString(5,RC.getTempMin());
            stmt.setInt(6,RC.getCodigo());
            stmt.execute();
        }
        catch (SQLException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
    /**
     * Creo funcion para listar los datos de mysql
     */
    public ArrayList<ProductosCongelados> listarCongelados() throws SQLException, ClassNotFoundException {
        Configuracion configuracion= new Configuracion();
        ArrayList<ProductosCongelados> productosCongelados= new ArrayList<ProductosCongelados>();
        try {
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            String query = "SELECT * FROM CONGELADOS";
            Statement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);// el rs almacena la información de la base de datos.
            while (rs.next()) { //rs.next devuelve true si hay más líneas en el result set. por defecto, al iniciar el ciclo, el rs está en la línea 0.
                ProductosCongelados productosCongeladoss = new ProductosCongelados();
                productosCongeladoss.setNombre(rs.getString("NOMBRE"));
                productosCongeladoss.setPais(rs.getString("PAIS"));
                productosCongeladoss.setPrecio(rs.getString("PRECIO"));
                productosCongeladoss.setTempMax(rs.getString("TEMPMAX"));
                productosCongeladoss.setTempMin(rs.getString("TEMPMIN"));
                productosCongeladoss.setCodigo(rs.getInt("CODIGO"));
                productosCongelados.add(productosCongeladoss);
            }
            conn.close();
        }
        catch (SQLException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
        return productosCongelados;
    }
    /**
     * Creo funcion para eliminar los datos de mysql
     */
    public void eliminarCongelados(ProductosCongelados tmp) throws ClassNotFoundException,SQLException{
        Configuracion configuracion= new Configuracion();
        try{
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "DELETE FROM CONGELADOS WHERE CODIGO = ?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,tmp.getCodigo());
            stmt.execute();
        }
        catch (SQLException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
    /**
     * Creo funcion para modificar los datos en mysql
     */
    public void modificarCongelados(ProductosCongelados tmp) throws ClassNotFoundException,SQLException{
        Configuracion configuracion= new Configuracion();
        try{
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "UPDATE CONGELADOS SET NOMBRE=?,PAIS=?,PRECIO=?,TEMPMAX=?,TEMPMIN=?" +
                    "where CODIGO = ?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,tmp.getNombre());
            stmt.setString(2,tmp.getPais());
            stmt.setString(3,tmp.getPrecio());
            stmt.setString(4,tmp.getTempMax());
            stmt.setString(5,tmp.getTempMin());
            stmt.setInt(6,tmp.getCodigo());
            stmt.execute();
        }
        catch (SQLException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
}
