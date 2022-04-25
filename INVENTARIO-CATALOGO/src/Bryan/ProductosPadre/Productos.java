package Bryan.ProductosPadre;

public class Productos {
    private String nombre;
    private String pais;
    private String precio;
    private int codigo;
    /**
     * Get y Set
     */
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getPais() {return pais;}
    public void setPais(String pais) {this.pais = pais;}

    public String getPrecio() {return precio;}
    public void setPrecio(String precio) {this.precio = precio;}

    public int getCodigo() {return codigo;}
    public void setCodigo(int codigo) {this.codigo = codigo;}
    /**
     * Constructor vacio
     */
    public Productos() {
    }
    /**
     * Constructor con datos
     */
    public Productos(String nombre, String pais, String precio, int codigo) {
        this.nombre = nombre;
        this.pais = pais;
        this.precio = precio;
        this.codigo = codigo;
    }
    /**
     * To String
     */
    @Override
    public String toString() {
        return "Productos{" +
                "nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", precio='" + precio + '\'' +
                ", codigo=" + codigo +
                '}';
    }
}
