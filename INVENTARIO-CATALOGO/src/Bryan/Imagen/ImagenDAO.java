package Bryan.Imagen;

import Bryan.Config_Propeties.Configuracion;

import java.io.InputStream;
import java.sql.*;

public class ImagenDAO {
    /**
     * Funcion de insertar imagen en mysql
     */
    public void insertarImagen(Imagen tmpImagen) throws ClassNotFoundException, SQLException {
        try{
            Configuracion configuracion= new Configuracion();
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "INSERT INTO IMAGEN (ID,IMAGEN) VALUES(?,?)";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,tmpImagen.getId());
            stmt.setBlob(2,tmpImagen.getImagen());
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
     * Funcion de obtener imagen
     */
    public Imagen obtenerImagen(String codigo) throws ClassNotFoundException, SQLException {
        Imagen imagen = new Imagen();
        try{
            Configuracion configuracion= new Configuracion();
            Connection conn = null;
            String query = "SELECT * FROM IMAGEN WHERE ID = ?";
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.setString(1,codigo);
            rs = stmt.executeQuery();

            while (rs.next()) { //rs.next devuelve true si hay más líneas en el result set. por defecto, al iniciar el ciclo, el rs está en la línea 0.
                imagen.setId(rs.getString("ID"));
                imagen.setImagen((InputStream) rs.getBlob("IMAGEN").getBinaryStream());
            }
            conn.close();
        }
        catch (SQLException e){
            throw e;

        }
        catch (Exception e){
            throw e;
        }
        return imagen;
    }
    /**
     * Funcion de obtener imagen
     */
    public String obtenerCodigoImagen() throws ClassNotFoundException, SQLException {
        try{
            Configuracion configuracion= new Configuracion();
            Class.forName(configuracion.getClaseDB());
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String strConexion = configuracion.getStrConexion();
            String query = "SELECT COUNT(*) FROM IMAGEN";
            conn = DriverManager.getConnection(strConexion);
            stmt = conn.prepareStatement(query);
            stmt.execute();
            int records=0;
            rs = stmt.executeQuery();
            while (rs.next()) {
                records = rs.getInt(1)+1;
            }
            return Integer.valueOf(records).toString();
        }
        catch (SQLException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
}
