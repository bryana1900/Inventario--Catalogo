package Bryan.Inventario;

import java.time.LocalDate;

public class Inventario {
    private String nombre;
    private String tipo;
    private int Cantidad;
    private LocalDate fecha;
    private int codigo;
    /**
     * Get y set
     */
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}

    public int getCantidad() {return Cantidad;}
    public void setCantidad(int cantidad) {Cantidad = cantidad;}

    public LocalDate getFecha() {return fecha;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

    public int getCodigo() {return codigo;}
    public void setCodigo(int codigo) {this.codigo = codigo;}
    /**
     * Constructor vacio
     */
    public Inventario() {
    }
    /**
     * Constructor con datos
     */
    public Inventario(String nombre, String tipo, int cantidad, LocalDate fecha, int codigo) {
        this.nombre = nombre;
        this.tipo = tipo;
        Cantidad = cantidad;
        this.fecha = fecha;
        this.codigo = codigo;
    }
    /**
     * To string devuelve datos
     */
    @Override
    public String toString() {
        return "Inventario{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", Cantidad=" + Cantidad +
                ", fecha=" + fecha +
                ", codigo=" + codigo +
                '}';
    }
}
