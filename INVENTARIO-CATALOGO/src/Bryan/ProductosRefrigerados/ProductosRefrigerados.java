package Bryan.ProductosRefrigerados;

import Bryan.ProductosPadre.Productos;

public class ProductosRefrigerados extends Productos {
    private String tiporefrigerador;
    /**
     * Get y Set
     */
    public String getTiporefrigerador() {return tiporefrigerador;}
    public void setTiporefrigerador(String tiporefrigerador) {this.tiporefrigerador = tiporefrigerador;}
    /**
     * Constructor vacio
     */
    public ProductosRefrigerados() {
    }
    /**
     * Constructor con datos usando herencia "super"
     */
    public ProductosRefrigerados(String nombre, String pais, String precio, int codigo, String tiporefrigerador) {
        super(nombre, pais, precio, codigo);
        this.tiporefrigerador = tiporefrigerador;
    }
    /**
     * To String con herencia "Super"
     */
    @Override
    public String toString() {
        return "ProductosRefrigerados{" + super.toString()+
                "tiporefrigerador='" + tiporefrigerador + '\'' +
                '}';
    }
}
