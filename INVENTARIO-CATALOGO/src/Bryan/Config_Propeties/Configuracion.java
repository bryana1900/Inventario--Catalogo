package Bryan.Config_Propeties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {
    private String claseDB;
    private String strConexion;

    public String getClaseDB() {
        return claseDB;
    }

    public void setClaseDB(String claseDB) {
        this.claseDB = claseDB;
    }

    public String getStrConexion() {
        return strConexion;
    }

    public void setStrConexion(String strConexion) {
        this.strConexion = strConexion;
    }

    public Configuracion() {
        Properties prop= new Properties();
        String archivo="C:\\Users\\bryan\\Downloads\\Examen-Ampliacion\\INVENTARIO-CATALOGO\\src\\Bryan\\Config_Propeties\\config.properties";

        try {
            FileInputStream fis = new FileInputStream(archivo);
            prop.load(fis);
            this.setClaseDB(prop.getProperty("claseDB"));
            this.setStrConexion(prop.getProperty("stringConexion"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
