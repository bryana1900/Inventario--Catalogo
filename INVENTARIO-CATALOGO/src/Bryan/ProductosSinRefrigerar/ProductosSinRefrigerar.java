package Bryan.ProductosSinRefrigerar;


import Bryan.ProductosPadre.Productos;

public class ProductosSinRefrigerar extends Productos {
    private String recomendaciones;
    private String tipo;
    /**
     * Get y Set
     */
    public String getRecomendaciones() {return recomendaciones;}
    public void setRecomendaciones(String recomendaciones) {this.recomendaciones = recomendaciones;}

    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}
    /**
     * Constructor vacio
     */
    public ProductosSinRefrigerar() {
    }
    /**
     * Constructor con datos de herencia "Super"
     */
    public ProductosSinRefrigerar(String nombre, String pais, String precio, int codigo, String recomendaciones, String tipo) {
        super(nombre, pais, precio, codigo);
        this.recomendaciones = recomendaciones;
        this.tipo = tipo;
    }
    /**
     * To String con herencia "Super"
     */
    @Override
    public String toString() {
        return "ProductosSinRefrigerar{" + super.toString() +
                "recomendaciones='" + recomendaciones + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
