package Bryan.Inventario;

import Bryan.Config_Propeties.Configuracion;
import java.sql.*;
import java.util.ArrayList;

public class InventarioDAO {
    /**
     * Funcion de insertar datos en mysql
     */
    public void insertarInventario(Inventario tmp) throws ClassNotFoundException, SQLException {
        try{
            Configuracion configuracion= new Configuracion();
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "INSERT INTO INVENTARIO (NOMBRE,TIPO,CANTIDAD,FECHA,CODIGO) VALUES(?,?,?,?,?)";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,tmp.getNombre());
            stmt.setString(2,tmp.getTipo());
            stmt.setInt(3,tmp.getCantidad());
            stmt.setDate(4, Date.valueOf (tmp.getFecha()));
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
    /**
     * Funcion de listar datos que vienen de mysql
     */
    public ArrayList<Inventario> listarInventario() throws SQLException, ClassNotFoundException {
        Configuracion configuracion= new Configuracion();
        ArrayList<Inventario> inventarios= new ArrayList<Inventario>();
        try {
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            String query = "SELECT * FROM INVENTARIO";
            Statement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);// el rs almacena la información de la base de datos.

            while (rs.next()) { //rs.next devuelve true si hay más líneas en el result set. por defecto, al iniciar el ciclo, el rs está en la línea 0.
                Inventario inventario = new Inventario();
                inventario.setNombre(rs.getString("NOMBRE"));
                inventario.setTipo(rs.getString("TIPO"));
                inventario.setCantidad(rs.getInt("CANTIDAD"));
                inventario.setFecha(rs.getDate("Fecha").toLocalDate());
                inventario.setCodigo(rs.getInt("CODIGO"));

                inventarios.add(inventario);
            }
            conn.close();
        }
        catch (SQLException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
        return inventarios;
    }
    /**
     * Funcion de eliminar datos en mysql
     */
    public void eliminarInventario(Inventario tmp) throws ClassNotFoundException,SQLException{
        Configuracion configuracion= new Configuracion();
        try{
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "DELETE FROM INVENTARIO WHERE CODIGO = ?";
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
     * Funcion de modificar datos en mysql
     */
    public void modificarInventario(Inventario tmp) throws ClassNotFoundException,SQLException{
        Configuracion configuracion= new Configuracion();
        try{
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "UPDATE INVENTARIO SET NOMBRE=?,TIPO=?,CANTIDAD=?,FECHA=?" +
                    "where CODIGO = ?";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,tmp.getNombre());
            stmt.setString(2,tmp.getTipo());
            stmt.setInt(3,tmp.getCantidad());
            stmt.setDate(4, Date.valueOf (tmp.getFecha()));
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
