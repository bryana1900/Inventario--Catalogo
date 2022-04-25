package Bryan.ProductosRefrigerados;

import Bryan.Config_Propeties.Configuracion;

import java.sql.*;
import java.util.ArrayList;

public class ProductosRefrigeradosDAO {
    /**
     * Funcion insertar datos en el Mysql
     */
    public void insertarRefrigerados(ProductosRefrigerados RF) throws ClassNotFoundException, SQLException {
        try{
            Configuracion configuracion= new Configuracion();
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "INSERT INTO REFRIGERADOS (NOMBRE,PAIS,PRECIO,TIPOREFRI,CODIGO) VALUES(?,?,?,?,?)";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,RF.getNombre());
            stmt.setString(2,RF.getPais());
            stmt.setString(3,RF.getPrecio());
            stmt.setString(4,RF.getTiporefrigerador());
            stmt.setInt(5,RF.getCodigo());
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
     * Funcion listar datos que vienen del Mysql
     */
    public ArrayList<ProductosRefrigerados> listarRefrigerados() throws SQLException, ClassNotFoundException {
        Configuracion configuracion= new Configuracion();
        ArrayList<ProductosRefrigerados> productosRefrigerados= new ArrayList<ProductosRefrigerados>();
        try {
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            String query = "SELECT * FROM REFRIGERADOS";
            Statement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);// el rs almacena la información de la base de datos.
            while (rs.next()) { //rs.next devuelve true si hay más líneas en el result set. por defecto, al iniciar el ciclo, el rs está en la línea 0.
                ProductosRefrigerados productosRefrigeradoss = new ProductosRefrigerados();
                productosRefrigeradoss.setNombre(rs.getString("NOMBRE"));
                productosRefrigeradoss.setPais(rs.getString("PAIS"));
                productosRefrigeradoss.setPrecio(rs.getString("PRECIO"));
                productosRefrigeradoss.setTiporefrigerador(rs.getString("TIPOREFRI"));
                productosRefrigeradoss.setCodigo(rs.getInt("CODIGO"));
                productosRefrigerados.add(productosRefrigeradoss);
            }
            conn.close();
        }
        catch (SQLException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
        return productosRefrigerados;
    }
    /**
     * Funcion Eliminar datos de Mysql
     */
    public void eliminarRefrigerados(ProductosRefrigerados tmp) throws ClassNotFoundException,SQLException{
        Configuracion configuracion= new Configuracion();
        try{
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "DELETE FROM REFRIGERADOS WHERE CODIGO = ?";
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
     * Funcion Modificar datos en Mysql
     */
    public void modificarRefrigerados(ProductosRefrigerados tmp) throws ClassNotFoundException,SQLException{
        Configuracion configuracion= new Configuracion();
        try{
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "UPDATE REFRIGERADOS SET NOMBRE=?,PAIS=?,PRECIO=?,TIPOREFRI=?" +
                    "where CODIGO = ?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,tmp.getNombre());
            stmt.setString(2,tmp.getPais());
            stmt.setString(3,tmp.getPrecio());
            stmt.setString(4,tmp.getTiporefrigerador());
            stmt.setInt(5,tmp.getCodigo());
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
