package Bryan.ProductosSinRefrigerar;

import Bryan.Config_Propeties.Configuracion;
import java.sql.*;
import java.util.ArrayList;

public class ProductosSinRefrigerarDAO {
    /**
     * Funcion insertar datos en Mysql
     */
    public void insertarSinRefrigerar(ProductosSinRefrigerar tmp) throws ClassNotFoundException, SQLException {
        try{
            Configuracion configuracion= new Configuracion();
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "INSERT INTO SINREFRIGERAR (NOMBRE,PAIS,RECOMENDACIONES,PRECIO,TIPO,CODIGO) VALUES(?,?,?,?,?,?)";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,tmp.getNombre());
            stmt.setString(2,tmp.getPais());
            stmt.setString(3,tmp.getRecomendaciones());
            stmt.setString(4,tmp.getPrecio());
            stmt.setString(5,tmp.getTipo());
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
    /**
     * Funcion listar datos que vienen de Mysql
     */
    public ArrayList<ProductosSinRefrigerar> listarSinRefrigerar() throws SQLException, ClassNotFoundException {
        Configuracion configuracion= new Configuracion();
        ArrayList<ProductosSinRefrigerar> productosSinRefrigerars= new ArrayList<ProductosSinRefrigerar>();
        try {
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            String query = "SELECT * FROM SINREFRIGERAR";
            Statement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);// el rs almacena la información de la base de datos.

            while (rs.next()) { //rs.next devuelve true si hay más líneas en el result set. por defecto, al iniciar el ciclo, el rs está en la línea 0.
                ProductosSinRefrigerar productosSinRefrigerar = new ProductosSinRefrigerar();
                productosSinRefrigerar.setNombre(rs.getString("NOMBRE"));
                productosSinRefrigerar.setPais(rs.getString("PAIS"));
                productosSinRefrigerar.setRecomendaciones(rs.getString("RECOMENDACIONES"));
                productosSinRefrigerar.setPrecio(rs.getString("PRECIO"));
                productosSinRefrigerar.setTipo(rs.getString("TIPO"));
                productosSinRefrigerar.setCodigo(rs.getInt("CODIGO"));


                productosSinRefrigerars.add(productosSinRefrigerar);
            }
            conn.close();
        }
        catch (SQLException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
        return productosSinRefrigerars;
    }
    /**
     * Funcion que elimina datos de Mysql
     */
    public void eliminarSinRefrigerar(ProductosSinRefrigerar tmp) throws ClassNotFoundException,SQLException{
        Configuracion configuracion= new Configuracion();
        try{
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "DELETE FROM SINREFRIGERAR WHERE CODIGO = ?";
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
     * Funcion de modificar datos de Mysql
     */
    public void modificarSinRefrigerar(ProductosSinRefrigerar tmp) throws ClassNotFoundException,SQLException{
        Configuracion configuracion= new Configuracion();
        try{
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "UPDATE SINREFRIGERAR SET NOMBRE=?,PAIS=?,PRECIO=?,RECOMENDACIONES=?,TIPO=?" +
                    "where CODIGO = ?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,tmp.getNombre());
            stmt.setString(2,tmp.getPais());
            stmt.setString(3,tmp.getPrecio());
            stmt.setString(4,tmp.getRecomendaciones());
            stmt.setString(5,tmp.getTipo());
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
